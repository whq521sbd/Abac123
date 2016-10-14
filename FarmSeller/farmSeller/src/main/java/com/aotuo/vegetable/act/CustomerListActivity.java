package com.aotuo.vegetable.act;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.aotuo.vegetable.R;
import com.aotuo.vegetable.adapter.CustomListAdapter;
import com.aotuo.vegetable.entity.CustomEntity;
import com.aotuo.vegetable.util.CommonTools;
import com.aotuo.vegetable.view.TitleView;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by 牛XX on 2016/9/10.
 */

public class CustomerListActivity extends Activity {
    private TitleView titleView;
    private ListView listView;
    private List<CustomEntity> list = new ArrayList<CustomEntity>();
    private CustomListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_custom_list);
        initUI();
        getData();
    }

    private void initUI() {
        titleView = new TitleView();
        titleView.initView(this, "客户列表");
        listView = (ListView) findViewById(R.id.listView);
        adapter = new CustomListAdapter(this, list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(CustomerListActivity.this, MyRecord.class);
                intent.putExtra("type", "custom");
                intent.putExtra("customData", list.get(position));
                startActivity(intent);
            }
        });

    }

    private void getData() {//参数：Token,ViewType(1,调用做为卖家;2,调用者做为买家)
        HashMap<String, Object> param = new HashMap<String, Object>();
        param.put("Token", CommonTools.getToken(this));
        param.put("ViewType", "1");
        MainActivity.postRequest(1, sHandler, "/UserA/Customer", param);
    }

    private Handler sHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {
                if (msg.arg1 > 0) {
                    List<CustomEntity> tlist = null;
                    try {
                        tlist = new Gson().fromJson(msg.obj.toString(),
                                new TypeToken<List<CustomEntity>>() {
                                }.getType());
                    } catch (JsonSyntaxException e) {
                        e.printStackTrace();
                    }
                    if (tlist != null) {
                        list.clear();
                        list.addAll(tlist);
                        adapter.notifyDataSetChanged();
                    }
                }
            }
        }
    };
}
