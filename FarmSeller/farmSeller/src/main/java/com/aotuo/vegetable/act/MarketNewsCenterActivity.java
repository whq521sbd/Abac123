package com.aotuo.vegetable.act;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.LinearLayout;

import com.aotuo.vegetable.R;
import com.aotuo.vegetable.adapter.ListViewAdapter;
import com.aotuo.vegetable.adapter.NewsAdapter;
import com.aotuo.vegetable.base.BaseActivity;
import com.aotuo.vegetable.entity.KeyValueData;
import com.aotuo.vegetable.entity.NewsData;
import com.aotuo.vegetable.entity.NewsListData;
import com.aotuo.vegetable.entity.NewsTabData;
import com.aotuo.vegetable.util.CommonTools;
import com.aotuo.vegetable.view.TitleView;
import com.aotuo.vegetable.view.XListView;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MarketNewsCenterActivity extends BaseActivity implements
        OnClickListener, XListView.IXListViewListener {
    private TitleView titleView;
    private LinearLayout tabList;
    private XListView news_listview;
    private NewsTabData listTabData;
    private NewsAdapter adapter;
    private List<NewsListData> list = new ArrayList<NewsListData>();
    public static String content;
    private SharedPreferences shared_key;
    private String key;
    private String mesid;
    private int curPage = 1;
    private int curTab = 0;
    private GridView chat_gridview;
    private List<KeyValueData> newsdatalist  = new ArrayList<KeyValueData>();
    private ListViewAdapter listViewAdapter = new ListViewAdapter(MarketNewsCenterActivity.this,newsdatalist);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_center);
        shared_key = getSharedPreferences("shared_key", Activity.MODE_PRIVATE);
        key = getIntent().getStringExtra("key");
        initUI();
        initTabdata();


    }

    private void initUI() {

        chat_gridview = (GridView) findViewById(R.id.chat_gridview);
        chat_gridview.setAdapter(listViewAdapter);

        content = "确认删除消息？";
        titleView = new TitleView();
        titleView.initView(this, "市场消息");

        news_listview = (XListView) findViewById(R.id.news_listview);
        news_listview.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                Intent intent_news = new Intent(MarketNewsCenterActivity.this,
                        WebPageActivity.class);
                int pos = arg2 -1;
                if(pos >= 0){
                    intent_news.putExtra("MessID", list.get(pos).getID());
                    intent_news.putExtra("title", list.get(pos).getTitle());
                    startActivityForResult(intent_news, 1002);
                }
            }
        });
        news_listview.setPullLoadEnable(true);
        news_listview.setPullRefreshEnable(true);
        news_listview.setXListViewListener(this);
        adapter = new NewsAdapter(MarketNewsCenterActivity.this, list);
        news_listview.setAdapter(adapter);
        tabList = (LinearLayout) findViewById(R.id.tabList);
    }

    private void initTabList() {
        tabList.removeAllViews();
        for (int i = 0; i < listTabData.getClasses().size(); i++) {
            //遍历老数据源，获取所有数据，并添加到新数据源中
            KeyValueData oldlist = listTabData.getClasses().get(i);
            newsdatalist.add(oldlist);
        }
        //数据更新后，刷新适配器
        listViewAdapter.notifyDataSetChanged();
    }

    private void selTab() {
//        for (int i = 0; i < listTabData.getClasses().size(); i++) {
//            RelativeLayout rl = (RelativeLayout) tabList.getChildAt(i);
//            TextView tv = (TextView) rl.findViewById(R.id.txt);
//            if (curTab == i)
//                tv.setTextColor(getResources().getColor(R.color.c06c1ae));
//            else
//                tv.setTextColor(getResources().getColor(R.color.c333333));
//        }
        curPage = 1;
        initdata(curPage);
    }

    /**
     * 获取tab数据
     */
    private void initTabdata() {
        HashMap<String,String> param = new HashMap<String, String>();
        MainActivity.getRequest(1, sHandler, "/NewsA/AllMessClass", param);
    }

    private void initdata(int index) {
        HashMap<String, Object> param = new HashMap<String, Object>();
        param.put("Token", CommonTools.getToken(this));
        param.put("curPage", index + "");
        param.put("NewsClass", listTabData.getClasses().get(curTab).getKey());
        MainActivity.postRequest(2, sHandler, "/NewsA/ClassMessList", param);
    }

    /**
     * 返回：{
     * Ver = "20160826170001",
     * Classes= new List<KeyValuePair<string, string>>()
     * { new KeyValuePair<string, string>("01", "最新热点")}
     */
    private Handler sHandler = new Handler() {
        public void handleMessage(Message msg) {
            if (msg.what == 1) {
                listTabData = null;
                try {
                    listTabData = new Gson().fromJson(msg.obj.toString(), NewsTabData.class);
                } catch (JsonSyntaxException e) {
                    e.printStackTrace();
                }
                if (listTabData != null) {
                    initTabList();
                    selTab();
                    //保存新闻版本号
                    CommonTools.setMessVer(MarketNewsCenterActivity.this, listTabData.getVer());
                }
            } else if (msg.what == 2) {
                news_listview.stopRefresh();
                news_listview.stopLoadMore();
                NewsData ld= null;
                try {
                    ld = new Gson().fromJson(msg.obj.toString(), NewsData.class);
                } catch (JsonSyntaxException e) {
                    e.printStackTrace();
                }
                if (ld != null && ld.getNews().size() > 0) {
                    if(curPage == 1){
                        list.clear();
                    }
                    curPage ++;
                    list.addAll(ld.getNews());
                } else {
                    if(curPage == 1){
                        list.clear();
                    }
                }
                adapter.notifyDataSetChanged();
            }
        }

    };

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Intent intent = new Intent();
            setResult(1003, intent);
            finish();
        }
        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                finish();
                break;
            default:
                break;
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 2342) {

        }
    }

    @Override
    protected void onDestroy() {
        content = "";
        super.onDestroy();
    }

    @Override
    public void onRefresh() {
        curPage = 1;
        initdata(curPage);
    }

    @Override
    public void onLoadMore() {
        initdata(curPage);
    }
}
