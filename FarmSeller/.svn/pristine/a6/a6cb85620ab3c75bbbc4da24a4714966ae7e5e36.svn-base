package com.aotuo.vegetable.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.aotuo.vegetable.R;
import com.aotuo.vegetable.act.MainActivity;
import com.aotuo.vegetable.entity.RecordEntity;
import com.aotuo.vegetable.util.CommonTools;
import com.aotuo.vegetable.util.FinalContent;

import net.tsz.afinal.FinalBitmap;

import java.util.ArrayList;
import java.util.List;

public class RecordAdapter extends BaseAdapter {
	private List<RecordEntity> list = new ArrayList<RecordEntity>();
	private LayoutInflater inflater;

	public RecordAdapter(Context context, List<RecordEntity> reList) {
		super();
		// TODO Auto-generated constructor stub
		this.list = reList;
		inflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return list.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public View getView(int arg0, View v, ViewGroup arg2) {
		// TODO Auto-generated method stub
		ViewHolder vh = new ViewHolder();
		if (v == null) {
			v = inflater.inflate(R.layout.recorder_list_item, null);
			vh.txtDate = (TextView) v.findViewById(R.id.txtDate);
			vh.name = (TextView) v.findViewById(R.id.name);
			vh.price = (TextView) v.findViewById(R.id.price);
			vh.weight = (TextView) v.findViewById(R.id.weight);
			vh.total = (TextView) v.findViewById(R.id.total);
			vh.log = (TextView) v.findViewById(R.id.log);
			vh.booth = (TextView) v.findViewById(R.id.booth);
			vh.address = (TextView) v.findViewById(R.id.address);
			vh.state = (TextView) v.findViewById(R.id.state);
			vh.logRate = (TextView) v.findViewById(R.id.logRate);
			vh.goodsTotal = (TextView) v.findViewById(R.id.goodsTotal);
			vh.distance = (TextView) v.findViewById(R.id.distance);
			vh.txtDistance = (TextView) v.findViewById(R.id.txtDistance);
			vh.txtLograte = (TextView) v.findViewById(R.id.txtLograte);
			vh.txtLog = (TextView) v.findViewById(R.id.txtLog);
			vh.img = (ImageView) v.findViewById(R.id.img);
			vh.ordernumber = (TextView) v.findViewById(R.id.ordernumber);

			v.setTag(vh);
		} else {
			vh = (ViewHolder) v.getTag();
		}
		RecordEntity re = list.get(arg0);
		vh.txtDate.setText(re.getTime());
		vh.name.setText(re.getGoodsTitle());
		vh.price.setText(re.getPrice() + "元/公斤");
		vh.weight.setText(re.getWeight() + "公斤");

		vh.booth.setText(re.getBooth());
		vh.address.setText(re.getMarket());
		vh.state.setText(re.getState());

		vh.log.setText("运费：  " + re.getLogMoney() + "元");
		vh.logRate.setText(re.getLogRate()+"/公斤公里");
		vh.goodsTotal.setText("商品金额：" + re.getGoodsMoney()+"元");
		vh.distance.setText(re.getLogDis() + "公里");
		vh.ordernumber.setText(re.getNum());

		if(CommonTools.getUserSn().equals(re.getSellerNum())){//自己是卖方
			vh.logRate.setVisibility(View.INVISIBLE);
			vh.goodsTotal.setVisibility(View.GONE);
			vh.log.setVisibility(View.GONE);
			vh.distance.setVisibility(View.GONE);

			vh.txtDistance.setVisibility(View.GONE);
			vh.txtLograte.setVisibility(View.GONE);
			vh.txtLog.setVisibility(View.GONE);

			vh.total.setText("合计：  " + re.getGoodsMoney() + "元");
		} else {//自己是买方
			vh.logRate.setVisibility(View.VISIBLE);
			vh.goodsTotal.setVisibility(View.VISIBLE);
			vh.log.setVisibility(View.VISIBLE);
			vh.distance.setVisibility(View.VISIBLE);

			vh.txtDistance.setVisibility(View.VISIBLE);
			vh.txtLograte.setVisibility(View.VISIBLE);
			vh.txtLog.setVisibility(View.VISIBLE);

			vh.total.setText("合计：  " + re.getSum() + "元");
		}
		FinalBitmap.create(inflater.getContext()).display(vh.img, FinalContent.FinalUrl + re.getPicPath());
		return v;
	}

	private class ViewHolder {
		TextView txtDate;
		TextView name;
		TextView price;
		TextView weight;
		TextView total;
		TextView log;
		TextView booth;
		TextView address;
		TextView state;
		TextView logRate;
		TextView distance;
		TextView goodsTotal;

		TextView txtDistance;
		TextView txtLograte;
		TextView txtLog;
		ImageView img;
		public TextView ordernumber;
	}
}
