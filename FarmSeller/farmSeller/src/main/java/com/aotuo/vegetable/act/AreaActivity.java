package com.aotuo.vegetable.act;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.aotuo.vegetable.R;
import com.aotuo.vegetable.adapter.CityAdapter;
import com.aotuo.vegetable.base.BaseActivity;
import com.aotuo.vegetable.entity.AreaMessage;
import com.aotuo.vegetable.sqlite.AreaDBDao;
import com.aotuo.vegetable.view.TitleView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 牛XX on 2016/9/20.
 */

public class AreaActivity extends BaseActivity {
    private TitleView titleView;
    private ListView listView;
    private List<AreaMessage> list = new ArrayList<AreaMessage>();
    private CityAdapter adapter;
    private AreaDBDao dao;
    private List<AreaMessage> areaStr = new ArrayList<AreaMessage>();
    private String mode; //0:选择省市区，   1:选择城市

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_area);
        mode = getIntent().getStringExtra("mode");

        dao = new AreaDBDao(this);
        initUI();
    }

    private void initUI() {
        titleView = new TitleView();
        titleView.initView(this, "选择地区");
        titleView.setLeftClick(new TitleView.ITitleBarLeft() {
            @Override
            public void onLeftClick() {
                if (areaStr.size() > 0) {
                    areaStr.remove(areaStr.size() - 1);
                    String id = "0";
                    if (areaStr.size() > 0) {
                        AreaMessage am = areaStr.get(areaStr.size() - 1);
                        id = am.getGc_id();
                    }
                    List<AreaMessage> tlist = getData(id);
                    if (tlist != null) {
                        list.clear();
                        list.addAll(tlist);
                        adapter.notifyDataSetChanged();
                    } else {
                        toBack();
                    }
                } else {
                    setResult(RESULT_CANCELED);
                    finish();
                }
            }
        });

        listView = (ListView) findViewById(R.id.listView);
        List<AreaMessage> tlist = getData("0");
        if (tlist != null)
            list.addAll(tlist);
        adapter = new CityAdapter(this, list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                AreaMessage am = list.get(position);
                areaStr.add(am);
                if (areaStr.size() == 1 && "1".equals(mode)) {
                    toBack1();
                } else if (areaStr.size() < 3) {
                    List<AreaMessage> tlist = getData(am.getGc_id());
                    if (tlist != null) {
                        list.clear();
                        list.addAll(tlist);
                        adapter.notifyDataSetChanged();
                    } else {
                        toBack();
                    }
                } else {
                    toBack();
                }
            }
        });
    }

    private List<AreaMessage> getData(String id) {
        if (id == null)
            id = "0";
        return dao.getChildArea(id);
    }

    private void toBack1() {
        String city = "";
        Intent intent = new Intent();
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < areaStr.size(); i ++){
            sb.append(areaStr.get(i).getGc_name());
            city = areaStr.get(i).getGc_name();
        }

        intent.putExtra("areaName", sb.toString());
        intent.putExtra("cityName", city);
        intent.putExtra("areaNum", areaStr.get(areaStr.size() - 1).getGc_id());
        setResult(RESULT_OK, intent);
        finish();
    }

    private void toBack() {
        String city = "";
        Intent intent = new Intent();
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < areaStr.size(); i ++){
            sb.append(areaStr.get(i).getGc_name());
            if(i == 1){
                city = areaStr.get(i).getGc_name();
            }
        }

        intent.putExtra("areaName", sb.toString());
        intent.putExtra("cityName", city);
        intent.putExtra("areaNum", areaStr.get(areaStr.size() - 1).getGc_id());
        setResult(RESULT_OK, intent);
        finish();
    }
}
