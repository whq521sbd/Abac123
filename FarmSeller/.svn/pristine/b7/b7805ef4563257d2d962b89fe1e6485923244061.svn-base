package com.aotuo.vegetable.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.aotuo.vegetable.R;

public class OkDialog extends Dialog implements
		android.view.View.OnClickListener {
	Context context;
	private RelativeLayout ok;
	private TextView dialog_content;
	private DialogListener listener;
	private String title;

	public interface DialogListener {
		public void onclick(View view);
	}

	public OkDialog(Context context, int theme, String title,
			DialogListener listener) {
		super(context, theme);
		this.context = context;
		this.listener = listener;
		this.title = title;
	}

	public OkDialog(Context context) {
		super(context);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.ok_dialog);
		ok = (RelativeLayout) findViewById(R.id.ok);
		ok.setOnClickListener(this);
		dialog_content = (TextView) findViewById(R.id.dialog_content);

		dialog_content.setText(title);
	}

	@Override
	public void onClick(View arg0) {
		listener.onclick(arg0);
	}

}
