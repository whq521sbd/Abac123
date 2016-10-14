package com.aotuo.vegetable.act;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.text.Editable;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextWatcher;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.aotuo.vegetable.R;
import com.aotuo.vegetable.adapter.GuidePagerAdapter;
import com.aotuo.vegetable.base.BaseActivity;
import com.aotuo.vegetable.entity.CityMessage;
import com.aotuo.vegetable.entity.ClassifyMessage;
import com.aotuo.vegetable.entity.GoodsA;
import com.aotuo.vegetable.entity.LogInfoEntity;
import com.aotuo.vegetable.entity.MessageFlag;
import com.aotuo.vegetable.entity.OrderImgEntity;
import com.aotuo.vegetable.entity.OrderTempData;
import com.aotuo.vegetable.sqlite.ClassifyDBDao;
import com.aotuo.vegetable.util.CommonTools;
import com.aotuo.vegetable.util.FinalContent;
import com.aotuo.vegetable.util.MyToast;
import com.aotuo.vegetable.util.NewsBroadcastReceiver;
import com.aotuo.vegetable.util.StringUtils;
import com.custom.nostra13.universalimageloader.core.ImageLoader;
import com.custom.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import net.tsz.afinal.FinalBitmap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by 牛XX on 2016/9/23.
 */

public class LocalSellActivity extends BaseActivity implements View.OnClickListener {
    // private PullToRefreshView mPullToRefreshView;
    private final static int GET_CLASSIC = 13;
    private TextView location_address;// 地址
    private TextView newsFlags;// 是否有未读消息
    public ImageLoader imageLoader = ImageLoader.getInstance();
    private EditText editWeight, editPrice, editNum;
    private TextView name, allWeight, introduction, editTotal, editFreight, distance;
    private TextView atDistance, atFax;
    private Button toCreate;
    private ImageView goodsImg;
    private TextView selType, txtGoodsSort;
    private LinearLayout goodsInfo;
    private LinearLayout exDistance;
    private RelativeLayout goodsimagArea;
    private RelativeLayout back;
    private RelativeLayout location;
    private RelativeLayout home_inform;
    private GoodsA myGoodsA;
    private ClassifyDBDao classicDao;
    private ClassifyMessage classicMessage;
    private OrderTempData otd;
    private MessageFlag messageFlag = new MessageFlag();
    private SharedPreferences shared;
    int area_id, city_id;// 省和城市id
    private SharedPreferences shared_key;
    private String key;
    private SharedPreferences shared_city;
    private SharedPreferences shared_price;
    private CityMessage locCity = new CityMessage();
    private NewsBroadcastReceiver receiver;
    private String orderStr;
    private LogInfoEntity lie;
    private String buyNum;
    private View titleGap;
    private ViewPager vp_Adviewpager;
    private View ad1, ad2, ad3;
    private List<View> list;
    private double mPrice = 0;
    private double mWeight = 0;
    private Display mDisplay;
    private Button toback;


    /*
    * 广告轮播
    * */

    private final int AUTO_MSG = 1;
    private final int HANDLE_MSG = AUTO_MSG + 1;
    private static final int PHOTO_CHANGE_TIME = 5000;//定时变量
    private int index = 0;

    public int indexadd(int i) {//递归方法，循环播放
        if (index == 2) {
            index = 0;
            return index;
        } else {

            index++;
        }
        return index;
    }

    private Handler mHandler = new Handler() {

        public void handleMessage(Message msg) {
            switch (msg.what) {
                case AUTO_MSG:

                    vp_Adviewpager.setCurrentItem(indexadd(0));//收到消息后设置当前要显示的图片


                    mHandler.sendEmptyMessageDelayed(AUTO_MSG, PHOTO_CHANGE_TIME);
                    break;
                case HANDLE_MSG:
                    mHandler.sendEmptyMessageDelayed(AUTO_MSG, PHOTO_CHANGE_TIME);
                    break;
                default:
                    break;
            }
        }

        ;
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.deal_fragment);
        shared = getSharedPreferences("shared",
                Activity.MODE_PRIVATE);
        shared_key = getSharedPreferences("shared_key",
                Activity.MODE_PRIVATE);
        key = shared_key.getString("key", null);
        flag_key = key;
        shared_city = getSharedPreferences("shared_city",
                Activity.MODE_PRIVATE);
        shared_price = getSharedPreferences("shared_price",
                Activity.MODE_PRIVATE);
        imageLoader.init(ImageLoaderConfiguration
                .createDefault(this));
        classicDao = new ClassifyDBDao(this);
        initUI();

        initData();

        LayoutInflater layoutInflater = getLayoutInflater();
        ad1 = layoutInflater.inflate(R.layout.ad1, null);
        ad2 = layoutInflater.inflate(R.layout.ad2, null);
        ad3 = layoutInflater.inflate(R.layout.ad3, null);
        list = new ArrayList<View>();

        list.add(ad1);
        list.add(ad2);
        list.add(ad3);

        GuidePagerAdapter adapter = new GuidePagerAdapter((ArrayList<View>) list);
        vp_Adviewpager.setAdapter(adapter);


        mHandler.sendEmptyMessageDelayed(AUTO_MSG, PHOTO_CHANGE_TIME);

    }

    /**
     * 初始化UI
     *
     */
    private void initUI() {
        // myDialog = DialogUtil.createDialog(getActivity(), "");
        // myDialog.setCancelable(true);
        // myDialog.show();
        goodsInfo = (LinearLayout) findViewById(R.id.goodsInfo);
        exDistance = (LinearLayout) findViewById(R.id.exDistance);
        editFreight = (EditText) findViewById(R.id.editFreight);
        editPrice = (EditText) findViewById(R.id.editPrice);
        editNum = (EditText) findViewById(R.id.editNum);
        newsFlags = (TextView) findViewById(R.id.newsFlags);
        atFax = (TextView) findViewById(R.id.atFax);
        atDistance = (TextView) findViewById(R.id.atDistance);
        name = (TextView) findViewById(R.id.name);
        txtGoodsSort = (TextView) findViewById(R.id.txtGoodsSort);
        allWeight = (TextView) findViewById(R.id.allWeight);
        introduction = (TextView) findViewById(R.id.introduction);
        editWeight = (EditText) findViewById(R.id.editWeight);
        editTotal = (TextView) findViewById(R.id.editTotal);
        toCreate = (Button) findViewById(R.id.toCreate);
        goodsImg = (ImageView) findViewById(R.id.goodsImg);
        selType = (TextView) findViewById(R.id.selType);
        distance = (TextView) findViewById(R.id.distance);
        goodsimagArea = (RelativeLayout) findViewById(R.id.goodsimagArea);
        location = (RelativeLayout) findViewById(R.id.location);
        home_inform = (RelativeLayout) findViewById(R.id.home_inform);
        back = (RelativeLayout) findViewById(R.id.back);
        location_address = (TextView) findViewById(R.id.location_address);
        toback = (Button) findViewById(R.id.toback);

        //广告轮播
        vp_Adviewpager = (ViewPager) findViewById(R.id.vp_Adviewpager);
        goodsimagArea.setOnClickListener(this);
        //location.setOnClickListener(this);
        goodsInfo.setOnClickListener(this);
        home_inform.setOnClickListener(this);
        distance.setOnClickListener(this);
        back.setOnClickListener(this);
        toback.setOnClickListener(this);

        editWeight.setFilters(new InputFilter[]{lengthfilter});
        editWeight.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (myGoodsA == null) {
                    editTotal.setText("0.00");
                    return;
                }
                String price = editPrice.getText().toString();
                String weight = editWeight.getText().toString();

                weight = stringFromat(weight);
                try {
                    mPrice = 0;
                    mWeight = 0;
                    double mFreight = 0;
                    double mTotal = 0;
                    if (StringUtils.isEmpty(price))
                        mPrice = 0;
                    else
                        mPrice = Double.parseDouble(price);
                    if (StringUtils.isEmpty(weight))
                        mWeight = 0;
                    else
                        mWeight = Double.parseDouble(weight);

                    if (lie != null)
                        mFreight = 0;//mFreight = lie.getLogDis() * lie.getLogRate() * mWeight;
                    else
                        mFreight = 0;
                    String sFreight = String.format("%.2f", mFreight);
                    editFreight.setText(sFreight);

                } catch (NumberFormatException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });
        editPrice.setFilters(new InputFilter[]{lengthfilter});
        editPrice.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (myGoodsA == null) {
                    editTotal.setText("0.00");
                    return;
                }
                String price = editPrice.getText().toString();
                String weight = editWeight.getText().toString();
                String freight = editFreight.getText().toString();
                price = stringFromat(price);
                try {
                    mPrice = 0;
                    mWeight = 0;
                    double mFreight = 0;
                    double mTotal = 0;
                    if (StringUtils.isEmpty(price))
                        mPrice = 0;
                    else
                        mPrice = Double.parseDouble(price);
                    if (StringUtils.isEmpty(weight))
                        mWeight = 0;
                    else
                        mWeight = Double.parseDouble(weight);
                    if (StringUtils.isEmpty(freight))
                        mFreight = 0;
                    else
                        mFreight = 0;//mFreight = Double.parseDouble(freight);
                    mTotal = mPrice * mWeight + mFreight;

                    String tempTotal = String.format("%.2f", mTotal);
                    editTotal.setText(tempTotal);
                } catch (NumberFormatException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });
        editFreight.setFilters(new InputFilter[]{lengthfilter});
        editFreight.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (myGoodsA == null) {
                    editTotal.setText("0.00");
                    return;
                }
                String price = editPrice.getText().toString();
                String weight = editWeight.getText().toString();
                String freight = editFreight.getText().toString();
                freight = stringFromat(freight);
                try {
                    mPrice = 0;
                    mWeight = 0;
                    double mFreight = 0;
                    double mTotal = 0;
                    if (StringUtils.isEmpty(price))
                        mPrice = 0;
                    else
                        mPrice = Double.parseDouble(price);
                    if (StringUtils.isEmpty(weight))
                        mWeight = 0;
                    else
                        mWeight = Double.parseDouble(weight);
                    if (StringUtils.isEmpty(freight))
                        mFreight = 0;
                    else
                        mFreight = 0;//mFreight = Double.parseDouble(freight);
                    mTotal = mPrice * mWeight + mFreight;

                    String tempTotal = String.format("%.2f", mTotal);
                    editTotal.setText(tempTotal);
                } catch (NumberFormatException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });

        toCreate.setOnClickListener(this);

        titleGap = findViewById(R.id.titleGap);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            titleGap.setVisibility(View.VISIBLE);
        } else {
            titleGap.setVisibility(View.GONE);
        }
    }

    private void initData() {
        if(CommonTools.getUserSn() != null && CommonTools.getUserSn().equals(shared_price.getString("sellSn", ""))){
            String sWeight = shared_price.getString("weight", "");
            String sTotal = shared_price.getString("total", "");
            String sGoodsA = shared_price.getString("GoodsA", "");
            String sOrderCode = shared_price.getString("orderCode", "");
            myGoodsA = null;
            if (!StringUtils.isEmpty(sGoodsA)) {
                try {
                    myGoodsA = new Gson().fromJson(sGoodsA, GoodsA.class);
                } catch (JsonSyntaxException e) {
                    e.printStackTrace();
                }
            }
            if (myGoodsA != null) {
                classicMessage = classicDao.getClassify(myGoodsA.getClassXXXID());
                if (classicMessage == null) {
                    myGoodsA = null;
                    return;
                } else {
                    initUIData(sWeight, sTotal);
                }
            } else {
                //createEmptCodeImg();
            }
            if (!StringUtils.isEmpty(sOrderCode)) {
                otd = new OrderTempData();
                otd.setTransID(sOrderCode);
            }
        }
    }

    private void initUIData(String sWeight, String sTotal) {
        classicMessage = classicDao.getClassify(myGoodsA.getClassXXXID());
        if (classicMessage == null)
            return;
        selType.setText("交易品种>" + classicMessage.getGc_name());
        name.setText(myGoodsA.getGoodsName());
        allWeight.setText(myGoodsA.getQuantity());
        introduction.setText(myGoodsA.getIntroduction());
        editPrice.setText(myGoodsA.getPrice());
        editWeight.setText(sWeight);
        editTotal.setText(sTotal);

        //ClassXXX,GoodsTitle,Price,SellerDevice,TotalPrice,Weight
        String gPic = classicMessage.getImage();
        if (!StringUtils.isEmpty(myGoodsA.getPic())) {
            gPic = myGoodsA.getPic();
        }
        if (myGoodsA != null && otd != null) {
            if (!StringUtils.isEmpty(orderStr) && orderStr.contains(myGoodsA.getClassXXXID())) {
                orderStr = createCodeImg(myGoodsA.getClassXXXID(),
                        myGoodsA.getGoodsName(),
                        myGoodsA.getPrice(),
                        CommonTools.getDeviceInfo(this),
                        sTotal, sWeight, gPic, myGoodsA.getQuantity(), otd.getTransID()/*,
                        buyNum, editFreight.getText().toString()*/);
            }
        }

        if (myGoodsA != null) {
            txtGoodsSort.setText(classicMessage.getGc_name());
            //txtGoodsSort.setVisibility(View.GONE);
            FinalBitmap.create(this).display(goodsImg, FinalContent.FinalUrl + myGoodsA.getPic());
        } else {
            txtGoodsSort.setText("请选择交易品种！");
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

            }

            @Override
            public void update(String msg) {//接收消息
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
        this.registerReceiver(receiver, filter);
    }

    @Override
    public void onStop() {
        // TODO Auto-generated method stub
        this.unregisterReceiver(receiver);
        receiver.setINews(null);
        receiver = null;
        super.onStop();
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
    public void onResume() {
        super.onResume();
        String cityname = shared_city.getString("cityName", null);
        area_id = Integer.parseInt(shared_city.getString("area_id", "2513"));
        city_id = Integer.parseInt(shared_city.getString("city_id", "223"));

        if (!StringUtils.isEmpty(cityname))
            location_address.setText(cityname);
    }

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
                Intent intentN = new Intent(LocalSellActivity.this,
                        NewsMainActivity.class);
                intentN.putExtra("key", key);
                intentN.putExtra("flag", messageFlag);
                startActivity(intentN);
            }
            break;
            case R.id.toCreate: {
                long ss = System.currentTimeMillis();
                if (myGoodsA == null) {
                    MyToast.showToast(LocalSellActivity.this, "请选择商品！", 3);
                    return;
                }
                String weight = editWeight.getText().toString();
                if (StringUtils.isEmpty(weight)) {
                    MyToast.showToast(LocalSellActivity.this, "请输入购买重量！", 3);
                    return;
                }
                String p = editTotal.getText().toString();
                double d = 0;
                try {
                    d = Double.parseDouble(p);
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
                if(d == 0){
                    MyToast.showToast(LocalSellActivity.this, "请确认购买价格及重量！", 3);
                    return;
                }

                toTransTemp();
            }
            break;
            case R.id.goodsInfo:
            case R.id.goodsimagArea: {
                Intent intent = new Intent(LocalSellActivity.this, GoodsList.class);
                intent.putExtra("mode", "select");
                startActivityForResult(intent, GET_CLASSIC);
            }
            break;
            case R.id.distance: {
                getDistance();
            }
            break;
            case R.id.toback:
                finish();
                break;
            default:
                break;
        }

    }

    /**
     * 获取买家距离
     */
    private void getDistance() {
        String s = editNum.getText().toString();
        if (s.length() == 0) {
            MyToast.showToast(this, "请输入账号尾号！", 2);
            return;
        }
        while (s.length() < 6)
            s = "0" + s;
        String ms = CommonTools.getUserSn();
        buyNum = ms.substring(0, 6) + s;
        HashMap<String, Object> param = new HashMap<String, Object>();
        param.put("Token", CommonTools.getToken(this));
        param.put("BuyerNum", buyNum);
        MainActivity.postRequest(3, sHandler, "/GoodsA/LogInfo", param);
    }

    /**
     * 参数：Token,GoodsID,GoodsTitle,
     * SellerDevice,Weight,Price,
     * GoodsMoney(商品金额),
     * LogDis(运输距离),
     * LogRate(运输税率),
     * LogMoney(运输金额),
     * TotalMoney(总金额)
     * 参数：
     */
    private void toTransTemp() {
        HashMap<String, Object> param = new HashMap<String, Object>();
        param.put("Token", CommonTools.getToken(this));
        param.put("GoodsID", myGoodsA.getGoodsID());
        param.put("GoodsTitle", myGoodsA.getGoodsName());
        param.put("SellerDevice", CommonTools.getDeviceInfo(this));
        param.put("Weight", editWeight.getText().toString());
        param.put("Price", editPrice.getText().toString());

        param.put("GoodsMoney", String.format("%.2f", mPrice * mWeight));
/*        if (lie == null) {
            param.put("LogDis", "0");
            param.put("LogRate", "0");
            String log = editFreight.getText().toString();
            if (StringUtils.isEmpty(log))
                param.put("LogMoney", "0");
            else
                param.put("LogMoney", log);
        } else {
            param.put("LogDis", "" + lie.getLogDis());
            param.put("LogRate", "" + lie.getLogRate());
            param.put("LogMoney", String.format("%.2f", lie.getLogDis() * lie.getLogRate() * mWeight));
        }*/
        param.put("LogDis", "0");
        param.put("LogRate", "0");
        param.put("LogMoney", "0");

        param.put("TotalMoney", editTotal.getText().toString());
        MainActivity.postRequest(1, sHandler, "/GoodsA/TransTemp", param);
    }

    /**
     * 参数：ClassID，Token, curPage
     *
     * @param goodsID
     */
    private void getGoodsA(String goodsID) {
        HashMap<String, Object> param = new HashMap<String, Object>();
        param.put("ClassID", "0");
        param.put("Token", CommonTools.getToken(this));
        param.put("curPage", "1");
        MainActivity.postRequest(2, sHandler, "/GoodsA/GoodsListBy", param);
    }

    private Handler sHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1: {   //提交订单
                    if (msg.arg1 > 0) {
                        // new { TransID = "201302010201" } });
                        otd = null;
                        //orderText.setText("订单号：");
                        try {
                            otd = new Gson().fromJson(msg.obj.toString(), OrderTempData.class);
                        } catch (JsonSyntaxException e) {
                            e.printStackTrace();
                        }
                        if (otd != null) {
                            //orderText.setText("订单号：" + otd.getTransID());
                            SharedPreferences.Editor editor = shared_price.edit();
                            editor.putString("sellSn", CommonTools.getUserSn());
                            editor.putString("weight", editWeight.getText().toString());
                            editor.putString("total", editTotal.getText().toString());
                            editor.putString("GoodsA", myGoodsA.toString());
                            editor.putString("orderCode", otd.getTransID());
                            editor.commit();
                            MyToast.showToast(LocalSellActivity.this, "提交订单成功！", 2);

                            String weight = editWeight.getText().toString();
                            String totalPrice = editTotal.getText().toString();

                            orderStr = createCodeImg(myGoodsA.getClassXXXID(),
                                    myGoodsA.getGoodsName(),
                                    editPrice.getText().toString(),
                                    CommonTools.getDeviceInfo(LocalSellActivity.this),
                                    totalPrice, weight,
                                    myGoodsA.getPic(), myGoodsA.getQuantity(), otd.getTransID()/*,
                                    buyNum,= editFreight.getText().toString()*/);

                            Intent intent = new Intent(LocalSellActivity.this, ShowCodeActivity.class);
                            intent.putExtra("orderStr", orderStr);
                            intent.putExtra("orderSn", otd.getTransID());
                            intent.putExtra("name", name.getText().toString());
                            intent.putExtra("price", editPrice.getText().toString());
                            intent.putExtra("weight", editWeight.getText().toString());
                            intent.putExtra("total", editTotal.getText().toString());
                            startActivity(intent);
                        } else {
                            MyToast.showToast(LocalSellActivity.this, msg.obj.toString(), 2);
                        }
                    } else {
                        MyToast.showToast(LocalSellActivity.this, msg.obj.toString(), 2);
                    }
                }
                break;
                case 2: {   //获取商品信息
                    if (msg.arg1 > 0) {
                        myGoodsA = null;
                        try {
                            myGoodsA = new Gson().fromJson(msg.obj.toString(),
                                    GoodsA.class);
                        } catch (JsonSyntaxException e) {
                            e.printStackTrace();
                        }
                        if (myGoodsA != null) {
                            initUIData("", "");
                        }
                    } else {
                        MyToast.showToast(LocalSellActivity.this, msg.obj.toString(), 2);
                    }
                }
                break;
                case 3: {//返回:new { LogDis="",LogRate=""} });
                    lie = null;
                    try {
                        lie = new Gson().fromJson(msg.obj.toString(), LogInfoEntity.class);
                    } catch (JsonSyntaxException e) {
                        e.printStackTrace();
                    }
                    if (lie != null) {
                        atFax.setText(String.format("%.2f", lie.getLogRate()));
                        atDistance.setText(String.format("%.2f", lie.getLogDis()));
                        editFreight.setText(String.format("%.2f", lie.getLogDis() * lie.getLogRate() * mWeight));
                    }
                }
                break;
            }
        }
    };

    /**
     * ClassXXX,GoodsTitle,Price,SellerDevice,TotalPrice,Weight
     *
     * @param classXXX
     * @param goodsTitle
     * @param price
     * @param sellerDevice
     * @param totalPrice
     * @param weight
     * @param pic
     * @param allWeight
     * @param orderCode
     * @return
     */
    private String createCodeImg(String classXXX, String goodsTitle, String price,
                                 String sellerDevice, String totalPrice,
                                 String weight, String pic, String allWeight,
                                 String orderCode/*, String bNum, String freight*/) {
        OrderImgEntity oie = new OrderImgEntity();
        oie.setOrderCode(orderCode);
        oie.setGoodsTitle(goodsTitle);
        oie.setPrice(price);
        oie.setWeight(weight);
        oie.setTotalPrice(totalPrice);
        //oie.setBuyNum(bNum);
        //oie.setFreight(freight);

        return oie.toString();
    }

    /**
     * 回调数据
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (GET_CLASSIC == requestCode
                && resultCode == Activity.RESULT_OK) {
            GoodsA ga = (GoodsA) data.getSerializableExtra("goodsA");
            if (myGoodsA == null || myGoodsA.getGoodsID() != ga.getGoodsID()) {
                myGoodsA = ga;
                initUIData("", "");
            }
        }
    }

    @Override
    public void onDestroy() {
        // 退出时销毁定位

        // myDialog.dismiss();
        super.onDestroy();
    }

    private InputFilter lengthfilter = new InputFilter() {
        private int decimalDigits = 2;

        public CharSequence filter(CharSequence source, int start, int end,
                                   Spanned dest, int dstart, int dend) {
            // 删除等特殊字符，直接返回
            if ("".equals(source.toString())) {
                return null;
            }
            String dValue = dest.toString();
            String[] splitArray = dValue.split("\\.");
            if (splitArray.length > 1) {
                String dotValue = splitArray[1];
                int diff = dotValue.length() + 1 - decimalDigits;
                if (diff > 0) {
                    return source.subSequence(start, end - diff);
                }
            }
            return null;
        }

        public void setDecimalDigits(int decimalDigits) {
            this.decimalDigits = decimalDigits;
        }
    };
}
