package com.aotuo.vegetable.act;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.aotuo.vegetable.R;
import com.aotuo.vegetable.adapter.GuidePagerAdapter;
import com.aotuo.vegetable.base.BaseActivity;
import com.aotuo.vegetable.util.CommonTools;
import com.aotuo.vegetable.util.NetManager;
import com.aotuo.vegetable.util.StringUtils;

import java.util.ArrayList;
import java.util.List;


public class GuideActivity extends BaseActivity {
    private NetManager netManager;
    //private ImageView img1, img2, img3;
    private ViewPager VP_guideviewpager;
    private  View view01,view02,view03;
    private List<View> viewlist;
    private TextView tv_into;

    //private LocationService locationService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        netManager = new NetManager(this);
        /*img1 = (ImageView) findViewById(R.id.img1);
        img2 = (ImageView) findViewById(R.id.img2);
        img3 = (ImageView) findViewById(R.id.img3);
        img2.setVisibility(View.GONE);
        img3.setVisibility(View.GONE);*/
        VP_guideviewpager = (ViewPager) findViewById(R.id.VP_guideviewpager);
        tv_into = (TextView) findViewById(R.id.tv_into);
        LayoutInflater layoutInflater =getLayoutInflater();
        view01 =  layoutInflater.inflate(R.layout.activity_guide1,null);
        view02 =  layoutInflater.inflate(R.layout.activity_guide2,null);
        view03 =  layoutInflater.inflate(R.layout.activity_guide3,null);
        tv_into = (TextView) view03.findViewById(R.id.tv_into);
        viewlist =  new ArrayList<View>();
//        viewlist.add(view01);
//        viewlist.add(view02);
        viewlist.add(view03);

        GuidePagerAdapter adapter =  new GuidePagerAdapter((ArrayList<View>) viewlist);
        VP_guideviewpager.setAdapter(adapter);

        tv_into.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(GuideActivity.this,
                        MainActivity.class);
                startActivity(intent);
                finish();
            }
        });


    }


    @Override
    protected void onStart() {
        goToMain();
        super.onStart();
    }

    // 进入主程序的方法
    private void goToMain() {
        if (netManager.isOpenNetwork() || netManager.isOpenWifi()) {
            new Handler(){
                @Override
                public void handleMessage(Message msg) {
                    super.handleMessage(msg);
                    if(!StringUtils.isEmpty(CommonTools.getToken(GuideActivity.this))){
                        Intent intent = new Intent(GuideActivity.this,
                                MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }
            }.sendEmptyMessageDelayed(1, 1000);

        } else {
            // 如果网络不可用，则弹出对话框，对网络进行设置
            AlertDialog.Builder builder = new Builder(GuideActivity.this);
            builder.setTitle("开启网络服务");
            builder.setMessage("网络没有连接，请到设置进行网络设置！");
            builder.setPositiveButton("确定",
                    new android.content.DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            try {
                                String sdkVersion = android.os.Build.VERSION.SDK;
                                if (Integer.valueOf(sdkVersion) > 10) {
                                    startActivity(new Intent(
                                            android.provider.Settings.ACTION_SETTINGS));
                                } else {
                                    startActivity(new Intent(
                                            android.provider.Settings.ACTION_WIRELESS_SETTINGS));
                                }
                                dialog.cancel();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    });
            builder.setNegativeButton("取消",
                    new android.content.DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            GuideActivity.this.finish();
                        }
                    });
            builder.show();
        }
        super.onStart();
    }
/*

    Handler mhandler = new Handler() {
        // 定义处理信息的方法
        public void handleMessage(Message msg) {
            switch (msg.what) {



                case 1:

                    Intent intent = new Intent(GuideActivity.this,
                            MainActivity.class);
                    startActivity(intent);
                    finish();
                break;

                case 2:
                    img2.setVisibility(View.VISIBLE);
                    img1.setVisibility(View.GONE);
                    mhandler.sendEmptyMessageDelayed(3, 2000);
                    break;
                case 3:
                    img3.setVisibility(View.VISIBLE);
                    img2.setVisibility(View.GONE);
                    mhandler.sendEmptyMessageDelayed(1, 2000);
                break;
                case 5:

                    break;
            }
            super.handleMessage(msg);
        }

    };
*/

}
