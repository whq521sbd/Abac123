package com.aotuo.vegetable.act;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.aotuo.vegetable.R;
import com.aotuo.vegetable.base.BaseActivity;
import com.aotuo.vegetable.entity.BlanceEntity;
import com.aotuo.vegetable.util.CommonTools;
import com.aotuo.vegetable.util.MyToast;
import com.aotuo.vegetable.view.TitleView;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.util.HashMap;

public class MyAccount extends BaseActivity implements OnClickListener {
    private TextView txtBlance;
    private TitleView titleView;
    private RelativeLayout recharge, withdrawCash;
    private BlanceEntity blanceEntity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_myaccount);
        initUI();
        getData();
    }

    private void initUI() {
        // TODO Auto-generated method stub
        titleView = new TitleView();
        titleView.initView(this, "我的资金账户");
        txtBlance = (TextView) findViewById(R.id.txtBlance);
        recharge = (RelativeLayout) findViewById(R.id.recharge);
        recharge.setOnClickListener(this);
        withdrawCash = (RelativeLayout) findViewById(R.id.withdrawCash);
        withdrawCash.setOnClickListener(this);

    }

    private void getData() {
        HashMap<String, Object> param = new HashMap<String, Object>();
        param.put("Token", CommonTools.getToken(this));
        MainActivity.postRequest(1, sHandler, "/UserA/Balance", param);
    }

    private Handler sHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1: {
                    if(msg.arg1 > 0){
                        blanceEntity = null;
                        try {
                            blanceEntity = new Gson().fromJson(msg.obj.toString(), BlanceEntity.class);
                        } catch (JsonSyntaxException e) {
                            e.printStackTrace();
                        }
                        if(blanceEntity != null){
                            txtBlance.setText(blanceEntity.getBalance() + "元");
                        } else {
                            txtBlance.setText("0.00元");
                        }
                    } else {
                        MyToast.showToast(MyAccount.this, msg.obj.toString(), 2);
                    }
                }
                break;
            }
        }
    };

    @Override
    public void onClick(View arg0) {
        // TODO Auto-generated method stub
        switch (arg0.getId()) {
            case R.id.recharge: {
                finish();
            }
            break;
            case R.id.withdrawCash: {
                finish();
            }
            break;
        }
    }

}
