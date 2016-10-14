package com.aotuo.vegetable.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.aotuo.vegetable.R;

//用户更新头像对话框
public class Userphoto_dialog extends Dialog implements
		android.view.View.OnClickListener {
	Context context;
	public RelativeLayout paizhao;
	public RelativeLayout image_book;
	private DialogListener listener;

	public interface DialogListener {
		public void onClick(View view);
	}

	public Userphoto_dialog(Context context) {
		super(context);
		this.context = context;
	}

	public Userphoto_dialog(Context context, int theme, DialogListener listener) {
		super(context, theme);
		this.context = context;
		this.listener = listener;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.userphoto_dialog);
		paizhao = (RelativeLayout) findViewById(R.id.paizhao);
		image_book = (RelativeLayout) findViewById(R.id.image_book);
		paizhao.setOnClickListener(this);
		image_book.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		listener.onClick(v);
	}

}
