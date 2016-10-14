package com.aotuo.vegetable.util;

import android.app.Dialog;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

public class StaticDialog {
	// 确认删除对话框
	public void init_dialog(Dialog dialog) {
		Window dialogWindow = dialog.getWindow();
		WindowManager.LayoutParams lp = dialog.getWindow().getAttributes();
		dialogWindow.setLayout(lp.WRAP_CONTENT, lp.WRAP_CONTENT);
		dialogWindow.getDecorView().setPadding(0, 0, 0, 0);
		dialogWindow.setGravity(Gravity.CENTER);
		dialog.getWindow().setAttributes(lp);
		// 设置点击Dialog外部任意区域关闭Dialog
		dialog.setCanceledOnTouchOutside(false);
		dialog.show();
	}
}
