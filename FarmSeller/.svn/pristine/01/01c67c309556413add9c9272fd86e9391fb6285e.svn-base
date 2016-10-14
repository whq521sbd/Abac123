package com.aotuo.vegetable.act;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.aotuo.vegetable.R;
import com.aotuo.vegetable.adapter.RecordAdapter;
import com.aotuo.vegetable.base.BaseActivity;
import com.aotuo.vegetable.entity.CustomEntity;
import com.aotuo.vegetable.entity.RecordData;
import com.aotuo.vegetable.entity.RecordEntity;
import com.aotuo.vegetable.util.CommonTools;
import com.aotuo.vegetable.util.MyToast;
import com.aotuo.vegetable.view.TitleView;
import com.aotuo.vegetable.view.XListView;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class MyRecord extends BaseActivity implements OnClickListener, XListView.IXListViewListener {
    private TitleView titleView = new TitleView();
    private RelativeLayout searchBar;
    private TextView curDay, preDay, nextDay, toSearch;
    private EditText editSearch;
    private XListView listView;
    private List<RecordEntity> reList = new ArrayList<RecordEntity>();
    private RecordAdapter adapter;
    private String type;
    private int period = 0;
    private CustomEntity customEntity;
    private int curPage = 0;
    private int totalPage = 0;
    private long selTime, curTime, getTime;
    private final long day = 1000 * 3600 * 24;
    private boolean isRunning = false;
    private boolean isLoading = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_myrecord);
        type = getIntent().getStringExtra("type");
        searchBar = (RelativeLayout) findViewById(R.id.searchBar);
        if ("current".equals(type)) {
            searchBar.setVisibility(View.GONE);
        } else if ("custom".equals(type)) {
            searchBar.setVisibility(View.GONE);
            customEntity = (CustomEntity) getIntent().getSerializableExtra("customData");
        }
        curTime = System.currentTimeMillis();
        selTime = curTime;
        getTime = curTime;
        initUI();
        setSelectTime();
        curPage = 1;
        getData(curPage);
    }

    private String createTime(long time) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(new Date(time));
    }

    private void setSelectTime(){
        curDay.setText(createTime(selTime));
    }

    /**
     * 获取订单信息列表
     */
    private void getData(int page) {
        if ("current".equals(type)) {
            HashMap<String, Object> param = new HashMap<String, Object>();
            param.put("Token", CommonTools.getToken(this));
            param.put("queryTime", createTime(getTime));
            param.put("curPage", "" + page);
            MainActivity.postRequest(1, sHandler, "/GoodsA/QueryTransByTime", param);
        } else if ("custom".equals(type)) {
            //参数：Token,queryTime,CustomerNum(对方帐户编号),curPage
            HashMap<String, Object> param = new HashMap<String, Object>();
            param.put("Token", CommonTools.getToken(this));
            param.put("queryTime", createTime(getTime));
            param.put("CustomerNum", customEntity.getID());
            param.put("curPage", "" + page);
            MainActivity.postRequest(1, sHandler, "/GoodsA/CustomerTrans", param);
        }
    }

    private Handler sHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {
                isLoading = false;
                listView.stopRefresh();
                listView.stopLoadMore();
                if(!isRunning)
                    return;
                if (msg.arg1 > 0) {
                    RecordData rd = null;
                    try {
                        rd = new Gson().fromJson(msg.obj.toString(), RecordData.class);
                    } catch (JsonSyntaxException e) {
                        e.printStackTrace();
                    }
                    if (rd != null && rd.getTrans() != null && rd.getTrans().size() > 0) {
                        if (rd.getCurPage() == 1)
                            reList.clear();
                        reList.addAll(rd.getTrans());
                        curPage = rd.getCurPage();
                        totalPage = rd.getPageSize();
                    } else if (curPage == 1) {
                        reList.clear();
                        MyToast.showToast(MyRecord.this, "没有交易记录！", 3);
                    }

                } else if (curPage == 1) {
                    reList.clear();
                    MyToast.showToast(MyRecord.this, "没有交易记录！", 3);
                }
                selTime = getTime;
                setSelectTime();
                adapter.notifyDataSetChanged();
            }
        }
    };

    private void initUI() {
        // TODO Auto-generated method stub
        if ("current".equals(type)) {
            titleView.initView(this, "我的交易记录");
        } else if ("custom".equals(type)) {
            titleView.initView(this, customEntity.getFullName());
        }
        curDay = (TextView) findViewById(R.id.curDay);
        preDay = (TextView) findViewById(R.id.preDay);
        preDay.setOnClickListener(this);
        nextDay = (TextView) findViewById(R.id.nextDay);
        nextDay.setOnClickListener(this);
        listView = (XListView) findViewById(R.id.listView);

        adapter = new RecordAdapter(this, reList);
        listView.setAdapter(adapter);
        listView.setPullLoadEnable(true);
        listView.setPullRefreshEnable(true);
        listView.setXListViewListener(this);

        toSearch = (TextView) findViewById(R.id.toSearch);
        editSearch = (EditText) findViewById(R.id.editSearch);

        toSearch.setOnClickListener(this);
    }

    @Override
    public void onClick(View arg0) {
        // TODO Auto-generated method stub
        switch (arg0.getId()) {
            case R.id.preDay: {//上一天
                if(isLoading){
                    MyToast.showToast(MyRecord.this, "正在加载数据，请稍后！", 3);
                    return;
                }
                isLoading = true;
                getTime = selTime - day;
                curPage = 1;
                getData(curPage);
            }
            break;
            case R.id.nextDay: {//下一天
                if(isLoading){
                    MyToast.showToast(MyRecord.this, "正在加载数据，请稍后！", 3);
                    return;
                }
                getTime = selTime + day;
                if (getTime <= curTime) {
                    curPage = 1;
                    getData(curPage);
                } else {
                    MyToast.showToast(MyRecord.this, "还没有明天的交易记录！", 3);
                }
            }
            break;
            case R.id.toSearch: {
                CommonTools.hideSoftkey(MyRecord.this, editSearch);
            }
            break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        isRunning = true;
    }

    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
        isRunning = false;
        CommonTools.hideSoftkey(MyRecord.this, editSearch);
    }

    @Override
    public void onRefresh() {
        curPage = 1;
        getData(curPage);

    }

    @Override
    public void onLoadMore() {
        if (curPage < totalPage)
            getData(curPage + 1);
        listView.stopRefresh();
        listView.stopLoadMore();
    }
}
