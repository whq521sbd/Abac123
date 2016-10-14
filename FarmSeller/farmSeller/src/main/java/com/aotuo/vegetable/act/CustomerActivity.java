package com.aotuo.vegetable.act;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.aotuo.vegetable.R;
import com.aotuo.vegetable.base.BaseActivity;
import com.aotuo.vegetable.entity.OtherUserEntity;
import com.aotuo.vegetable.hx.ChatActivity;
import com.aotuo.vegetable.util.CommonTools;
import com.aotuo.vegetable.util.FinalContent;
import com.aotuo.vegetable.view.TitleView;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import net.tsz.afinal.FinalBitmap;

import java.util.HashMap;

/**
 * Created by 牛XX on 2016/9/22.
 */

public class CustomerActivity extends BaseActivity implements View.OnClickListener {
    private String num;
    private TitleView titleView;
    private ImageView imgBusLic;
    private RelativeLayout mobile, tel, addr, email, fax, contactSell;
    private TextView txtMobile, txtTel, txtAddr, txtEmail, txtFax, userName, company;
    private OtherUserEntity oue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.customer_center);
        num = getIntent().getStringExtra("num");
        initUI();
        getData();
    }

    private void initUI() {
        titleView = new TitleView();
        titleView.initView(this, "卖家信息");
        imgBusLic = (ImageView) findViewById(R.id.imgBusLic);
        contactSell = (RelativeLayout) findViewById(R.id.contactSell);
        mobile = (RelativeLayout) findViewById(R.id.mobile);
        tel = (RelativeLayout) findViewById(R.id.tel);
        addr = (RelativeLayout) findViewById(R.id.addr);
        email = (RelativeLayout) findViewById(R.id.email);
        fax = (RelativeLayout) findViewById(R.id.fax);

        contactSell.setOnClickListener(this);
        mobile.setOnClickListener(this);
        tel.setOnClickListener(this);

        txtMobile = (TextView) findViewById(R.id.txtMobile);
        txtTel = (TextView) findViewById(R.id.txtTel);
        txtAddr = (TextView) findViewById(R.id.txtAddr);
        txtEmail = (TextView) findViewById(R.id.txtEmail);
        txtFax = (TextView) findViewById(R.id.txtFax);
        userName = (TextView) findViewById(R.id.userName);
        company = (TextView) findViewById(R.id.company);

    }

    private void initData(){
        txtMobile.setText(oue.getMobile());
        txtTel.setText(oue.getTel());
        txtAddr.setText(oue.getAddr());
        txtEmail.setText(oue.getEmail());
        txtFax.setText(oue.getFax());
        userName.setText(oue.getUserName());
        company.setText(oue.getComName());
        FinalBitmap.create(this).display(imgBusLic, FinalContent.FinalUrl + oue.getBusLic());
    }

    private void getData() {
        HashMap<String, Object> param = new HashMap<String, Object>();
        param.put("Token", CommonTools.getToken(this));
        param.put("SeeNum", num);

        MainActivity.postRequest(1, sHandler, "/UserA/UserInfo", param);
    }

    private Handler sHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {
                if (msg.arg1 > 0) {
                    oue = null;
                    try {
                        oue = new Gson().fromJson(msg.obj.toString(), OtherUserEntity.class);
                    } catch (JsonSyntaxException e) {
                        e.printStackTrace();
                    }
                }
                if (oue != null) {
                    initData();
                }
            }
        }
    };

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.contactSell:{
                Intent intent = new Intent(CustomerActivity.this, ChatActivity.class);
                intent.putExtra("RecID", oue.getNum());
                intent.putExtra("RecName", oue.getUserName());
                startActivity(intent);
            }
            break;
            case R.id.mobile:{
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:"+oue.getMobile()));
                startActivity(intent);
            }
            break;
            case R.id.tel:{
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:"+oue.getTel()));
                startActivity(intent);
            }
            break;
        }
    }
}
