package com.aotuo.vegetable.act;

import android.app.Activity;
import android.app.Dialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.aotuo.vegetable.R;
import com.aotuo.vegetable.base.BaseActivity;
import com.aotuo.vegetable.entity.LoginResultEntity;
import com.aotuo.vegetable.util.CommonTools;
import com.aotuo.vegetable.util.DialogUtil;
import com.aotuo.vegetable.util.MyToast;
import com.aotuo.vegetable.util.StringUtils;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.util.HashMap;

public class LoginActivity extends BaseActivity implements OnClickListener {
    private TextView forgetPwd;// 登录、忘记密码、注册
    private Dialog myDialog;
    private RelativeLayout login_btn;
    private CheckBox savePwd;
    private EditText login_username, login_password;// 用户名和密码
    private String username, password;
    private String cpusername, cppassword;
    private SharedPreferences shared_key;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        shared_key = getSharedPreferences("shared_key", Activity.MODE_PRIVATE);
        username = shared_key.getString("username", null);
        cppassword = shared_key.getString("userpwd", null);
        initUI();
    }

    private void initUI() {
        myDialog = DialogUtil.createDialog(this, "");
        myDialog.setCancelable(true);

        login_btn = (RelativeLayout) findViewById(R.id.login_btn);
        login_btn.setOnClickListener(this);

        login_username = (EditText) findViewById(R.id.login_username);
        login_password = (EditText) findViewById(R.id.login_password);
        forgetPwd = (TextView) findViewById(R.id.forgetPwd);
        savePwd = (CheckBox) findViewById(R.id.savePwd);
        if(!StringUtils.isEmpty(username))
            login_username.setText(username);
        if(!StringUtils.isEmpty(cppassword) && cppassword.length() == 32) {
            login_password.setText(cppassword.substring(0, 8));
            savePwd.setChecked(true);
        } else {
            savePwd.setChecked(false);
        }
        login_username.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                login_password.setText("");
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_btn:
                if (checkIsEmpty()) {
                    HashMap<String, Object> hashMap = new HashMap<String, Object>();
                    hashMap.put("UserName", username);
                    if(!StringUtils.isEmpty(cppassword)
                            && cppassword.startsWith(password) && cppassword.length() == 32 )
                        hashMap.put("Pwd", cppassword);
                    else
                        hashMap.put("Pwd", CommonTools.md5(password));

                    hashMap.put("deviceInfo", CommonTools.getDeviceInfo(LoginActivity.this));
                    //MyToast.showToast(LoginActivity.this, tm.getDeviceId(), 2);
                    MainActivity.postRequest(1, sHandler, "/UserA/Login", hashMap);
                }
                break;
            case R.id.savePwd: {

            }
            break;
            default:
                break;
        }
    }

    private Handler sHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            Log.i("login back", msg.obj.toString());
            myDialog.dismiss();
            LoginResultEntity lre = null;
            try {
                lre = new Gson().fromJson(msg.obj.toString(), LoginResultEntity.class);
            } catch (JsonSyntaxException e) {
                e.printStackTrace();
            }
            if (lre != null) {
                //保存类版本号和城市版本号
                CommonTools.setToken(LoginActivity.this, lre.getToken(), lre.getClassVer());
                CommonTools.setUserType(LoginActivity.this, lre.getFunMenu());
                CommonTools.setAreaVer(LoginActivity.this, lre.getAreaVer());
                CommonTools.setMarket(LoginActivity.this, lre.getMarketName(), lre.getMarketNum());
                CommonTools.setSmsLimit(LoginActivity.this, lre.getSMSLimit());
                CommonTools.setSmsSw(LoginActivity.this, lre.getCheckSMS());

                //更新最新数据
                MainActivity.getUserData();

                //save用户名
                SharedPreferences.Editor editor = shared_key.edit();
                editor.putString("username", username);
                if(savePwd.isChecked()) {
                    if(!StringUtils.isEmpty(cppassword) && cppassword.startsWith(password))
                        editor.putString("userpwd", cppassword);
                    else
                        editor.putString("userpwd", CommonTools.md5(password));
                } else {
                    editor.putString("userpwd", null);
                }
                editor.commit();
                setResult(RESULT_OK);
                finish();
            } else {
                if (msg.obj != null)
                    MyToast.showToast(LoginActivity.this, msg.obj.toString(), 2);
            }
        }
    };

    String key;

    private boolean checkIsEmpty() {
        if (!isFinishing()) {
            myDialog.show();
        }
        username = login_username.getText().toString();
        password = login_password.getText().toString();
        if (StringUtils.isEmpty(username)) {
            Toast.makeText(LoginActivity.this, "用户名不能为空!", Toast.LENGTH_SHORT)
                    .show();
            return false;
        } else if (username.length() < 3) {
            Toast.makeText(LoginActivity.this, "用户名长度大于2且小于20",
                    Toast.LENGTH_SHORT).show();
            return false;
        }

        if (StringUtils.isEmpty(password)) {
            Toast.makeText(LoginActivity.this, "密码不能为空!", Toast.LENGTH_SHORT)
                    .show();
            return false;
        }
        return true;
    }

    @Override
    protected void onDestroy() {
        myDialog.dismiss();
        super.onDestroy();
    }
}
