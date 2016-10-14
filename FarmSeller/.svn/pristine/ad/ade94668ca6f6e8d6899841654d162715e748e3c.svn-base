package com.aotuo.vegetable.act;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.aotuo.vegetable.base.BaseActivity;
import com.aotuo.vegetable.R;

public class SelectPayment extends BaseActivity implements OnClickListener {
	private TextView online_pay;
	private TextView cod;
	private TextView delivery;
	private TextView other;
	private ImageView confirm;

	private String payType; // 0:在线支付 1：货到付款
	private String deliveryType; // 0：快递 1：其他
	private int pos;
	private String pay_delivery;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_select_payment);
		pos = getIntent().getIntExtra("pos", 0);
		payType = getIntent().getStringExtra("pay");
		deliveryType = getIntent().getStringExtra("delivery");
		pay_delivery = getIntent().getStringExtra("pay_delivery");
		
		initUI();
		if("在线支付".equals(payType))
			selonLine();
		else if("货到付款".equals(payType))
			selCod();
		if("快递".equals(deliveryType))
			selDelivery();
		else if("其他".equals(deliveryType))
			selOther();
		
	}

	private void initUI() {
		// TODO Auto-generated method stub
		online_pay = (TextView) findViewById(R.id.online_pay);
		cod = (TextView) findViewById(R.id.cod);
		delivery = (TextView) findViewById(R.id.delivery);
		other = (TextView) findViewById(R.id.other);
		confirm = (ImageView) findViewById(R.id.confirm);
		online_pay.setOnClickListener(this);
		cod.setOnClickListener(this);
		delivery.setOnClickListener(this);
		other.setOnClickListener(this);
		confirm.setOnClickListener(this);
		
		if("0".equals(pay_delivery))
			cod.setVisibility(View.GONE);
		else
			cod.setVisibility(View.VISIBLE);
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getId()) {
		case R.id.online_pay: {
			selonLine();
		}
			break;
		case R.id.cod: {
			selCod();
		}
			break;
		case R.id.delivery: {
			selDelivery();
		}
			break;
		case R.id.other: {
			selOther();
		}
			break;
		case R.id.confirm: {
			Intent intent = new Intent();
			
			intent.putExtra("pos", pos);
			intent.putExtra("pay", payType);
			intent.putExtra("delivery", deliveryType);
			setResult(RESULT_OK, intent);
			finish();
		}
			break;
		}
	}

	private void selonLine(){
		payType = "在线支付";
		online_pay.setBackgroundResource(R.drawable.selete_btn_corner_background);
		online_pay.setTextColor(getResources().getColor(R.color.cfd3636));
		cod.setBackgroundResource(R.drawable.cannel_delete_background);
		cod.setTextColor(getResources().getColor(R.color.c333333));
	}
	
	private void selCod(){
		payType = "货到付款";
		cod.setBackgroundResource(R.drawable.selete_btn_corner_background);
		cod.setTextColor(getResources().getColor(R.color.cfd3636));
		online_pay
				.setBackgroundResource(R.drawable.cannel_delete_background);
		online_pay.setTextColor(getResources().getColor(R.color.c333333));
	}
	
	private void selDelivery(){
		deliveryType = "快递";
		delivery.setBackgroundResource(R.drawable.selete_btn_corner_background);
		delivery.setTextColor(getResources().getColor(R.color.cfd3636));
		other.setBackgroundResource(R.drawable.cannel_delete_background);
		other.setTextColor(getResources().getColor(R.color.c333333));
	}
	
	private void selOther(){
		deliveryType = "其他";
		other.setBackgroundResource(R.drawable.selete_btn_corner_background);
		other.setTextColor(getResources().getColor(R.color.cfd3636));
		delivery.setBackgroundResource(R.drawable.cannel_delete_background);
		delivery.setTextColor(getResources().getColor(R.color.c333333));
	}
}
