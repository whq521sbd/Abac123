package com.aotuo.vegetable.act;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.aotuo.vegetable.R;
import com.aotuo.vegetable.base.BaseActivity;
import com.aotuo.vegetable.util.StringUtils;
import com.aotuo.vegetable.view.TitleView;

/**
 * Created by 牛XX on 2016/9/13.
 */

public class SMSSetActivity extends BaseActivity {
    private TitleView titleView;
    private RadioGroup radioG;
    private RadioButton radioOn, radioOff;
    private EditText editLimit, chgPwd;
    private TextView submit;
    private String cpStr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_sms_set);

        initUI();
    }

    private void initUI() {
        titleView = new TitleView();
        titleView.initView(this, "短信设置");

        editLimit = (EditText) findViewById(R.id.limit);
        chgPwd = (EditText) findViewById(R.id.chgPwd);

        radioG = (RadioGroup) findViewById(R.id.radioG);
        radioOn = (RadioButton) findViewById(R.id.radioOn);
        radioOff = (RadioButton) findViewById(R.id.radioOff);
        radioG.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == radioOn.getId()) {
                    editLimit.setFocusable(true);
                    editLimit.setFocusableInTouchMode(true);
                    if(!StringUtils.isEmpty(cpStr))
                        editLimit.setText(cpStr);
                    else
                        editLimit.setText("500");
                } else if (checkedId == radioOff.getId()) {
                    editLimit.setFocusable(false);
                    editLimit.setFocusableInTouchMode(false);
                    cpStr = editLimit.getText().toString();
                    editLimit.setText("");
                }
            }
        });
        radioG.check(radioOn.getId());

        submit = (TextView) findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
