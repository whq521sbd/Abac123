package com.aotuo.vegetable.act;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.aotcap.zxing.test.CaptureActivity;
import com.aotuo.vegetable.R;
import com.aotuo.vegetable.base.BaseActivity;
import com.aotuo.vegetable.dialog.EditDialog;
import com.aotuo.vegetable.entity.CityMessage;
import com.aotuo.vegetable.entity.ClassifyEntity;
import com.aotuo.vegetable.entity.LogInfoEntity;
import com.aotuo.vegetable.entity.MessageFlag;
import com.aotuo.vegetable.entity.OrderImgEntity;
import com.aotuo.vegetable.entity.OrderInfoEntity;
import com.aotuo.vegetable.util.CommonTools;
import com.aotuo.vegetable.util.MyToast;
import com.aotuo.vegetable.util.NewsBroadcastReceiver;
import com.aotuo.vegetable.util.StaticDialog;
import com.aotuo.vegetable.util.StringUtils;
import com.custom.nostra13.universalimageloader.core.ImageLoader;
import com.custom.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by 牛XX on 2016/9/23.
 */

public class LocalBuyActivity extends BaseActivity implements View.OnClickListener {
    // private PullToRefreshView mPullToRefreshView;
    private final static int GET_ER_CODE = 15;
    private TextView location_address;// 地址
    private TextView newsFlags;// 是否有未读消息
    public ImageLoader imageLoader = ImageLoader.getInstance();
    private ClassifyEntity classicEntity;
    private RelativeLayout home_inform;
    private RelativeLayout back;
    private LinearLayout menuCode0;
    private EditText editIdenCode;
    private TextView name, allWeight, editPrice, editWeight, editTotal, editFreight;
    private Button toBuy;
    private Button gCode;
    private ImageView goodsImg;
    private TextView selType;
    private RelativeLayout location;
    private String strOderCode = "erfwsf";
    private OrderImgEntity oie;
    private EditDialog editDialog;
    private View titleGap;

    private Bundle savedInstanceState;
    private SharedPreferences shared;
    private SharedPreferences shared_key;
    private String key;
    private SharedPreferences shared_city;
    private CityMessage locCity = new CityMessage();
    private NewsBroadcastReceiver receiver;
    private MessageFlag messageFlag = new MessageFlag();
    private OrderInfoEntity orderInfo = null;
    private TextView tv_nocamera;
    private  Button toreturn;
    private  View selectableline;
    private LogInfoEntity lie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.buyer_fragment);
        shared = getSharedPreferences("shared",
                Activity.MODE_PRIVATE);
        shared_key = getSharedPreferences("shared_key",
                Activity.MODE_PRIVATE);
        key = shared_key.getString("key", null);
        flag_key = key;
        shared_city = getSharedPreferences("shared_city",
                Activity.MODE_PRIVATE);
        imageLoader.init(ImageLoaderConfiguration
                .createDefault(this));
        //getDistance();
        initUI();
    }

    /**
     * 初始化UI
     *
     * @param
     */
    private void initUI() {
        // myDialog = DialogUtil.createDialog(getActivity(), "");
        // myDialog.setCancelable(true);
        // myDialog.show();
        editIdenCode = (EditText) findViewById(R.id.editIdenCode);
        name = (TextView) findViewById(R.id.name);
        editFreight = (TextView) findViewById(R.id.editFreight);
        newsFlags = (TextView) findViewById(R.id.newsFlags);
        home_inform = (RelativeLayout) findViewById(R.id.home_inform);
        allWeight = (TextView) findViewById(R.id.allWeight);
        editPrice = (TextView) findViewById(R.id.editPrice);
        editWeight = (TextView) findViewById(R.id.editWeight);
        editTotal = (TextView) findViewById(R.id.editTotal);
        toBuy = (Button) findViewById(R.id.toBuy);
        gCode = (Button) findViewById(R.id.gCode);
        goodsImg = (ImageView) findViewById(R.id.goodsImg);
        selType = (TextView) findViewById(R.id.selType);
        back = (RelativeLayout) findViewById(R.id.back);
        menuCode0 = (LinearLayout) findViewById(R.id.menuCode0);
        location = (RelativeLayout) findViewById(R.id.location);
        location_address = (TextView) findViewById(R.id.location_address);
        tv_nocamera = (TextView) findViewById(R.id.tv_nocamera);
        toreturn = (Button) findViewById(R.id.toreturn);
        selectableline = findViewById(R.id.selectableline);
        location.setOnClickListener(this);
        toBuy.setOnClickListener(this);
        gCode.setOnClickListener(this);
        home_inform.setOnClickListener(this);
        back.setOnClickListener(this);
        tv_nocamera.setOnClickListener(this);
        toreturn.setOnClickListener(this);

        editDialog = new EditDialog(this, R.style.MyDialogStyle,
                "请输入交易密码", InputType.TYPE_TEXT_VARIATION_PASSWORD,
                new EditDialog.DialogListener() {
                    @Override
                    public void onclick(View view) {
                        switch (view.getId()) {
                            case R.id.cannel: {
                                editDialog.dismiss();
                            }
                            break;
                            case R.id.ok: {
                                String pwd = editDialog.returnString();
                                if (StringUtils.isEmpty(pwd) || pwd.length() < 3) {
                                    MyToast.showToast(LocalBuyActivity.this, "请输入正确交易密码！", 2);
                                } else {
                                    toPay(pwd);
                                }
                                editDialog.dismiss();
                            }
                            break;
                        }
                    }
                });

        titleGap = findViewById(R.id.titleGap);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            titleGap.setVisibility(View.VISIBLE);
        } else {
            titleGap.setVisibility(View.GONE);
        }
    }

    private String stringFromat(String s) {
        if (StringUtils.isEmpty(s)) {
            return "0.00";
        }
        String ts = s.replace(".", "A");
        String[] ss = ts.split("A");
        if (ss.length == 1) {
            return ss[0] + ".00";
        } else {
            if (ss[1].length() == 1) {
                return s + "0";
            } else if (ss[1].length() == 2) {
                return s;
            } else {
                return ss[0] + "." + ss[1].substring(0, 2);
            }
        }
    }

    String flag_key;
    int flag_read = 0;

    @Override
    public void onStart() {
        super.onStart();
        shared_key = getSharedPreferences("shared_key",
                Activity.MODE_PRIVATE);
        key = shared_key.getString("key", null);
        if (flag_read == 0) {
            if (!StringUtils.isEmpty(key)) {
                if (StringUtils.isEmpty(flag_key)) {
                    flag_key = key;
                    // initdate();
                } else if (!key.equals(flag_key)) {
                    flag_key = key;
                    // initdate();
                }
            } else {
                flag_key = key;
            }
        }
        IntentFilter filter = new IntentFilter();
        filter.addAction(NewsBroadcastReceiver.NEWS_ACTION);
        receiver = new NewsBroadcastReceiver();
        receiver.setINews(new NewsBroadcastReceiver.INews() {
            @Override
            public void update() {//接收消息
                // TODO Auto-generated method stub

            }

            @Override
            public void update(String msg) {
                messageFlag.clear();
                if ("hasNews".equals(msg)) {
                    messageFlag.setHasNews(true);
                } else if ("hasMsg".equals(msg)) {
                    messageFlag.setHasMsg(true);
                } else if ("hasMess".equals(msg)) {
                    messageFlag.setHasMess(true);
                }
                if (messageFlag.isHasNews())
                    newHandler.sendEmptyMessage(1);
                else if (messageFlag.isHasMsg())
                    newHandler.sendEmptyMessage(1);
                else if (messageFlag.isHasMess())
                    newHandler.sendEmptyMessage(1);
                else
                    newHandler.sendEmptyMessage(2);
            }
        });
        registerReceiver(receiver, filter);
    }

    private Handler newHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1: {//有消息
                    newsFlags.setVisibility(View.VISIBLE);
                }
                break;
                case 2: {//无消息
                    newsFlags.setVisibility(View.GONE);
                }
                break;
            }
        }
    };

    @Override
    public void onStop() {
        // TODO Auto-generated method stub
        unregisterReceiver(receiver);
        receiver.setINews(null);
        receiver = null;
        super.onStop();
    }

    @Override
    public void onResume() {
        super.onResume();
        showNum();
    }

    private void showNum() {
        userHandler.removeCallbacksAndMessages(null);
        String unum = CommonTools.getUserSn();
        if (unum != null && unum.length() > 6)
            unum = unum.substring(unum.length() - 6, unum.length());
        else
            userHandler.sendEmptyMessageDelayed(1, 3000);
//        userNum.setText(unum);
    }

    private Handler userHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            String unum = CommonTools.getUserSn();
            if (unum != null && unum.length() > 6) {
                unum = unum.substring(unum.length() - 6, unum.length());
//                userNum.setText(unum);
                userHandler.removeCallbacksAndMessages(null);
            } else
                userHandler.sendEmptyMessageDelayed(1, 3000);
        }
    };

    /**
     * 点击时间
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                finish();
                break;

            case R.id.home_inform: {
                Intent intentN = new Intent(LocalBuyActivity.this,
                        NewsMainActivity.class);
                intentN.putExtra("key", key);
                intentN.putExtra("flag", messageFlag);
                startActivity(intentN);
            }
            break;
            case R.id.toBuy: {//付款
                float t = -1;
                try {
                    t = Float.parseFloat(oie.getTotalPrice());
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
                if (t > 0 && CommonTools.getSmsSw(LocalBuyActivity.this)) {
                    if (t > CommonTools.getSmsLimit(LocalBuyActivity.this)) {
                        String str = editIdenCode.getText().toString();
                        if(!StringUtils.isEmpty(str)){
                            MyToast.showToast(LocalBuyActivity.this, "请输入短信验证码！", 2);
                            return;
                        }
                    }
                }
                if (CommonTools.getCheckTPWD(LocalBuyActivity.this)) {
                    StaticDialog sd = new StaticDialog();
                    sd.init_dialog(editDialog);
                    editDialog.clearEdit();
                } else {
                    toPay("");
                }
            }
            break;
            case R.id.snCode: {//手动用订单号获取订单信息
                //editOrder.setText("201609051450056284RF");
//                String eCode = editOrder.getText().toString();
//                if (StringUtils.isEmpty(eCode)) {
//                    MyToast.showToast(LocalBuyActivity.this, "请输入订单号！", 2);
//                    return;
//                }
//                getMenuOrderCode(eCode);
            }
            break;
            case R.id.idenCode: {//获取验证码
                if (StringUtils.isEmpty(strOderCode)) {
                    MyToast.showToast(LocalBuyActivity.this, "请先获取订单信息！", 2);
                    return;
                }
                getIdenCode();
            }
            break;
            case R.id.gCode: {//扫描二维码获取订单信息
                Intent intent = new Intent(LocalBuyActivity.this, CaptureActivity.class);
                startActivityForResult(intent, GET_ER_CODE);
            }
            break;
            //没有摄像头
            case R.id.tv_nocamera:
                startActivityForResult(new Intent(LocalBuyActivity.this, NoCamera.class), 65);
                break;
            case R.id.toreturn:
                finish();
                break;
            default:
                break;
        }
    }

    /**
     * Token,Num(临时订单号),RandomCode,TransPwd,BuyerDevice,
     * Token,Num(临时订单号),RandomCode,TransPwd,BuyerDevice,LogDis,LogMoney,LogRate,TotalMoney
     * 参数：
     */
    private void toPay(String pwd) {
        HashMap<String, Object> param = new HashMap<String, Object>();
        param.put("Token", CommonTools.getToken(this));
        param.put("Num", strOderCode);
        param.put("RandomCode", editIdenCode.getText().toString());
        param.put("TransPwd", CommonTools.md5(pwd));
        param.put("BuyerDevice", CommonTools.getDeviceInfo(this));
        param.put("LogDis", "0.00");
        param.put("LogRate", "0.00");
        param.put("LogMoney", "0.00");
        param.put("TotalMoney", editTotal.getText().toString());
        MainActivity.postRequest(2, sHandler, "/GoodsA/TransAdd", param);
    }

    /**
     * 参数：Token,Num(临时订单号)
     */
    private void getIdenCode() {
        HashMap<String, Object> param = new HashMap<String, Object>();
        param.put("Token", CommonTools.getToken(this));
        param.put("Num", strOderCode);
        MainActivity.postRequest(1, sHandler, "/GoodsA/TransCodeReSend", param);
    }

    /**
     * 手动获取订单号
     *
     * @param code
     */
    private void getMenuOrderCode(String code) {
        HashMap<String, Object> param = new HashMap<String, Object>();
        param.put("Token", CommonTools.getToken(this));
        param.put("Num", code);
        MainActivity.postRequest(3, sHandler, "/GoodsA/TransInfo", param);
    }

    /**
     * 获取买家距离
     */
    private void getDistance() {

        String ms = CommonTools.getUserSn();
        HashMap<String, Object> param = new HashMap<String, Object>();
        param.put("Token", CommonTools.getToken(this));
        param.put("BuyerNum", CommonTools.getUserSn());
        MainActivity.postRequest(4, sHandler, "/GoodsA/LogInfo", param);
    }

    private Handler sHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1: { // 获取验证码
                    if (msg.arg1 > 0) {
//                        TransIDData tid = null;
//                        try {
//                            tid = new Gson().fromJson(msg.obj.toString(), TransIDData.class);
//                        } catch (JsonSyntaxException e) {
//                            e.printStackTrace();
//                        }
//                        if (tid != null) {
//                            //MyToast.showToast(LocalBuyActivity.this, "获取验证码成功！", 2);
//                            //delayHandler.removeCallbacksAndMessages(null);
//                            //code = 0;
//                            //delayHandler.sendEmptyMessageDelayed(12, 1000);
////                            idenCode.setTextColor(getResources().getColor(R.color.gray));
//                        } else {
////                            idenCode.setText("获取验证码");
////                            idenCode.setClickable(true);
////                            idenCode.setTextColor(getResources().getColor(R.color.black));
//                        }
                    } else {
//                        idenCode.setClickable(true);
//                        idenCode.setTextColor(getResources().getColor(R.color.black));
                        //MyToast.showToast(LocalBuyActivity.this, "获取验证码失败！", 2);
                    }
                }
                break;
                case 2: {
                    if (msg.arg1 > 0) {
                        clearShow();
                        MyToast.showToast(LocalBuyActivity.this, "付款成功！", 2);
                        Intent intent = new Intent(LocalBuyActivity.this, PayInfoActivity.class);
                        finish();
                        if(!StringUtils.isEmpty(msg.obj.toString())){
                            ArrayList<String> ls = new ArrayList<String>();
                            ls.add(msg.obj.toString());
                            intent.putStringArrayListExtra("num", ls);
                            startActivity(intent);
                        }
                    } else {
                        if (msg.obj.toString() != null) {
                            if (msg.obj.toString().contains("不存在"))
                                clearShow();
                        }
                        MyToast.showToast(LocalBuyActivity.this, msg.obj.toString(), 2);
                    }
                }
                break;
                case 3: {
                    if (msg.arg1 > 0) {
                        orderInfo = null;
                        try {
                            orderInfo = new Gson().fromJson(msg.obj.toString(), OrderInfoEntity.class);
                        } catch (JsonSyntaxException e) {
                            e.printStackTrace();
                        }
                        if (orderInfo != null) {
                            showOrder2();
                        }
                    }
                }
                break;
                case 4: {//返回:new { LogDis="",LogRate=""} });
                    lie = null;
                    try {
                        lie = new Gson().fromJson(msg.obj.toString(), LogInfoEntity.class);
                    } catch (JsonSyntaxException e) {
                        e.printStackTrace();
                    }
                    if (lie != null) {
                    }
                }
                break;
            }
        }
    };

    private int code;
    private Handler delayHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 12) {
//                if (code < 60) {
//                    code++;
//                    delayHandler.sendEmptyMessageDelayed(12, 1000);
//                    idenCode.setText("等待中..." + (60 - code));
//                } else {
//                    delayHandler.removeCallbacksAndMessages(null);
//                    idenCode.setText("获取验证码");
//                    idenCode.setClickable(true);
//                    idenCode.setTextColor(getResources().getColor(R.color.black));
//                }
            }
        }
    };

    /**
     * 回调数据
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (GET_ER_CODE == requestCode
                && resultCode == Activity.RESULT_OK) {
            String qrCode = data.getStringExtra("qr_code");
            oie = null;
            if (!StringUtils.isEmpty(qrCode)) {
                String[] arr = qrCode.split(":");
                if (arr.length == 5) {
                    //orderCode+":"+goodsTitle+":"+price+":"+weight+":"+totalPrice;
                    oie = new OrderImgEntity();
                    oie.setOrderCode(arr[0]);
                    oie.setGoodsTitle(arr[1]);
                    oie.setPrice(arr[2]);
                    oie.setWeight(arr[3]);
                    oie.setTotalPrice(arr[4]);
                    //oie.setBuyNum(arr[5]);
                    //oie.setFreight(arr[5]);
                }
            }
            showOrder(oie);
            float t = -1;
            try {
                t = Float.parseFloat(oie.getTotalPrice());
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
            if (t > 0) {
                if (t > CommonTools.getSmsLimit(LocalBuyActivity.this)) {
                    if(CommonTools.getSmsSw(LocalBuyActivity.this)){
                        menuCode0.setVisibility(View.VISIBLE);
                        selectableline.setVisibility(View.VISIBLE);
                        getIdenCode();
                    } else
                        menuCode0.setVisibility(View.GONE);
                    selectableline.setVisibility(View.GONE);
                } else {
                    menuCode0.setVisibility(View.GONE);
                    selectableline.setVisibility(View.GONE);
                }
            }
        } else if (requestCode == 65 && resultCode == RESULT_OK) {
            if(data != null){
                orderInfo = (OrderInfoEntity) data.getSerializableExtra("oie");
                showOrder2();

                float t = -1;
                try {
                    t = Float.parseFloat(oie.getTotalPrice());
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
                if (t > 0) {
                    if (t > CommonTools.getSmsLimit(LocalBuyActivity.this)) {
                        if(CommonTools.getSmsSw(LocalBuyActivity.this)){
                            menuCode0.setVisibility(View.VISIBLE);
                            getIdenCode();
                        } else
                            menuCode0.setVisibility(View.GONE);
                    } else {
                        menuCode0.setVisibility(View.GONE);
                    }
                }
            }
        }
    }

    private void showOrder(OrderImgEntity oie) {
        if (oie == null) {
            clearShow();
        } else {
//            String esn = CommonTools.getUserSn();
//            if(esn != null && !esn.equals(oie.getBuyNum())){
//                MyToast.showToast(getActivity(), "无权购买此商品！", 2);
//                return ;
//            }

//            editOrder.setText(oie.getOrderCode());
            name.setText(oie.getGoodsTitle());
            editPrice.setText(oie.getPrice());
            editWeight.setText(oie.getWeight());
            editTotal.setText(oie.getTotalPrice());
            //editFreight.setText(oie.getFreight());
            //selType.setText("交易品种>" + oie.getClassXXX());
            //allWeight.setText(oie.getAllWeight());


            strOderCode = oie.getOrderCode();
        }
    }

    private void showOrder2() {
        if (orderInfo != null) {
            oie = new OrderImgEntity();
            oie.setPrice(orderInfo.getPrice());
            oie.setWeight(orderInfo.getWeight());
            oie.setTotalPrice(orderInfo.getSum());
            oie.setGoodsTitle(orderInfo.getGoodsTitle());
            oie.setOrderCode(orderInfo.getNum());
            showOrder(oie);
        } else {
            clearShow();
        }
    }

    private void clearShow() {
        editPrice.setText("");
        editWeight.setText("");
        editTotal.setText("");
       // editOrder.setText("");
        name.setText("");
        editFreight.setText("");
        strOderCode = "";
        menuCode0.setVisibility(View.GONE);
    }

    @Override
    public void onDestroy() {
        // 退出时销毁定位

        // myDialog.dismiss();
        super.onDestroy();
    }

}
