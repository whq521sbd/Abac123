package com.aotuo.vegetable.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.InputType;
import android.text.Spanned;
import android.text.method.NumberKeyListener;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.aotuo.vegetable.R;


public class EditDialog extends Dialog  implements View.OnClickListener{
	private DialogListener listener;
	private EditText content;
	private EditText contentPwd;
	private RelativeLayout cannel;
	private RelativeLayout ok;
	private String strContent;
	private String strTitle;
	private int editType;
	private String strParam;

	public int getIntParam() {
		return intParam;
	}

	public void setIntParam(int intParam) {
		this.intParam = intParam;
	}

	private int intParam;

	public String getStrParam() {
		return strParam;
	}

	public void setStrParam(String strParam) {
		this.strParam = strParam;
	}

	public interface DialogListener{
		public void onclick(View view);
	}
	
	public EditDialog(Context context) {
		super(context);
	}
	public EditDialog(Context context, int theme, String title, int editType,
					  DialogListener listener) {
		super(context, theme);
		this.listener=listener;
		strTitle = title;
		this.editType = editType;
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.edit_dialog);
		content =(EditText) findViewById(R.id.content);
		contentPwd =(EditText) findViewById(R.id.contentPwd);
		cannel =(RelativeLayout) findViewById(R.id.cannel);
		cannel.setOnClickListener(this);
		ok =(RelativeLayout) findViewById(R.id.ok);
		ok.setOnClickListener(this);
		TextView txtTitle = (TextView) findViewById(R.id.title);
		txtTitle.setText(strTitle);

		if(editType == InputType.TYPE_TEXT_VARIATION_PASSWORD){
			content.setVisibility(View.GONE);
			contentPwd.setVisibility(View.VISIBLE);
		} else {
			content.setVisibility(View.VISIBLE);
			contentPwd.setVisibility(View.GONE);
			if(editType == InputType.TYPE_NUMBER_FLAG_DECIMAL){
				content.setFilters(new InputFilter[]{lengthfilter});
				content.setKeyListener(new NumberKeyListener() {
					@Override
					protected char[] getAcceptedChars() {
						return new char[]{'0','1','2','3','4','5','6','7','8','9','.'};
					}

					@Override
					public int getInputType() {
						return InputType.TYPE_MASK_VARIATION;
					}
				});
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
	@Override
	public void onClick(View arg0) {
		if(content.getVisibility() == View.VISIBLE)
			strContent = content.getText().toString();
		else
			strContent = contentPwd.getText().toString();
		listener.onclick(arg0);
	}
	public String returnString(){
		return strContent;
	}

	public void clearEdit(){
		content.setText("");
		contentPwd.setText("");
	}
	@Override
	public void dismiss() {
		strContent ="";
		super.dismiss();
	}
	@Override
	public void cancel() {
		strContent ="";
		super.cancel();
	}
	
}
