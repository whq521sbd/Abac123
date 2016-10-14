package com.aotuo.vegetable.act;

import android.app.Activity;
import android.app.Dialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.aotuo.vegetable.R;
import com.aotuo.vegetable.util.CommonTools;
import com.aotuo.vegetable.util.DialogUtil;
import com.aotuo.vegetable.util.MyToast;
import com.aotuo.vegetable.util.StringUtils;
import com.aotuo.vegetable.view.TitleView;

import java.util.HashMap;


public class ForgetPwdActivity extends Activity implements OnClickListener {
	private TitleView titleView;
	private EditText forget_old_pwd;
	private EditText forget_pwd_pwd;
	private EditText forget_pwd_sure_pwd;
	private TextView forget_pwd_ok;
	String  code;
	SharedPreferences shared_key;
	String key;
	private Dialog myDialog;
	private String pwdType;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_forget_pwd);
		shared_key = getSharedPreferences("shared_key", Activity.MODE_PRIVATE);
		key = shared_key.getString("key", null);
		code = getIntent().getStringExtra("code");
		pwdType = getIntent().getStringExtra("type");
		initUI();
	}

	private void initUI() {
		myDialog= DialogUtil.createDialog(this, "");
		myDialog.setCancelable(true);

		titleView = new TitleView();
		titleView.initView(this, "修改密码");

		forget_old_pwd = (EditText) findViewById(R.id.forget_old_pwd);
		forget_old_pwd.setVisibility(View.VISIBLE);

		forget_pwd_pwd = (EditText) findViewById(R.id.forget_pwd_pwd);
		forget_pwd_sure_pwd = (EditText) findViewById(R.id.forget_pwd_sure_pwd);
		forget_pwd_ok = (TextView) findViewById(R.id.forget_pwd_ok);
		forget_pwd_ok.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.forget_pwd_ok:{
				if(chechpwd()){
					//参数：Token,oldPwd, pwd
					HashMap<String,Object> param = new HashMap<String, Object>();
					param.put("Token", CommonTools.getToken(ForgetPwdActivity.this));
					param.put("oldPwd", CommonTools.md5(forget_old_pwd.getText().toString()));
					param.put("pwd", CommonTools.md5(forget_pwd_sure_pwd.getText().toString()));
					if("pwd".equals(pwdType))
						MainActivity.postRequest(1, sHandler, "/UserA/EditLogPwd", param);
					else if("chpwd".equals(pwdType))
						MainActivity.postRequest(1, sHandler, "/UserA/EditTransPwd", param);
				}
			}
			break;
		default:
			break;
		}
	}

	private Handler sHandler = new Handler(){
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			if(msg.what == 1){//返回： { Data = par.Token }
				if(msg.arg1 > 0){
					MyToast.showToast(ForgetPwdActivity.this, "修改密码成功！",2);
					finish();
				}
			}
		}
	};

	String password, password_again;

	private boolean chechpwd() {
		password = forget_pwd_pwd.getText().toString();
		password_again = forget_pwd_sure_pwd.getText().toString();
		if (StringUtils.isEmpty(password)) {
			Toast.makeText(ForgetPwdActivity.this, "密码不能为空!", Toast.LENGTH_SHORT).show();
			return false;
		} else if (password.length() < 3) {
			Toast.makeText(ForgetPwdActivity.this, "密码长度不能小于3位!", Toast.LENGTH_SHORT).show();
			return false;
		}else if (password.length() >12) {
			Toast.makeText(ForgetPwdActivity.this, "密码长度不能大于12位!", Toast.LENGTH_SHORT).show();
			return false;
		}
		if (StringUtils.isEmpty(password_again)) {
			Toast.makeText(ForgetPwdActivity.this, "确认密码不能为空!", Toast.LENGTH_SHORT).show();
			return false;
		} else if (password_again.length() < 3) {
			Toast.makeText(ForgetPwdActivity.this, "密码长度不能小于3位!", Toast.LENGTH_SHORT).show();
			return false;
		}else if (password_again.length() >12) {
			Toast.makeText(ForgetPwdActivity.this, "密码长度不能大于12位!", Toast.LENGTH_SHORT).show();
			return false;
		}
		if (!password.equals(password_again)) {
			Toast.makeText(ForgetPwdActivity.this, "两次密码输入不相同!", Toast.LENGTH_SHORT).show();
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
