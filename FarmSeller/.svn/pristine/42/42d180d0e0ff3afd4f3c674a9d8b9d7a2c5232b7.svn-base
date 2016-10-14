package com.aotuo.vegetable.adapter;

import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.aotuo.vegetable.R;
import com.aotuo.vegetable.act.MainActivity;
import com.aotuo.vegetable.entity.TransEntity;
import com.aotuo.vegetable.util.CommonTools;
import com.aotuo.vegetable.util.FinalContent;

import net.tsz.afinal.FinalBitmap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * Created by Administrator on 2016/10/9.
 */

public class CarAdapter extends BaseAdapter {

    private List<TransEntity> dataList = new ArrayList();
    private Context ctx;
    private LayoutInflater inflater;
    private Handler sHandler;

    public CarAdapter(Context ctx, List<TransEntity> dataList, Handler sHandler) {
        this.ctx = ctx;
        inflater = LayoutInflater.from(ctx);
        this.dataList = dataList;
        this.sHandler = sHandler;
    }

    @Override
    public int getCount() {
        return dataList.size();
    }

    @Override
    public Object getItem(int position) {
        return dataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    /**
     * 对于ListView中的数据，显示每行数据都要调用一次该方法， convertView和ViewHolder同完成缓存机制 ，以优化性能
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        ViewHolder holder;
        if (view == null) {
//            LayoutInflater inflater = (LayoutInflater) ctx
//                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

                    //已经绑定购物车item布局
            view = inflater.inflate(R.layout.car_listview_item, null);


            holder = new ViewHolder();

            // 初始化item 控件
            holder.ck_checkbox = (CheckBox) view.findViewById(R.id.ck_checkbox);
            holder.tv_edit = (TextView) view.findViewById(R.id.tv_edit);
            holder.car_image = (ImageView) view.findViewById(R.id.car_image);
            holder.name = (TextView) view.findViewById(R.id.name);
            holder.price = (TextView) view.findViewById(R.id.price);
            holder.logRate = (TextView) view.findViewById(R.id.logRate);
            holder.distance = (TextView) view.findViewById(R.id.distance);
            holder.log = (TextView) view.findViewById(R.id.log);
            holder.total = (TextView) view.findViewById(R.id.total);
            holder.goodsTotal = (TextView) view.findViewById(R.id.goodsTotal);
            holder.booth = (TextView) view.findViewById(R.id.booth);
            holder.state = (TextView) view.findViewById(R.id.state);
            holder.address = (TextView) view.findViewById(R.id.address);
            holder.delName = (TextView) view.findViewById(R.id.delName);
            holder.weight = (TextView) view.findViewById(R.id.weight);

            holder.normalArea = (LinearLayout) view.findViewById(R.id.normalArea);
            holder.editArea = (LinearLayout) view.findViewById(R.id.editArea);

            holder.Purchaseweight = (EditText) view.findViewById(R.id.Purchaseweight);
            holder.tv_delete = (TextView) view.findViewById(R.id.tv_delete);

            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        /**
         *
         * TODO： 获取javabean数据之后，给item相应控件赋值  --->Done
         * */

        //Setview  获取数据源赋值给item控件
        TransEntity te = dataList.get(position);
        if (te.isSelect()) {
            if(!holder.ck_checkbox.isChecked())
                holder.ck_checkbox.setChecked(true);
        } else {
            if(holder.ck_checkbox.isChecked())
                holder.ck_checkbox.setChecked(false);
        }
        holder.name.setText(te.getGoodsTitle());
        holder.price.setText(te.getPrice() + "元/公斤");
        holder.weight.setText(te.getWeight() + "公斤");
        holder.logRate.setText(te.getLogRate() + "元/公斤公里");
        holder.distance.setText(te.getLogDis() + "公里");
        holder.log.setText(te.getLogMoney() + "元");
        holder.total.setText("合计："+te.getSum()+"元");
        double p = 0, w = 0;
        try {
            p = Double.parseDouble(te.getPrice());
            w = Double.parseDouble(te.getWeight());
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        holder.goodsTotal.setText(String.format("%.2f", p * w) + "元");
        holder.booth.setText(te.getBooth());
        //holder.state.setText(te.getS);
        //holder.address.setText(te.get);

        // 设置图片
        FinalBitmap.create(inflater.getContext()).display(holder.car_image, FinalContent.FinalUrl +te.getPicPath());


        holder.delName.setText(te.getGoodsTitle());
        //点击“编辑”之后，设置 tv_edit 控件的文字并且 显示或隐藏 相应布局
        holder.editArea.setTag(position);
        holder.normalArea.setTag(holder.editArea);
        holder.tv_edit.setTag(holder.normalArea);
        holder.tv_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View normalArea = (View) v.getTag();
                View editArea = (View) normalArea.getTag();
                Integer pos = 0;
                pos = (Integer) editArea.getTag();
                if(pos < dataList.size()){
                    TransEntity te = dataList.get(pos);
                    TextView tv = (TextView) v;
                    if(!te.isEdit()){
                        te.setEdit(true);
                        tv.setText("完成");
                        editArea.setVisibility(View.VISIBLE);
                        normalArea.setVisibility(View.GONE);
                    } else {
                        te.setEdit(false);
                        tv.setText("编辑");
                        editArea.setVisibility(View.GONE);
                        normalArea.setVisibility(View.VISIBLE);
                    }
                }
            }
        });
        holder.ck_checkbox.setTag(position);
        holder.ck_checkbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer pos = 0;
                pos = (Integer) v.getTag();
                if(pos < dataList.size()){
                    TransEntity te = dataList.get(pos);
                    if(((CheckBox) v).isChecked()){
                        te.setSelect(true);
                    } else {
                        te.setSelect(false);
                    }
                    sHandler.sendEmptyMessage(30);
                }
            }
        });
        holder.tv_delete.setTag(position);
        holder.tv_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer pos = 0;
                pos = (Integer) v.getTag();
                if(pos < dataList.size()){
                    //参数：Token,Num
                    HashMap<String, Object> param = new HashMap<String, Object>();
                    param.put("Token", CommonTools.getToken(ctx));
                    param.put("Num", dataList.get(pos).getNum());
                    MainActivity.postRequest(2, sHandler, "/GoodsA/TransTempDel", param);
                }
            }
        });
        //获取“总重量”
        String Purchaseweight = holder.Purchaseweight.getText().toString();
        return view;
    }


    private static class ViewHolder {
        public CheckBox ck_checkbox;
        public TextView delName;
        public TextView tv_edit;
        public ImageView car_image;
        public TextView name, price, weight, goodsTotal, logRate, distance, log, total, booth, state, address;
        public EditText Purchaseweight;
        public TextView tv_delete;
        public LinearLayout normalArea;
        public LinearLayout editArea;
    }
}
