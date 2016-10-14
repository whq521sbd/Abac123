package com.aotuo.vegetable.act;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.aotuo.vegetable.R;
import com.aotuo.vegetable.base.BaseActivity;
import com.aotuo.vegetable.util.CommonTools;
import com.aotuo.vegetable.util.MyToast;
import com.aotuo.vegetable.util.StringUtils;
import com.aotuo.vegetable.view.TitleView;

/**
 * Created by 牛XX on 2016/9/24.
 */

public class NolocalActivity extends BaseActivity implements View.OnClickListener {
    private static final int TO_NEXT = 131;
    private TitleView titleView;
    private Button toSearcch;
    private LinearLayout selCity;
    private TextView city;
    private EditText goodsName;
    private String areaName, areaNum, areaCity;
    private  Button bt_toreturnhome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_nolocal_sell);

        initUI();
    }

    private void initUI() {
        titleView = new TitleView();
        if (CommonTools.isBuy(this))
            titleView.initView(this, "本市");
        else
            titleView.initView(this, "选择城市");
        toSearcch = (Button) findViewById(R.id.toSearcch);
        city = (TextView) findViewById(R.id.city);
        goodsName = (EditText) findViewById(R.id.goodsName);
        bt_toreturnhome = (Button) findViewById(R.id.bt_toreturnhome);
        selCity = (LinearLayout) findViewById(R.id.selCity);
        if (CommonTools.isBuy(this))
            selCity.setVisibility(View.GONE);

        toSearcch.setOnClickListener(this);
        city.setOnClickListener(this);
        bt_toreturnhome.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.city: {
                Intent intent = new Intent(NolocalActivity.this,
                        AreaActivity.class);
                intent.putExtra("mode", "1");
                startActivityForResult(intent, 12);
            }
            break;
            case R.id.bt_toreturnhome:
                finish();
                break;

            case R.id.toSearcch: {
                if (StringUtils.isEmpty(areaNum) && CommonTools.isSell(this)) {
                    MyToast.showToast(NolocalActivity.this, "请选择要查看的城市！", 2);
                    return;
                }

                if (CommonTools.isBuy(this)) {
                    Intent intent = new Intent(NolocalActivity.this, MarketActivity.class);
                    intent.putExtra("goodsName", goodsName.getText().toString());
                    intent.putExtra("MatketName", CommonTools.getMarketName(NolocalActivity.this));
                    intent.putExtra("MatketNum", "-1");
                    startActivityForResult(intent, TO_NEXT);
                } else {
                    Intent intent = new Intent(NolocalActivity.this, CityMarketActivity.class);
                    intent.putExtra("goodsName", goodsName.getText().toString());
                    intent.putExtra("areaCity", areaCity);
                    intent.putExtra("areaNum", areaNum);
                    startActivityForResult(intent, TO_NEXT);
                }
            }
            break;
        }
    }

    @Override
    public void onBackPressed() {
        setResult(RESULT_FIRST_USER);
        super.onBackPressed();
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 12 && resultCode == RESULT_OK && data != null) {
            areaName = data.getStringExtra("areaName");
            areaNum = data.getStringExtra("areaNum");
            areaCity = data.getStringExtra("cityName");
            city.setText(areaCity);
        } else if(requestCode == TO_NEXT && resultCode == RESULT_CANCELED){
            setResult(RESULT_CANCELED);
            finish();
        }
    }
}
