package com.aotuo.vegetable.adapter;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.text.Editable;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.aotuo.vegetable.R;
import com.aotuo.vegetable.act.GoodsList;
import com.aotuo.vegetable.act.MainActivity;
import com.aotuo.vegetable.entity.GoodsA;
import com.aotuo.vegetable.entity.LogInfoEntity;
import com.aotuo.vegetable.util.CommonTools;
import com.aotuo.vegetable.util.FinalContent;
import com.aotuo.vegetable.util.MyToast;
import com.aotuo.vegetable.util.StringUtils;

import net.tsz.afinal.FinalBitmap;

import java.util.HashMap;

/**
 * Created by 牛XX on 2016/9/14.
 */

public class SellerAdapter implements View.OnClickListener {
    public static final int S_GET_CLASSIC = 17;
    public static final int B_GET_CLASSIC = 18;
    public static final int B_BUY_OK = 19;
    private Activity context;
    private TextView stitle, editTotal, selGoodsSeller, submit, sDistance, sFax;
    private EditText editPrice, editWeight, editFreight;
    private ImageView imgSell;
    private Handler sHandler;
    private GoodsA myGoodsA;
    private String orderSn;
    private LogInfoEntity lie;
    private double mPrice = 0;
    private double mWeight = 0;
    private boolean isLock = false;

    public SellerAdapter(Activity context, View view, Handler sHandler) {
        this.context = context;
        this.sHandler = sHandler;
        initUI(view);
    }

    private void initUI(View view) {
        imgSell = (ImageView) view.findViewById(R.id.imgSell);
        stitle = (TextView) view.findViewById(R.id.stitle);
        editTotal = (TextView) view.findViewById(R.id.stotal);
        selGoodsSeller = (TextView) view.findViewById(R.id.selGoodsSeller);
        submit = (TextView) view.findViewById(R.id.submit);
        sDistance = (TextView) view.findViewById(R.id.sDistance);
        sFax = (TextView) view.findViewById(R.id.sFax);
        editPrice = (EditText) view.findViewById(R.id.editPrice);
        editFreight = (EditText) view.findViewById(R.id.sFreight);
        editWeight = (EditText) view.findViewById(R.id.editWeight);

        selGoodsSeller.setOnClickListener(this);
        submit.setOnClickListener(this);

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
                    if(lie != null){
                        sDistance.setText("" + lie.getLogDis());
                        sFax.setText("" + lie.getLogRate());
                    }
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
    }

    public void setData(GoodsA goodsA) {
        if (goodsA != null)
            this.myGoodsA = goodsA;
        else
            return;
        stitle.setText(goodsA.getGoodsName());
        editPrice.setText(goodsA.getPrice());
        editFreight.setText(goodsA.getFreight());
        FinalBitmap.create(context).display(imgSell, FinalContent.FinalUrl + goodsA.getPic());
    }

    public void setLie(LogInfoEntity lie) {
        this.lie = lie;
    }

    /**
     * String str = "$&buy=af,title=" + ga.getGoodsName()
     * + ",classXXXID=" + ga.getClassXXXID()
     * + ",goodsID=" + ga.getGoodsID()
     * + ",price=" + ga.getPrice()
     * + ",pic=" + ga.getPic();
     *
     * @param str
     */
    public void setData(String str) {
        String[] arr = str.split(",");
        try {
            if (arr.length == 6) {
                if (myGoodsA == null) {
                    myGoodsA = new GoodsA();
                }
                if (arr[1].contains("title")) {
                    String[] a = arr[1].split("=");
                    stitle.setText(a[1].trim());
                    myGoodsA.setGoodsName(a[1].trim());
                }
                if (arr[2].contains("classXXXID")) {
                    String[] a = arr[2].split("=");
                    myGoodsA.setClassXXXID(a[1].trim());
                }
                if (arr[3].contains("goodsID")) {
                    String[] a = arr[3].split("=");
                    myGoodsA.setGoodsID(a[1].trim());
                }
                if (arr[4].contains("price")) {
                    String[] a = arr[4].split("=");
                    myGoodsA.setPrice(a[1].trim());
                    editPrice.setText(a[1].trim());
                }
                if (arr[5].contains("pic")) {
                    String[] a = arr[5].split("=");
                    myGoodsA.setPic(a[1].trim());
                    FinalBitmap.create(context).display(imgSell, FinalContent.FinalUrl + a[1].trim());
                }

                editWeight.setText("");
                editTotal.setText("");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public GoodsA getMyGoodsA() {
        return myGoodsA;
    }

    public String getPrice() {
        return editPrice.getText().toString();
    }

    public String getWeight() {
        return editWeight.getText().toString();
    }

    public String getTotal() {
        return editTotal.getText().toString();
    }

    public String getFreight() {
        return editFreight.getText().toString();
    }

    public void setOrderSn(String orderSn) {
        this.orderSn = orderSn;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.selGoodsSeller: {
                Intent intent = new Intent(context, GoodsList.class);
                intent.putExtra("mode", "select");
                context.startActivityForResult(intent, S_GET_CLASSIC);
            }
            break;
            case R.id.submit: {
                if(isLock())
                    return;
                String pp = editPrice.getText().toString();
                String ww = editWeight.getText().toString();
                if(StringUtils.isEmpty(pp) || StringUtils.isEmpty(ww)) {
                    MyToast.showToast(context, "请确认价格及重量！", 3);
                    return;
                }

                setLock(true);
                HashMap<String, Object> param = new HashMap<String, Object>();
                param.put("Token", CommonTools.getToken(context));
                param.put("GoodsID", myGoodsA.getGoodsID());
                param.put("GoodsTitle", myGoodsA.getGoodsName());
                param.put("SellerDevice", CommonTools.getDeviceInfo(context));
                param.put("Price", pp);
                param.put("Weight", ww);

                param.put("GoodsMoney", String.format("%.2f", mPrice * mWeight));
                if (lie == null) {
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
                }

                String p = editTotal.getText().toString();
                double d = 0;
                try {
                    d = Double.parseDouble(p);
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
                if(d == 0){
                    MyToast.showToast(context, "请确认购买价格及重量！", 3);
                    return;
                }

                param.put("TotalMoney", editTotal.getText().toString());
                MainActivity.postRequest(3, sHandler, "/GoodsA/TransTemp", param);
            }
            break;
        }
    }

    public boolean isLock() {
        return isLock;
    }

    public void setLock(boolean lock) {
        isLock = lock;
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
