package com.aotuo.vegetable.act;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.InputType;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.aotuo.vegetable.R;
import com.aotuo.vegetable.base.BaseActivity;
import com.aotuo.vegetable.dialog.DefaultDialog;
import com.aotuo.vegetable.dialog.EditDialog;
import com.aotuo.vegetable.util.CommonTools;
import com.aotuo.vegetable.util.MyToast;
import com.aotuo.vegetable.util.StaticDialog;
import com.aotuo.vegetable.util.StringUtils;
import com.aotuo.vegetable.view.TitleView;

import java.util.HashMap;

/**
 * Created by 牛XX on 2016/9/13.
 */

public class SecurityActivity extends BaseActivity implements View.OnClickListener {
    private RelativeLayout loginPwd, chgPwd, chgMessage, setChgPwd;
    private TitleView titleView;
    private TextView chgStatus;
    private DefaultDialog dialog;
    private EditDialog editDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_security);

        loginPwd = (RelativeLayout) findViewById(R.id.loginPwd);
        chgPwd = (RelativeLayout) findViewById(R.id.chgPwd);
        chgMessage = (RelativeLayout) findViewById(R.id.chgMessage);
        setChgPwd = (RelativeLayout) findViewById(R.id.setChgPwd);
        chgStatus = (TextView) findViewById(R.id.chgStatus);
        loginPwd.setOnClickListener(this);
        chgPwd.setOnClickListener(this);
        chgMessage.setOnClickListener(this);
        setChgPwd.setOnClickListener(this);

        initChgPwd();
        titleView = new TitleView();
        titleView.initView(this, "安全中心");
        dialog = new DefaultDialog(this, R.style.MyDialogStyle,
                new DefaultDialog.DialogListener() {

                    @Override
                    public void onclick(View v) {
                        switch (v.getId()) {
                            case R.id.cannel_clear:
                                dialog.dismiss();
                                break;
                            case R.id.sure_clear:
                                dialog.dismiss();
                                StaticDialog sd = new StaticDialog();
                                sd.init_dialog(editDialog);
                                editDialog.clearEdit();
                                break;
                            default:
                                break;
                        }
                    }
                });
        dialog.setTitle("是否删除？");
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
                                    MyToast.showToast(SecurityActivity.this, "请输入正确交易密码！", 2);
                                } else {
                                    //参数：Token,TransPwd,State(1:使用 0:不使用)
                                    HashMap<String, String> param = new HashMap<String, String>();
                                    param.put("Token", CommonTools.getToken(SecurityActivity.this));
                                    param.put("TransPwd", pwd);
                                    if (CommonTools.getCheckTPWD(SecurityActivity.this))
                                        param.put("State", "0");
                                    else
                                        param.put("State", "1");
                                    MainActivity.getRequest(3, sHandler, "/UserA/TalkUserDel", param);

                                }
                                editDialog.dismiss();
                            }
                            break;
                        }
                    }
                });
    }

    private Handler sHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what == 3){
                if(msg.arg1 > 0){
                    if(CommonTools.getCheckTPWD(SecurityActivity.this)){
                        CommonTools.setCheckTPWD(SecurityActivity.this, false);
                    } else {
                        CommonTools.setCheckTPWD(SecurityActivity.this, true);
                    }
                    initChgPwd();
                    MyToast.showToast(SecurityActivity.this, "修改成功！", 2);
                }
            }
        }
    };

    private void initChgPwd(){
        if(CommonTools.getCheckTPWD(SecurityActivity.this))
            chgStatus.setText("使用交易密码");
        else
            chgStatus.setText("不使用交易密码");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.loginPwd: {
                Intent intent;
                intent = new Intent(SecurityActivity.this, ForgetPwdActivity.class);
                intent.putExtra("type", "pwd");
                startActivity(intent);
            }
            break;
            case R.id.chgPwd: {
                Intent intent;
                intent = new Intent(SecurityActivity.this, ForgetPwdActivity.class);
                intent.putExtra("type", "chpwd");
                startActivity(intent);
            }
            break;
            case R.id.setChgPwd: {
                StaticDialog sd = new StaticDialog();
                if (CommonTools.getCheckTPWD(SecurityActivity.this)) {
                    dialog.setStrContent("是否取消交易密码？");
                } else {
                    dialog.setStrContent("是否需要交易密码？");
                }
                sd.init_dialog(dialog);
            }
            break;
            case R.id.chgMessage: {
                startActivity(new Intent(SecurityActivity.this, SMSSetActivity.class));
            }
            break;
        }
    }
}
