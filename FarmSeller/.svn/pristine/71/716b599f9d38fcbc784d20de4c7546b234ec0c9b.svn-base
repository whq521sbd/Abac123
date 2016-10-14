package com.aotuo.vegetable.act;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.aotuo.vegetable.R;
import com.aotuo.vegetable.entity.OrderInfoEntity;
import com.aotuo.vegetable.util.CommonTools;
import com.aotuo.vegetable.util.MyToast;
import com.aotuo.vegetable.util.StringUtils;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.util.HashMap;

/**
 * Created by Administrator on 2016/9/26.
 */

public class NoCamera extends Activity implements View.OnClickListener {
    private TextView userNum;
    private EditText editIdenCode, editOrder;
    private Button /*idenCode,*/ snCode;
    private LinearLayout menuCode0;
    private OrderInfoEntity orderInfo = null;
    private String num;
    private Button BT_returnhome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.buyer_nocamer);
        initview();

    }

    private void initview() {
        BT_returnhome = (Button) findViewById(R.id.BT_returnhome);
        userNum = (TextView) findViewById(R.id.userNum);
        editIdenCode = (EditText) findViewById(R.id.editIdenCode);
        editOrder = (EditText) findViewById(R.id.editOrder);
        //idenCode = (Button) findViewById(R.id.idenCode);
        snCode = (Button) findViewById(R.id.snCode);
        menuCode0 = (LinearLayout) findViewById(R.id.menuCode0);
        userNum.setText(CommonTools.getUserSn());
        snCode.setOnClickListener(this);
        BT_returnhome.setOnClickListener(this);
    }

    /**
     * 手动获取订单号
     *
     * @param code
     */
    private void getMenuOrderCode(String code) {
        num = code;
        HashMap<String, Object> param = new HashMap<String, Object>();
        param.put("Token", CommonTools.getToken(this));
        param.put("Num", code);
        MainActivity.postRequest(3, sHandler, "/GoodsA/TransTempInfo", param);
    }

    private Handler sHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {

                case 3: {
                    if (msg.arg1 > 0) {
                        orderInfo = null;
                        try {
                            orderInfo = new Gson().fromJson(msg.obj.toString(), OrderInfoEntity.class);
                        } catch (JsonSyntaxException e) {
                            e.printStackTrace();
                        }
                        if (orderInfo != null) {
                            Intent intent = new Intent();
                            intent.putExtra("oie", orderInfo);
                            orderInfo.setNum(num);
                            setResult(RESULT_OK, intent);
                            finish();
                        }
                    } else {
                        MyToast.showToast(NoCamera.this, msg.obj.toString(), 2);
                    }
                }
                break;
            }
        }
    };

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.snCode:
                String str = editOrder.getText().toString();
                if (!StringUtils.isEmpty(str))
                    getMenuOrderCode(str);
                else
                    MyToast.showToast(NoCamera.this, "请输入订单号", 2);
                break;
            case R.id.BT_returnhome:

                finish();

                break;
            default:

                break;
        }
    }
}
