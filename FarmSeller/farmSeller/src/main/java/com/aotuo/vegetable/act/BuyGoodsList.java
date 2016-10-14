package com.aotuo.vegetable.act;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.aotuo.vegetable.R;
import com.aotuo.vegetable.adapter.GoodsAdapter;
import com.aotuo.vegetable.base.BaseActivity;
import com.aotuo.vegetable.entity.GoodsA;
import com.aotuo.vegetable.entity.GoodsListData;
import com.aotuo.vegetable.util.CommonTools;
import com.aotuo.vegetable.view.TitleView;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by 牛XX on 2016/8/26.
 */
public class BuyGoodsList extends BaseActivity {
    private final static int TO_CREATE = 12;
    private final static int TO_EDIT = 13;
    private ListView listView;
    private TitleView title;
    private GoodsListData goodsData;
    private GoodsAdapter adapter;
    private List<GoodsA> goodsList = new ArrayList<GoodsA>();
    private int curPage = 1;
    private String userNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_goods_list);
        userNum = getIntent().getStringExtra("UserNum");
        initUI();
        getList();
    }

    private void initUI() {
        title = new TitleView();
        title.initView(BuyGoodsList.this, "商品");
        title.setItitleBar(new TitleView.ITitleBar() {
            @Override
            public void onRightClick() {
                Intent intent = new Intent(BuyGoodsList.this, ClassicActivity.class);
                startActivityForResult(intent, TO_CREATE);
            }
        });

        listView = (ListView) findViewById(R.id.listView);

        adapter = new GoodsAdapter(BuyGoodsList.this, goodsList);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                intent.putExtra("goodsA", goodsList.get(position));
                setResult(RESULT_OK, intent);
                finish();
            }
        });

    }

    private void getList() {
        HashMap<String, String> param = new HashMap<String, String>();
        param.put("ClassID", "0");
        param.put("Token", CommonTools.getToken(BuyGoodsList.this));
        param.put("curPage", "" + curPage);
        param.put("UserNum", userNum);
        MainActivity.getRequest(1, sHandler, "/GoodsA/GoodsListByNum", param);
    }

    private Handler sHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {//获取商品列表
                goodsData = null;
                try {
                    goodsData = new Gson().fromJson(msg.obj.toString(),
                            GoodsListData.class);
                } catch (JsonSyntaxException e) {
                    e.printStackTrace();
                }
                if (goodsData != null) {
                    if (curPage == 1)
                        goodsList.clear();
                    if (goodsData.getGoods() != null && goodsData.getGoods().size() > 0) {
                        goodsList.addAll(goodsData.getGoods());
                        curPage++;
                    }
                    adapter.notifyDataSetChanged();
                } else if (curPage == 1) {
                    goodsList.clear();
                    adapter.notifyDataSetChanged();
                }
            }
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == TO_CREATE && resultCode == RESULT_OK) {
            curPage = 1;
            getList();
        } else if (requestCode == TO_EDIT && resultCode == RESULT_OK) {
            curPage = 1;
            getList();
        }
    }
}
