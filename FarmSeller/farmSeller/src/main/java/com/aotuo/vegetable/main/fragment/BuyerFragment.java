package com.aotuo.vegetable.main.fragment;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.aotcap.zxing.test.CaptureActivity;
import com.aotuo.vegetable.R;
import com.aotuo.vegetable.act.MainActivity;
import com.aotuo.vegetable.act.MyRecord;
import com.aotuo.vegetable.act.NewsMainActivity;
import com.aotuo.vegetable.act.NoCamera;
import com.aotuo.vegetable.dialog.EditDialog;
import com.aotuo.vegetable.entity.CityMessage;
import com.aotuo.vegetable.entity.ClassifyEntity;
import com.aotuo.vegetable.entity.MessageFlag;
import com.aotuo.vegetable.entity.OrderImgEntity;
import com.aotuo.vegetable.entity.OrderInfoEntity;
import com.aotuo.vegetable.entity.TransIDData;
import com.aotuo.vegetable.util.CommonTools;
import com.aotuo.vegetable.util.MyToast;
import com.aotuo.vegetable.util.NewsBroadcastReceiver;
import com.aotuo.vegetable.util.StaticDialog;
import com.aotuo.vegetable.util.StringUtils;
import com.aotuo.vegetable.view.MyScrollView;
import com.custom.nostra13.universalimageloader.core.ImageLoader;
import com.custom.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.util.HashMap;

/**
 * 首页
 *
 * @author 牛XX
 */
public class BuyerFragment extends Fragment implements OnClickListener {
    // private PullToRefreshView mPullToRefreshView;
    private final static int GET_ER_CODE = 15;
    private TextView location_address;// 地址
    private TextView newsFlags;// 是否有未读消息
    public ImageLoader imageLoader = ImageLoader.getInstance();
    private ClassifyEntity classicEntity;
    private RelativeLayout home_inform;
    private EditText editOrder, editIdenCode;
    private TextView name, allWeight, editPrice, editWeight, editTotal, editFreight;
    private TextView userNum;
    private Button toBuy;
    private Button snCode;
    private Button gCode;
    private Button idenCode;
    private ImageView goodsImg;
    private TextView selType;
    private RelativeLayout location;
    private String strOderCode = "erfwsf";
    private OrderImgEntity oie;
    private EditDialog editDialog;
    private View titleGap;
    private  TextView tv_nocamera;

    // 定位
    String cityName;
    MyScrollView homepage;
    Bundle savedInstanceState;
    View view;
    private SharedPreferences shared;

    // private Dialog myDialog;
    //CityDBDao dao;
    //String lat, lng;// 经纬度
    int area_id, city_id;// 省和城市id
    private SharedPreferences shared_key;
    String key;
    private SharedPreferences shared_city;
    private CityMessage locCity = new CityMessage();
    private NewsBroadcastReceiver receiver;
    private MessageFlag messageFlag = new MessageFlag();
    private OrderInfoEntity orderInfo = null;

    public BuyerFragment() {
        super();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.savedInstanceState = savedInstanceState;
        if (view == null) {
            view = inflater.inflate(R.layout.buyer_fragment, null);
            shared = getActivity().getSharedPreferences("shared",
                    Activity.MODE_PRIVATE);
            shared_key = getActivity().getSharedPreferences("shared_key",
                    Activity.MODE_PRIVATE);
            key = shared_key.getString("key", null);
            flag_key = key;
            shared_city = getActivity().getSharedPreferences("shared_city",
                    Activity.MODE_PRIVATE);
            imageLoader.init(ImageLoaderConfiguration
                    .createDefault(getActivity()));
            //dao = new CityDBDao(getActivity());
            initUI(view);
        }
        ViewGroup parent = (ViewGroup) view.getParent();
        if (parent != null) {
            parent.removeView(view);
        }

        return view;
    }

    /**
     * 初始化UI
     *
     * @param view
     */
    private void initUI(View view) {
        // myDialog = DialogUtil.createDialog(getActivity(), "");
        // myDialog.setCancelable(true);
        // myDialog.show();
        editOrder = (EditText) view.findViewById(R.id.editOrder);
        editIdenCode = (EditText) view.findViewById(R.id.editIdenCode);
        name = (TextView) view.findViewById(R.id.name);
        userNum = (TextView) view.findViewById(R.id.userNum);
        editFreight = (TextView) view.findViewById(R.id.editFreight);
        newsFlags = (TextView) view.findViewById(R.id.newsFlags);
        home_inform = (RelativeLayout) view.findViewById(R.id.home_inform);
        allWeight = (TextView) view.findViewById(R.id.allWeight);
        editPrice = (TextView) view.findViewById(R.id.editPrice);
        editWeight = (TextView) view.findViewById(R.id.editWeight);
        editTotal = (TextView) view.findViewById(R.id.editTotal);
        toBuy = (Button) view.findViewById(R.id.toBuy);
        snCode = (Button) view.findViewById(R.id.snCode);
        gCode = (Button) view.findViewById(R.id.gCode);
        idenCode = (Button) view.findViewById(R.id.idenCode);
        goodsImg = (ImageView) view.findViewById(R.id.goodsImg);
        selType = (TextView) view.findViewById(R.id.selType);
        location = (RelativeLayout) view.findViewById(R.id.location);
        location_address = (TextView) view.findViewById(R.id.location_address);
        tv_nocamera = (TextView) view.findViewById(R.id.tv_nocamera);

        tv_nocamera.setOnClickListener(this);
        location.setOnClickListener(this);
        toBuy.setOnClickListener(this);
        snCode.setOnClickListener(this);
        gCode.setOnClickListener(this);
        idenCode.setOnClickListener(this);
        home_inform.setOnClickListener(this);


        editDialog = new EditDialog(getActivity(), R.style.MyDialogStyle,
                "请输入交易密码", InputType.TYPE_TEXT_VARIATION_PASSWORD,
                new EditDialog.DialogListener() {
                    @Override
                    public void onclick(View view) {
                        switch (view.getId()){
                            case R.id.cannel:{
                                editDialog.dismiss();
                            }
                            break;
                            case R.id.ok:{
                                String pwd = editDialog.returnString();
                                if(StringUtils.isEmpty(pwd) || pwd.length() < 3){
                                    MyToast.showToast(getActivity(), "请输入正确交易密码！", 2);
                                } else {
                                    toPay(pwd);
                                }
                                editDialog.dismiss();
                            }
                            break;
                        }
                    }
                });

        titleGap = view.findViewById(R.id.titleGap);
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
        shared_key = getActivity().getSharedPreferences("shared_key",
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
        getActivity().registerReceiver(receiver, filter);
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
        getActivity().unregisterReceiver(receiver);
        receiver.setINews(null);
        receiver = null;
        super.onStop();
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if(!hidden)
            showNum();
    }

    @Override
    public void onResume() {
        super.onResume();
        showNum();
    }

    private void showNum(){
        userHandler.removeCallbacksAndMessages(null);
        String unum = CommonTools.getUserSn();
        if(unum != null && unum.length() > 6)
            unum = unum.substring(unum.length() - 6 , unum.length());
        else
            userHandler.sendEmptyMessageDelayed(1, 3000);
        userNum.setText(unum);
    }

    private Handler userHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            String unum = CommonTools.getUserSn();
            if(unum != null && unum.length() > 6) {
                unum = unum.substring(unum.length() - 6, unum.length());
                userNum.setText(unum);
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
            case R.id.location:// 选择城市

                break;

            case R.id.home_inform: {
                Intent intentN = new Intent(getActivity(),
                        NewsMainActivity.class);
                intentN.putExtra("key", key);
                intentN.putExtra("flag", messageFlag);
                startActivity(intentN);
            }
            break;
            case R.id.toBuy: {//付款
                if(CommonTools.getCheckTPWD(getActivity())){
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
                String eCode = editOrder.getText().toString();
                if (StringUtils.isEmpty(eCode)) {
                    MyToast.showToast(getActivity(), "请输入订单号！", 2);
                    return;
                }
                getMenuOrderCode(eCode);
            }
            break;
            case R.id.idenCode: {//获取验证码
                if (StringUtils.isEmpty(strOderCode)) {
                    MyToast.showToast(getActivity(), "请先获取订单信息！", 2);
                    return;
                }
                getIdenCode();
            }
            break;
            case R.id.gCode: {//扫描二维码获取订单信息
                Intent intent = new Intent(getActivity(), CaptureActivity.class);
                startActivityForResult(intent, GET_ER_CODE);

            }
            break;
            case R.id.tv_nocamera:
                startActivity(new Intent(getActivity(),NoCamera.class));
            default:
                break;
        }
    }

    /**Token,Num(临时订单号),RandomCode,TransPwd,BuyerDevice,
     * 参数：
     */
    private void toPay(String pwd) {
        idenCode.setClickable(false);
        HashMap<String, Object> param = new HashMap<String, Object>();
        param.put("Token", CommonTools.getToken(getActivity()));
        param.put("Num", strOderCode);
        param.put("RandomCode", editIdenCode.getText().toString());
        param.put("TransPwd", CommonTools.md5(pwd));
        param.put("BuyerDevice", CommonTools.getDeviceInfo(getActivity()));
        MainActivity.postRequest(2, sHandler, "/GoodsA/TransAdd", param);
    }

    /**
     * 参数：Token,Num(临时订单号)
     */
    private void getIdenCode() {
        idenCode.setClickable(false);
        HashMap<String, Object> param = new HashMap<String, Object>();
        param.put("Token", CommonTools.getToken(getActivity()));
        param.put("Num", strOderCode);
        MainActivity.postRequest(1, sHandler, "/GoodsA/TransCodeReSend", param);
    }

    /**
     * 手动获取订单号
     *
     * @param code
     */
    private void getMenuOrderCode(String code) {
		idenCode.setClickable(false);
		HashMap<String, Object> param = new HashMap<String, Object>();
		param.put("Token", CommonTools.getToken(getActivity()));
		param.put("Num", code);
		MainActivity.postRequest(3, sHandler, "/GoodsA/TransInfo", param);
    }

    private Handler sHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1: { // 获取验证码
                    if(msg.arg1 > 0) {
                        TransIDData tid = null;
                        try {
                            tid = new Gson().fromJson(msg.obj.toString(), TransIDData.class);
                        } catch (JsonSyntaxException e) {
                            e.printStackTrace();
                        }
                        if (tid != null) {
                            MyToast.showToast(getActivity(), "获取验证码成功！", 2);
                            delayHandler.removeCallbacksAndMessages(null);
                            code = 0;
                            delayHandler.sendEmptyMessageDelayed(12, 1000);
                            idenCode.setTextColor(getResources().getColor(R.color.gray));
                        } else {
                            idenCode.setText("获取验证码");
                            idenCode.setClickable(true);
                            idenCode.setTextColor(getResources().getColor(R.color.black));
                        }
                    } else {
                        idenCode.setClickable(true);
                        idenCode.setTextColor(getResources().getColor(R.color.black));
                        MyToast.showToast(getActivity(), "获取验证码失败！", 2);
                    }
                }
                break;
                case 2:{
                    if(msg.arg1 > 0){
                        clearShow();
                        MyToast.showToast(getActivity(), "付款成功！", 2);
                        Intent intent = new Intent(getActivity(), MyRecord.class);
                        intent.putExtra("type", "current");
                        startActivity(intent);
                    } else {
                        if(msg.obj.toString() != null){
                            if(msg.obj.toString().contains("不存在"))
                                clearShow();
                        }
                        MyToast.showToast(getActivity(), msg.obj.toString(), 2);
                    }
                }
                break;
                case 3:{
                    if(msg.arg1 > 0){
                        orderInfo = null;
                        try {
                            orderInfo = new Gson().fromJson(msg.obj.toString(), OrderInfoEntity.class);
                        } catch (JsonSyntaxException e) {
                            e.printStackTrace();
                        }
                        if(orderInfo!= null){
                            showOrder2();
                        }
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
                if (code < 60) {
                    code++;
                    delayHandler.sendEmptyMessageDelayed(12, 1000);
                    idenCode.setText("等待中..." + (60 - code));
                } else {
                    delayHandler.removeCallbacksAndMessages(null);
                    idenCode.setText("获取验证码");
                    idenCode.setClickable(true);
                    idenCode.setTextColor(getResources().getColor(R.color.black));
                }
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
            if(!StringUtils.isEmpty(qrCode)){
                String[] arr = qrCode.split(":");
                if(arr.length == 5){
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

            editOrder.setText(oie.getOrderCode());
            name.setText(oie.getGoodsTitle());
            editPrice.setText(oie.getPrice());
            editWeight.setText(oie.getWeight());
            editTotal.setText(oie.getTotalPrice());
            //editFreight.setText(oie.getFreight());
            //selType.setText("交易品种>" + oie.getClassXXX());
            //allWeight.setText(oie.getAllWeight());

            //FinalBitmap.create(getActivity()).display(goodsImg, FinalContent.FinalUrl + oie.getPic());
            strOderCode = oie.getOrderCode();
        }
    }

    private void showOrder2(){
        if(orderInfo != null){
            oie = new OrderImgEntity();
            oie.setPrice(orderInfo.getPrice());
            oie.setWeight(orderInfo.getWeight());
            oie.setTotalPrice(orderInfo.getSum());
            oie.setGoodsTitle(orderInfo.getGoodsTitle());
            //oie.setOrderCode(orderInfo.getNum());
            showOrder(oie);
        }
    }

    private void clearShow(){
        editPrice.setText("");
        editWeight.setText("");
        editTotal.setText("");
        editOrder.setText("");
        name.setText("");
        editFreight.setText("");
        strOderCode = "";
    }

    @Override
    public void onDestroy() {
        // 退出时销毁定位

        // myDialog.dismiss();
        super.onDestroy();
    }

}