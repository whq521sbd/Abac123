package com.aotuo.vegetable.act;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.aotuo.vegetable.R;
import com.aotuo.vegetable.adapter.RecordAdapter;
import com.aotuo.vegetable.base.BaseActivity;
import com.aotuo.vegetable.entity.OtherUserEntity;
import com.aotuo.vegetable.entity.RecordEntity;
import com.aotuo.vegetable.hx.ChatActivity;
import com.aotuo.vegetable.util.CommonTools;
import com.aotuo.vegetable.view.TitleView;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 摊位页面
 * Created by 牛XX on 2016/9/24.
 */

public class BoothActivity extends BaseActivity implements View.OnClickListener{
    private TitleView titleView;
    private TextView name, tel;
    private LinearLayout sendMsg, llTel;
    private ListView listView;
    private String goodsName, matketNum, boothName;
    private OtherUserEntity oue;
    private List<RecordEntity> list = new ArrayList<RecordEntity>();
    private RecordAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_booth);
        goodsName = getIntent().getStringExtra("goodsName");
        boothName = getIntent().getStringExtra("Booth");
        matketNum = getIntent().getStringExtra("UserNum");

        titleView = new TitleView();
        titleView.initView(this, boothName, "放弃");
        titleView.setLeftClick(new TitleView.ITitleBarLeft() {
            @Override
            public void onLeftClick() {
                setResult(RESULT_OK);
                finish();
            }
        });
        titleView.setItitleBar(new TitleView.ITitleBar() {
            @Override
            public void onRightClick() {
                setResult(RESULT_CANCELED);
                startActivity(new Intent(BoothActivity.this,NolocalActivity.class));
                finish();
            }
        });
        name = (TextView) findViewById(R.id.name);
        tel = (TextView) findViewById(R.id.tel);
        sendMsg = (LinearLayout) findViewById(R.id.sendMsg);
        llTel = (LinearLayout) findViewById(R.id.llTel);
        llTel.setOnClickListener(this);
        sendMsg.setOnClickListener(this);
        listView = (ListView) findViewById(R.id.listView);
        adapter = new RecordAdapter(this, list);
        listView.setAdapter(adapter);
        getInfo();
    }

    private void initData(){
        name.setText(oue.getUserName());
        tel.setText(oue.getMobile());

    }

    private void getInfo(){
        HashMap<String, Object> param = new HashMap<String, Object>();
        param.put("Token", CommonTools.getToken(this));
        param.put("SeeNum", matketNum);
        MainActivity.postRequest(1, sHandler, "/UserA/UserInfo", param);
    }

    /**
     * 参数：Token,SeeNum
     */
    private void getRecord(){
        HashMap<String, Object> param = new HashMap<String, Object>();
        param.put("Token", CommonTools.getToken(this));
        param.put("SeeNum", matketNum);
        MainActivity.postRequest(2, sHandler, "/GoodsA/LeastTransactions", param);
    }

    private Handler sHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 1:{
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
                        getRecord();
                    }
                }
                break;
                case 2:{
                    List<RecordEntity> tlist = null;
                    try {
                        tlist = new Gson().fromJson(msg.obj.toString(), new TypeToken<List<RecordEntity>>(){}.getType());
                    } catch (JsonSyntaxException e) {
                        e.printStackTrace();
                    }
                    if(tlist != null){
                        list.clear();
                        list.addAll(tlist);
                        adapter.notifyDataSetChanged();
                    }
                }
                break;
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
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tel:{
                Intent intent = new Intent(Intent.ACTION_DIAL);
                Uri data = Uri.parse("tel:" + oue.getMobile());
                intent.setData(data);
                startActivity(intent);
            }
            break;
            case R.id.sendMsg:{
                Intent intent = new Intent(BoothActivity.this, ChatActivity.class);
                intent.putExtra("RecID", oue.getNum());
                intent.putExtra("RecName", oue.getUserName());
                intent.putExtra("curStatus", "buy");
                intent.putExtra("otherHeadImg", oue.getHeadImg());
                intent.putExtra("otherBooth", oue.getBooth());
                startActivity(intent);
            }
            break;
        }
    }
}
