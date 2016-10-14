package com.aotuo.vegetable.dialog;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.aotuo.vegetable.R;

//搜索对话框
public class SearchCityDialog extends Dialog implements
		android.view.View.OnClickListener {
	Context context;
	EditText search_huodong;
	TextView search;
	private DialogListener listener;
	String fuzzy_search;

	public interface DialogListener {
		public void onClick(View view);
	}

	public SearchCityDialog(Context context) {
		super(context);
	}

	public SearchCityDialog(Context context, int theme) {
		super(context);
		this.context = context;
	}

	public SearchCityDialog(Context context, int theme, DialogListener listener) {
		super(context, theme);
		this.context = context;
		this.listener = listener;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.search_dialog);
		search_huodong = (EditText) findViewById(R.id.search_huodong);
		search = (TextView) findViewById(R.id.search);
		search.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		fuzzy_search = search_huodong.getText().toString();
		listener.onClick(v);
	}

	public String getsearch() {
		return fuzzy_search;
	}

	@Override
	public void dismiss() {
		super.dismiss();
		search_huodong.setText("");
	}

	@Override
	public void show() {
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			public void run() {
				InputMethodManager inputManager = (InputMethodManager) search_huodong
						.getContext().getSystemService(
								Context.INPUT_METHOD_SERVICE);
				inputManager.showSoftInput(search_huodong, 0);
			}
		}, 200);
		super.show();
	}

}
