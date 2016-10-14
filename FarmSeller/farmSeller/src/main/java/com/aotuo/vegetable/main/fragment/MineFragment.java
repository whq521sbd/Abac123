package com.aotuo.vegetable.main.fragment;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.aotuo.vegetable.R;
import com.aotuo.vegetable.act.CustomerInterlligence;
import com.aotuo.vegetable.act.CustomerListActivity;
import com.aotuo.vegetable.act.GoodsList;
import com.aotuo.vegetable.act.MainActivity;
import com.aotuo.vegetable.act.MyAccount;
import com.aotuo.vegetable.act.MyCenterActivity;
import com.aotuo.vegetable.act.MyRecord;
import com.aotuo.vegetable.base.BaseFragment;
import com.aotuo.vegetable.entity.UserEntity;
import com.aotuo.vegetable.util.CommonTools;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.util.HashMap;

import lecho.lib.hellocharts.samples.LineChartActivity;


public class MineFragment extends BaseFragment implements OnClickListener {

    private TextView myGoods, account, todayRecord, customerRecord,
            customerIntelligence, exit, statistics, myInfo, market, idnumber,txt_booth;
    private SharedPreferences shared_key;
    private SharedPreferences shared_city;
    private String key;
    private LayoutInflater inflater;
    private LinearLayout onlySell;
    private ViewGroup container;
    private Bundle savedInstanceState;
    private TextView userName;
    private View view;
    private View titleGap;
    private UserEntity user;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.inflater = inflater;
        this.container = container;
        this.savedInstanceState = savedInstanceState;
        if (view == null) {
            view = inflater.inflate(R.layout.mine_fragment, null);
            shared_key = getActivity().getSharedPreferences("shared_key",
                    Activity.MODE_PRIVATE);
            key = shared_key.getString("key", null);
            shared_city = getActivity().getSharedPreferences("shared_city",
                    Activity.MODE_PRIVATE);
            initUI(view);
            initdata();
            getData();
        }
        ViewGroup parent = (ViewGroup) view.getParent();
        if (parent != null) {
            parent.removeView(view);
        }
        return view;
    }

    private void initUI(View view) {
        txt_booth = (TextView) view.findViewById(R.id.txt_booth);
        onlySell = (LinearLayout) view.findViewById(R.id.onlySell);
        userName = (TextView) view.findViewById(R.id.userName);
        myGoods = (TextView) view.findViewById(R.id.myGoods);
        account = (TextView) view.findViewById(R.id.account);
        todayRecord = (TextView) view.findViewById(R.id.todayRecord);
        customerRecord = (TextView) view
                .findViewById(R.id.customerRecord);
        customerIntelligence = (TextView) view
                .findViewById(R.id.customerIntelligence);
        exit = (TextView) view.findViewById(R.id.exit);
        myInfo = (TextView) view.findViewById(R.id.myInfo);
        statistics = (TextView) view.findViewById(R.id.statistics);
        market = (TextView) view.findViewById(R.id.market);
        idnumber = (TextView) view.findViewById(R.id.idnumber);

        myGoods.setOnClickListener(this);
        account.setOnClickListener(this);
        todayRecord.setOnClickListener(this);
        customerRecord.setOnClickListener(this);
        customerIntelligence.setOnClickListener(this);
        exit.setOnClickListener(this);
        myInfo.setOnClickListener(this);
        statistics.setOnClickListener(this);

        titleGap = view.findViewById(R.id.titleGap);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            titleGap.setVisibility(View.VISIBLE);
        } else {
            titleGap.setVisibility(View.GONE);
        }
    }

    private void initdata() {
        userName.setText(CommonTools.getUserName(getActivity()));



        if (CommonTools.isSell(getActivity())){
            if (MainActivity.getUser()!=null){
                market.setText(MainActivity.getUser().getBooth());
            }
        }else {
            txt_booth.setVisibility(View.INVISIBLE);
        }



        if (CommonTools.isSell(getActivity()))
            idnumber.setText(CommonTools.getMarketName(getActivity()));
        else{
            if(user != null)
                idnumber.setText(user.getAddr());
        }
        if (CommonTools.isSell(getActivity()))
            onlySell.setVisibility(View.VISIBLE);
        else
            onlySell.setVisibility(View.GONE);
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.myGoods: {// 我的商品
                startActivity(new Intent(getActivity(), GoodsList.class));
            }
            break;
            case R.id.account: {//我的账户
                startActivity(new Intent(getActivity(), MyAccount.class));
            }
            break;
            case R.id.todayRecord: {//我的交易记录
                Intent intent = new Intent(getActivity(), MyRecord.class);
                intent.putExtra("type", "current");
                startActivity(intent);
            }
            break;
            case R.id.customerRecord: {//我的客户
                Intent intent = new Intent(getActivity(), CustomerListActivity.class);
                startActivity(intent);
            }
            break;
            case R.id.customerIntelligence: {//客户情报
                startActivity(new Intent(getActivity(), CustomerInterlligence.class));
            }
            break;
            case R.id.exit: {
                toExit();
            }
            break;
            case R.id.statistics: {//数据统计
                Intent intent;
                intent = new Intent(getActivity(), LineChartActivity.class);
                startActivity(intent);
            }
            break;
            case R.id.myInfo: {//个人资料
                Intent intent;
                intent = new Intent(getActivity(), MyCenterActivity.class);
                startActivity(intent);
            }
            break;
        }
    }

    private void toExit() {
        HashMap<String, Object> param = new HashMap<String, Object>();
        param.put("Token", CommonTools.getToken(getActivity()));
        MainActivity.postRequest(1, sHandler, "/UserA/LogOut", param);
    }
    private void getData() {
        HashMap<String, Object> param = new HashMap<String, Object>();
        param.put("Token", CommonTools.getToken(getActivity()));
        MainActivity.postRequest(2, sHandler, "/UserA/UserDetail", param);
    }

    private Handler sHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {
                CommonTools.setToken(getActivity(), "");
                System.exit(0);
            } else if(msg.what == 2){
                if (msg.arg1 > 0) {
                    user = null;
                    try {
                        user = new Gson().fromJson(msg.obj.toString(), UserEntity.class);
                    } catch (JsonSyntaxException e) {
                        e.printStackTrace();
                    }
                    if(user != null){
                        if (CommonTools.isSell(getActivity()))
                            idnumber.setText(CommonTools.getMarketName(getActivity()));
                        else{
                            idnumber.setText(user.getAddr());
                        }
                    }
                }
            }
        }
    };

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    }

    @Override
    public void handleCallBack(Message msg) {

    }
}
