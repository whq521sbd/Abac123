package com.aotuo.vegetable.act;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.aotuo.vegetable.R;
import com.aotuo.vegetable.adapter.PayResultAdapter;
import com.aotuo.vegetable.base.BaseActivity;
import com.aotuo.vegetable.entity.RecordEntity;
import com.aotuo.vegetable.util.CommonTools;
import com.aotuo.vegetable.view.TitleView;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PayInfoActivity extends BaseActivity implements View.OnClickListener{
    private TitleView titleView;
    private Button bt_payreturn;
    private PayResultAdapter adapter;
    private ListView listView;
    private List<String> num;
    private int index = 0;
    private List<RecordEntity> list = new ArrayList<RecordEntity>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pay_result);
        num = getIntent().getStringArrayListExtra("num");

        titleView = new TitleView();
        titleView.initView(this, "交易记录");

        bt_payreturn = (Button) findViewById(R.id.bt_payreturn);
        bt_payreturn.setOnClickListener(this);

        listView = (ListView) findViewById(R.id.listView);
        adapter = new PayResultAdapter(this, list);
        listView.setAdapter(adapter);
        index = 0;
        getData();
    }

    private void getData() {
        //参数：Token,Num
        HashMap<String, Object> param = new HashMap<String, Object>();
        param.put("Token", CommonTools.getToken(this));
        param.put("Num", num.get(index));
        MainActivity.postRequest(1, sHandler, "/GoodsA/TransInfo", param);
    }

    private Handler sHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {
                if (msg.arg1 > 0) {
                    RecordEntity re = null;
                    try {
                        re = new Gson().fromJson(msg.obj.toString(), RecordEntity.class);
                    } catch (JsonSyntaxException e) {
                        e.printStackTrace();
                    }
                    if (re != null) {
                        list.add(re);
                    }
                }
                index ++;
                if(index < num.size()){
                    getData();
                } else {
                    adapter.notifyDataSetChanged();
                }
            }
        }
    };

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bt_payreturn:
                finish();
                break;
            default:
                break;
        }

    }
}
