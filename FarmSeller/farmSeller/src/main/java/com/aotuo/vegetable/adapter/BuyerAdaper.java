package com.aotuo.vegetable.adapter;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.aotuo.vegetable.R;
import com.aotuo.vegetable.act.BuyGoodsActivity;
import com.aotuo.vegetable.act.BuyGoodsList;
import com.aotuo.vegetable.act.MainActivity;
import com.aotuo.vegetable.entity.GoodsA;
import com.aotuo.vegetable.util.CommonTools;
import com.aotuo.vegetable.util.FinalContent;
import com.aotuo.vegetable.util.MyToast;
import com.aotuo.vegetable.util.StringUtils;

import net.tsz.afinal.FinalBitmap;

import java.util.HashMap;

/**
 * Created by 牛XX on 2016/9/14.
 */

public class BuyerAdaper implements View.OnClickListener {
    public static final int B_GET_CLASSIC = 13;
    private Activity context;
    private TextView title, price, total, weight, bDistance, bFax, bFreight;
    private TextView selGoodsBuyer, toBuy, goodsTotal, addCar ,tv_querycar;
    private ImageView imgBuy;
    private Handler sHandler;
    private String orderSn;
    private String toUser;



////        查看购物车
//        tv_querycar.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
    public BuyerAdaper(Activity context, View view, Handler handler) {
        this.context = context;
        this.sHandler = handler;
        initUI(view);
    }

    public void setToUser(String toUser) {
        this.toUser = toUser;
    }

    private void initUI(View v) {
        title = (TextView) v.findViewById(R.id.title);
        price = (TextView) v.findViewById(R.id.price);
        total = (TextView) v.findViewById(R.id.total);
        addCar = (TextView) v.findViewById(R.id.addCar);
        goodsTotal = (TextView) v.findViewById(R.id.goodsTotal);
        bDistance = (TextView) v.findViewById(R.id.bDistance);
        bFax = (TextView) v.findViewById(R.id.bFax);
        bFreight = (TextView) v.findViewById(R.id.bFreight);
        imgBuy = (ImageView) v.findViewById(R.id.imgBuy);

        selGoodsBuyer = (TextView) v.findViewById(R.id.selGoodsBuyer);
        toBuy = (TextView) v.findViewById(R.id.toBuy);
        weight = (TextView) v.findViewById(R.id.weight);
        tv_querycar = (TextView) v.findViewById(R.id.tv_querycar);
        tv_querycar.setOnClickListener(this);
        selGoodsBuyer.setOnClickListener(this);
        toBuy.setOnClickListener(this);
        if(CommonTools.isBuy(context)){
            addCar.setOnClickListener(this);
            addCar.setVisibility(View.VISIBLE);
        } else {
            addCar.setVisibility(View.GONE);
        }
    }

    /**
     * "$&order=" + otd.getTransID()
     * + ",T=" + goodsA.getGoodsName()//title
     * + ",P=" + sellerAdapter.getPrice()//price
     * + ",W=" + sellerAdapter.getWeight()//weight
     * + ",T=" + sellerAdapter.getTotal()//total
     * + ",pic=" + goodsA.getPic()
     * + ",LD=" + LogDis
     * + ",LR=" + LogRate
     * + ",fre=" + sellerAdapter.getFreight();
     *
     * @param str
     */
    public void setData(String str) {
        String[] arr = str.split(",");
        try {
            if (arr.length == 9) {
                if (arr[0].contains("order")) {
                    String[] a = arr[0].split("=");
                    orderSn = a[1].trim();
                }
                if (arr[1].contains("T")) {
                    String[] a = arr[1].split("=");
                    title.setText(a[1].trim());
                }
                if (arr[2].contains("P")) {
                    String[] a = arr[2].split("=");
                    price.setText(a[1].trim());
                }
                if (arr[3].contains("W")) {
                    String[] a = arr[3].split("=");
                    weight.setText(a[1].trim());
                }
                if (arr[4].contains("T")) {
                    String[] a = arr[4].split("=");
                    goodsTotal.setText(a[1].trim());
                    total.setText(a[1].trim());
                }
                if (arr[5].contains("pic")) {
                    String[] a = arr[5].split("=");
                    FinalBitmap.create(context).display(imgBuy, FinalContent.FinalUrl + a[1].trim());
                }
                if (arr[6].contains("LD")) {
                    String[] a = arr[6].split("=");
                    bDistance.setText(a[1].trim());
                }
                if (arr[7].contains("LR")) {
                    String[] a = arr[7].split("=");
                    bFax.setText(a[1].trim());
                }
//                if(arr[8].contains("fre")){
//                    String[] a = arr[8].split("=");
//                    bFreight.setText(a[1].trim());
//                    float f = 0.0f;
//                    try {
//                        f = Float.parseFloat(a[1]);
//                    } catch (NumberFormatException e) {
//                        e.printStackTrace();
//                    }
//                    if(f > 0){
//                        String s = goodsTotal.getText().toString();
//                        float ff = 0f;
//                        try {
//                            ff = Float.parseFloat(s);
//                        } catch (NumberFormatException e) {
//                            e.printStackTrace();
//                        }
//                        f = f + ff;
//                        total.setText(String.format("%.2f", f));
//                    }
//                }
                {
                    double dis = 0, rate = 0, wei = 0, tot = 0;
                    try {
                        dis = Double.parseDouble(bDistance.getText().toString().trim());
                        rate = Double.parseDouble(bFax.getText().toString().trim());
                        wei = Double.parseDouble(weight.getText().toString().trim());
                        tot = Double.parseDouble(goodsTotal.getText().toString().trim());
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                    double fre = dis * rate * wei;
                    bFreight.setText(String.format("%.2f", fre));
                    total.setText(String.format("%.2f", fre + tot));
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setData(GoodsA goodsA) {
        title.setText(goodsA.getGoodsName());
        price.setText(goodsA.getPrice());
        bFreight.setText(goodsA.getFreight());
        FinalBitmap.create(context).display(imgBuy, FinalContent.FinalUrl + goodsA.getPic());
    }

    public void clearData() {
        title.setText("");
        price.setText("0");
        total.setText("0");
        bDistance.setText("0");
        bFax.setText("0");
        bFreight.setText("0");
        weight.setText("0");
        orderSn = "";
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.selGoodsBuyer: {
                Intent intent = new Intent(context, BuyGoodsList.class);
                intent.putExtra("UserNum", toUser);
                context.startActivityForResult(intent, SellerAdapter.B_GET_CLASSIC);
            }
            break;
            case R.id.toBuy: {
                if (!StringUtils.isEmpty(orderSn) && !StringUtils.isEmpty(total.getText().toString())) {
                    Intent intent = new Intent(context, BuyGoodsActivity.class);
                    intent.putExtra("OrderSn", orderSn);
                    intent.putExtra("name", title.getText().toString());
                    intent.putExtra("price", price.getText().toString());
                    intent.putExtra("weight", weight.getText().toString());
                    intent.putExtra("total", total.getText().toString());
                    intent.putExtra("dis", bDistance.getText().toString());
                    intent.putExtra("fax", bFax.getText().toString());
                    intent.putExtra("fre", bFreight.getText().toString());
                    context.startActivityForResult(intent, SellerAdapter.B_BUY_OK);
                } else {
                    MyToast.showToast(context, "请与商家确认订单！", 2);
                }
            }
            break;
            case R.id.addCar: {
                if (!StringUtils.isEmpty(orderSn) && !StringUtils.isEmpty(total.getText().toString())) {
                    //参数：Token,Num(临时订单号),LogDis(运费距离),LogRate(运费率),LogMoney(运费),TotalMoney(合计)
                    HashMap<String, Object> param = new HashMap<String, Object>();
                    param.put("Token", CommonTools.getToken(context));
                    param.put("Num", orderSn);
                    param.put("LogDis", bDistance.getText().toString());
                    param.put("LogRate", bFax.getText().toString());
                    param.put("LogMoney", bFreight.getText().toString());
                    param.put("TotalMoney", total.getText().toString());
                    MainActivity.postRequest(6, sHandler, "/GoodsA/CartAdd", param);
                } else {
                    MyToast.showToast(context, "请与商家确认订单！", 2);
                }

            }
            break;
            //跳转到购物车
            case R.id.tv_querycar:{
//                设置加载状态
                MainActivity.mSata=1;
                Intent intent = new Intent(context,MainActivity.class);
//                设置intent标志
                intent.putExtra("BACK","SHOWCAR");
                context.startActivity(intent);
                context.finish();

            }
                break;
        }
    }

}
