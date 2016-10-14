package com.aotuo.vegetable.act;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

import com.aotuo.vegetable.R;
import com.aotuo.vegetable.adapter.BoothAdapter;
import com.aotuo.vegetable.base.BaseActivity;
import com.aotuo.vegetable.entity.BoothData;
import com.aotuo.vegetable.entity.BoothEntity;
import com.aotuo.vegetable.util.CommonTools;
import com.aotuo.vegetable.util.MyToast;
import com.aotuo.vegetable.util.StringUtils;
import com.aotuo.vegetable.view.TitleView;
import com.aotuo.vegetable.view.XListView;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by 牛XX on 2016/9/24.
 */

public class MarketActivity extends BaseActivity {
    private static final int TO_NEXT = 131;
    private XListView listView;
    private TitleView titleView;
    private TextView noData;
    private List<BoothEntity> list = new ArrayList<BoothEntity>();
    private BoothAdapter adapter;
    private String goodsName, matketNum, matketName;
    private int curPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_city_market);
        goodsName = getIntent().getStringExtra("goodsName");
        matketNum = getIntent().getStringExtra("MatketNum");
        matketName = getIntent().getStringExtra("MatketName");
        initUI();
        curPage = 1;
        getData(curPage);
    }

    private void initUI() {
        titleView = new TitleView();
        titleView.initView(this, matketName);
        titleView.setLeftClick(new TitleView.ITitleBarLeft() {
            @Override
            public void onLeftClick() {
                setResult(RESULT_OK);
                finish();
            }
        });
        listView = (XListView) findViewById(R.id.listView);
        noData = (TextView) findViewById(R.id.noData);
        listView.setPullRefreshEnable(true);
        listView.setPullLoadEnable(true);
        listView.setXListViewListener(new XListView.IXListViewListener() {
            @Override
            public void onRefresh() {
                curPage = 1;
                getData(curPage);
            }

            @Override
            public void onLoadMore() {
                getData(curPage + 1);
            }
        });
        adapter = new BoothAdapter(this, list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int pos = position - 1;
                if (pos >= 0) {
                    if(CommonTools.getUserSn().equals(list.get(pos).getUserNum())){
                        MyToast.showToast(MarketActivity.this, "这是自己的摊位！", 2);
                    } else {
                        Intent intent = new Intent(MarketActivity.this, BoothActivity.class);
                        intent.putExtra("goodsName", goodsName);
                        intent.putExtra("Booth", list.get(pos).getBooth());
                        intent.putExtra("UserNum", list.get(pos).getUserNum());
                        startActivityForResult(intent, TO_NEXT);
                    }
                }
            }
        });
    }

    /**
     * MarketsByAreaNum：
     * 参数：Token, curPage,MatketNum,goodsName
     */
    private void getData(int page) {
        HashMap<String, Object> param = new HashMap<String, Object>();
        param.put("Token", CommonTools.getToken(this));
        param.put("curPage", "" + page);
        param.put("MatketNum", matketNum);
        if (!StringUtils.isEmpty(goodsName))
            param.put("goodsName", goodsName);
        MainActivity.postRequest(1, sHandler, "/GoodsA/BoothSMarketGoods", param);
    }

    private Handler sHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {
                listView.stopLoadMore();
                listView.stopRefresh();
                if (msg.arg1 > 0) {
                    BoothData bd = null;
                    try {
                        bd = new Gson().fromJson(msg.obj.toString(), BoothData.class);
                    } catch (JsonSyntaxException e) {
                        e.printStackTrace();
                    }
                    if (bd != null) {
                        if (curPage == 1) {
                            list.clear();
                        }
                        for(int i = 0; i< bd.getBooths().size(); i++){
                            if(CommonTools.getUserSn().equals(bd.getBooths().get(i).getUserNum())){
                                continue;
                            } else {
                                list.add(bd.getBooths().get(i));
                            }
                        }
                        curPage = bd.getCurPage();
                    }
                }
                adapter.notifyDataSetChanged();
                if (list.size() > 0)
                    noData.setVisibility(View.GONE);
                else
                    noData.setVisibility(View.VISIBLE);
            }
        }
    };
    @Override
    public void onBackPressed() {
        setResult(RESULT_FIRST_USER);
        super.onBackPressed();
        finish();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_CANCELED){
            setResult(RESULT_CANCELED);
            finish();
        }
    }
}
