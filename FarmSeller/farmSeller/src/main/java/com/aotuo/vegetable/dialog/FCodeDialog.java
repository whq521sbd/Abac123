package com.aotuo.vegetable.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.aotuo.vegetable.R;

public class FCodeDialog extends Dialog  implements android.view.View.OnClickListener{
	private Context context;
	private DialogListener listener;
	private EditText f_content;
	RelativeLayout cannel_f_code;
	RelativeLayout sure_f_code;
	String f_code;
	public interface DialogListener{
		public void onclick(View view);
	}
	
	public FCodeDialog(Context context) {
		super(context);
	}
	public FCodeDialog(Context context,int theme, DialogListener listener) {
		super(context, theme);
		this.context = context;
		this.listener=listener;
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.f_code_dialog);
		f_content=(EditText) findViewById(R.id.f_content);
		cannel_f_code=(RelativeLayout) findViewById(R.id.cannel_f_code);
		cannel_f_code.setOnClickListener(this);
		sure_f_code=(RelativeLayout) findViewById(R.id.sure_f_code);
		sure_f_code.setOnClickListener(this);
	}

	@Override
	public void onClick(View arg0) {
		f_code=f_content.getText().toString();
		listener.onclick(arg0);
	}
	public String returnString(){
		return f_code;
	}
	@Override
	public void dismiss() {
		f_code="";
		super.dismiss();
	}
	@Override
	public void cancel() {
		f_code="";
		super.cancel();
	}
	
}
