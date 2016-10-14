package com.aotuo.vegetable.act;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.PopupWindow.OnDismissListener;
import android.widget.TextView;

import com.aotuo.vegetable.R;
import com.aotuo.vegetable.adapter.InterlligenceAdapter;
import com.aotuo.vegetable.adapter.SelDateListAdapter;
import com.aotuo.vegetable.base.BaseActivity;
import com.aotuo.vegetable.entity.CustomerEntity;
import com.aotuo.vegetable.entity.KeyValueData;
import com.aotuo.vegetable.util.CommonTools;
import com.aotuo.vegetable.view.TitleView;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CustomerInterlligence extends BaseActivity implements
		OnClickListener {
	private TitleView titleView = new TitleView();
	private TextView selDate, dealTotal, dealNum;
	private ListView listView;
	private List<CustomerEntity> reList = new ArrayList<CustomerEntity>();
	private InterlligenceAdapter adapter;
	private List<KeyValueData> strList = new ArrayList<KeyValueData>();
	private int period = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_interlligence);
		strList.add(new KeyValueData("今天", "a"));
		strList.add(new KeyValueData("七天", "b"));
		strList.add(new KeyValueData("一个月", "c"));
		strList.add(new KeyValueData("一年", "d"));
		initData();
		initUI();
	}

	private void initData() {
		// TODO Auto-generated method stub
		HashMap<String, Object> param = new HashMap<String, Object>();
		param.put("Token", CommonTools.getToken(this));
		MainActivity.postRequest(1, sHandler, "/UserA/StatCusTimes", param);
	}

	private Handler sHandler = new Handler(){
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			if(msg.what== 1){
				if(msg.arg1 > 0){
					List<CustomerEntity> tList = null;
					try {
						tList = new Gson().fromJson(msg.obj.toString(),
                                new TypeToken<List<CustomerEntity>>(){}.getType());
					} catch (JsonSyntaxException e) {
						e.printStackTrace();
					}
					if(tList != null){
						reList.addAll(tList);
						adapter.notifyDataSetChanged();
					}
				}
			}
		}
	};

	private void initUI() {
		// TODO Auto-generated method stub
		titleView.initView(this, "客户情报");
		listView = (ListView) findViewById(R.id.listView);

		adapter = new InterlligenceAdapter(this, reList);
		listView.setAdapter(adapter);

		selDate = (TextView) findViewById(R.id.selDate);
		dealTotal = (TextView) findViewById(R.id.dealTotal);
		dealNum = (TextView) findViewById(R.id.dealNum);
		selDate.setOnClickListener(this);
		dealTotal.setOnClickListener(this);
		dealNum.setOnClickListener(this);
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getId()) {
		case R.id.selDate: {
			//showPopupWindow(selDate);
		}
			break;
		case R.id.dealTotal: {
			dealNum.setTextColor(CustomerInterlligence.this.getResources()
					.getColor(R.color.c333333));
			dealTotal.setTextColor(CustomerInterlligence.this.getResources()
					.getColor(R.color.c6b6900));
		}
			break;
		case R.id.dealNum: {
			dealNum.setTextColor(CustomerInterlligence.this.getResources()
					.getColor(R.color.c6b6900));
			dealTotal.setTextColor(CustomerInterlligence.this.getResources()
					.getColor(R.color.c333333));
		}
			break;
		}
	}
	
	private void selDate(String date){
		selDate.setText(date);
	}
	
	private PopupWindow mPopupWin;
	private SelDateListAdapter popAdapter;

	private void showPopupWindow(View parent) {
		LayoutInflater layoutInflater = (LayoutInflater) this
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		final LinearLayout layout = (LinearLayout) layoutInflater.inflate(
				R.layout.classic_popup, null);

		mPopupWin = new PopupWindow(layout, LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT);

		ListView popList = (ListView) layout.findViewById(R.id.popList);
		popAdapter = new SelDateListAdapter(CustomerInterlligence.this, strList);
		popList.setAdapter(popAdapter);
		popList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				period = arg2;
				selDate(((KeyValueData) popAdapter.getItem(arg2)).getKey());
				initData();
				mPopupWin.dismiss();
			}
		});

		layout.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
/*				int height = layout.findViewById(R.id.pop).getTop();
				int y = (int) mPopupWin.getHeight();
				if (event.getAction() == MotionEvent.ACTION_UP) {
					if (y < height) {
						mPopupWin.dismiss();
					}
				}*/
				return true;
			}
		});

		mPopupWin.setFocusable(true);
		mPopupWin.setOutsideTouchable(false);

		// backgroundAlpha(0.5f);
		mPopupWin.setOnDismissListener(new OnDismissListener() {

			@Override
			public void onDismiss() {
				// backgroundAlpha(1f);

			}
		});
		WindowManager manager = (WindowManager) this
				.getSystemService(Context.WINDOW_SERVICE);
		int xpos = manager.getDefaultDisplay().getWidth()
				- mPopupWin.getWidth() - 20;

		mPopupWin.showAsDropDown(parent, 0, 18);
	}
}
