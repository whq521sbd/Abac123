package com.aotuo.vegetable.util;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.aotuo.vegetable.R;

public class DialogUtil {

	private Context context;

	public DialogUtil(Context context) {
		this.context = context;
	}

	public static Dialog createDialog(Context context, String msg) {
		if(context!=null){
			LayoutInflater inflater = LayoutInflater.from(context);
			View v = inflater.inflate(R.layout.mydialog, null);// 得到加载view
			LinearLayout layout = (LinearLayout) v.findViewById(R.id.dialog_view);// 加载布局
			// main.xml中的ImageView
			ImageView spaceshipImage = (ImageView) v.findViewById(R.id.img_load);
			TextView tipTextView = (TextView) v.findViewById(R.id.tipTextView);// 提示文字
			// 加载动画
			Animation hyperspaceJumpAnimation = AnimationUtils.loadAnimation(
					context, R.anim.myanim);
			// 使用ImageView显示动画
			spaceshipImage.setAnimation(hyperspaceJumpAnimation);
			tipTextView.setText(msg);// 设置加载信息

			Dialog loadingDialog = new Dialog(context, R.style.Loooading_dialog);// 创建自定义样式dialog
			loadingDialog.setContentView(layout, new LinearLayout.LayoutParams(
					LinearLayout.LayoutParams.FILL_PARENT,
					LinearLayout.LayoutParams.FILL_PARENT));// 设置布局
			return loadingDialog;
		}else{
			return null;
		}
	}
}
