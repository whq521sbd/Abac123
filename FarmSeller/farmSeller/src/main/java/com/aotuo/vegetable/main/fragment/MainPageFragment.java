package com.aotuo.vegetable.main.fragment;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.aotuo.vegetable.R;
import com.aotuo.vegetable.act.LocalBuyActivity;
import com.aotuo.vegetable.act.LocalSellActivity;
import com.aotuo.vegetable.act.MainActivity;
import com.aotuo.vegetable.act.NolocalActivity;
import com.aotuo.vegetable.adapter.GuidePagerAdapter;
import com.aotuo.vegetable.base.BaseFragment;
import com.aotuo.vegetable.entity.MessageFlag;
import com.aotuo.vegetable.util.CommonTools;
import com.aotuo.vegetable.util.NewsBroadcastReceiver;

import java.util.ArrayList;
import java.util.List;


public class MainPageFragment extends BaseFragment implements OnClickListener {
    public static final int TO_MAIN_PAGE = 23;
    private TextView local, nonlocal;
    private LayoutInflater inflater;
    private LinearLayout onlySell;
    private ViewGroup container;
    private Bundle savedInstanceState;
    private View view;
    private ViewPager vp_AdMainviewpager;
    private List<View> adlist;
    private ImageView iv_ad1, iv_ad2, iv_ad3;
    private ImageView iv_step1;
    private NewsBroadcastReceiver receiver;

    private  ImageView pointimg;
    private ImageView[] imageViews;
    private  LinearLayout ll_pointlayout;

    /*
    * 广告轮播
    * */

    private final int AUTO_MSG = 1;
    private final int HANDLE_MSG = AUTO_MSG + 1;
    private static final int PHOTO_CHANGE_TIME = 5000;//定时变量
    private int index = 0;

    public int indexadd(int i) {//递归方法，循环播放
        if (index == 2) {
            index = 0;
            return index;
        } else {

            index++;
        }
        return index;
    }

    private Handler mHandler = new Handler() {

        public void handleMessage(Message msg) {
            switch (msg.what) {
                case AUTO_MSG:

                    vp_AdMainviewpager.setCurrentItem(indexadd(0));//收到消息后设置当前要显示的图片


                    mHandler.sendEmptyMessageDelayed(AUTO_MSG, PHOTO_CHANGE_TIME);
                    break;
                case HANDLE_MSG:
                    mHandler.sendEmptyMessageDelayed(AUTO_MSG, PHOTO_CHANGE_TIME);
                    break;
                default:
                    break;
            }
        }

        ;
    };

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.inflater = inflater;
        this.container = container;
        this.savedInstanceState = savedInstanceState;
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_main_page, null);
            initUI(view);
        }

        ViewGroup parent = (ViewGroup) view.getParent();
        if (parent != null) {
            parent.removeView(view);
        }

       /*
       * 广告数据源
       * */
        adlist = new ArrayList<View>();
        LayoutInflater layoutInflater = getActivity().getLayoutInflater();
        adlist.add(layoutInflater.inflate(R.layout.ad1, null));
        adlist.add(layoutInflater.inflate(R.layout.ad2, null));
        adlist.add(layoutInflater.inflate(R.layout.ad3, null));
        GuidePagerAdapter adapter = new GuidePagerAdapter((ArrayList<View>) adlist);
        vp_AdMainviewpager.setAdapter(adapter);
        initpoint();

        return view;
    }

    private void initpoint() {
//        首先获得list里的个数
        //       int dots = adlist.size();

        imageViews = new ImageView[adlist.size()];
        LinearLayout.LayoutParams margin = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        // 设置每个小圆点距离左边的间距
        margin.setMargins(10, 0, 0, 0);
        for (int i = 0; i < adlist.size(); i++) {
            pointimg = new ImageView(getActivity());
            // 设置每个小圆点的宽高
            pointimg.setLayoutParams(new LinearLayout.LayoutParams(15, 15));
            imageViews[i] = pointimg;
            if (i == 0) {
                // 默认选中第一张图片
                imageViews[i]
                        .setBackgroundResource(R.drawable.redpoint);
            } else {
                // 其他图片都设置未选中状态
                imageViews[i]
                        .setBackgroundResource(R.drawable.whitepoint);
            }
            ll_pointlayout.addView(imageViews[i], margin);

        }
    }
    private void initUI(View view) {
//
//        //图片适配
//        iv_step1 = (ImageView) view.findViewById(R.id.iv_step1);
//        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.saoma_step1);
//        int bwidth = bitmap.getWidth();
//        int bHeight = bitmap.getHeight();
//        WindowManager wm = getActivity().getWindowManager();
//        int width = wm.getDefaultDisplay().getWidth();
//        //int width = Screen.getScreenWidth(this);
//        Log.e("====", bwidth + " " + bHeight + " " + width);
//        int height = width * bHeight / bwidth;
////        ViewGroup.LayoutParams para = iv_step1.getLayoutParams();
////        para.height = height;
////        iv_step1.setLayoutParams(para);
//        iv_step1.setLayoutParams(new RelativeLayout.LayoutParams(width,height));
//        iv_step1.setImageResource(R.drawable.saoma_step1);
//
////
        ll_pointlayout = (LinearLayout) view.findViewById(R.id.ll_pointlayout);
        iv_ad1 = (ImageView) view.findViewById(R.id.iv_ad1);
        iv_ad2 = (ImageView) view.findViewById(R.id.iv_ad2);
        iv_ad3 = (ImageView) view.findViewById(R.id.iv_ad3);
        local = (TextView) view.findViewById(R.id.local);
        nonlocal = (TextView) view.findViewById(R.id.nonlocal);
        local.setOnClickListener(this);
        nonlocal.setOnClickListener(this);
        vp_AdMainviewpager = (ViewPager) view.findViewById(R.id.vp_AdMainviewpager);
        mHandler.sendEmptyMessageDelayed(AUTO_MSG, PHOTO_CHANGE_TIME);
        if (CommonTools.isSell(getActivity())) {
            nonlocal.setText("跨场交易");
        } else {
            nonlocal.setText("离场交易");
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        IntentFilter filter = new IntentFilter();
        filter.addAction(NewsBroadcastReceiver.NEWS_ACTION);
        receiver = new NewsBroadcastReceiver();
        receiver.setINews(new NewsBroadcastReceiver.INews() {
            @Override
            public void update() {//接收消息

            }

            @Override
            public void update(String msg) {//接收消息
                MessageFlag messageFlag = MainActivity.getMessageFlag();
                messageFlag.clear();
                if ("hasNews".equals(msg)) {
                    messageFlag.setHasNews(true);
                } else if ("hasMsg".equals(msg)) {
                    messageFlag.setHasMsg(true);
                } else if ("hasMess".equals(msg)) {
                    messageFlag.setHasMess(true);
                }
                if (messageFlag.isHasNews())
                    newHandler.sendEmptyMessage(1);
                else if (messageFlag.isHasMsg())
                    newHandler.sendEmptyMessage(1);
                else if (messageFlag.isHasMess())
                    newHandler.sendEmptyMessage(1);
                else
                    newHandler.sendEmptyMessage(2);
            }
        });
        getActivity().registerReceiver(receiver, filter);
    }

    private Handler newHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1: {//有消息
                    //newsFlags.setVisibility(View.VISIBLE);
                }
                break;
                case 2: {//无消息
                   // newsFlags.setVisibility(View.GONE);
                }
                break;
            }
        }
    };

    @Override
    public void onStop() {
        super.onStop();
        getActivity().unregisterReceiver(receiver);
        receiver.setINews(null);
        receiver = null;
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.local: {
                if (CommonTools.isSell(getActivity())) {
                    Intent intent = new Intent(getActivity(), LocalSellActivity.class);
                    startActivityForResult(intent, TO_MAIN_PAGE);
                } else {
                    Intent intent = new Intent(getActivity(), LocalBuyActivity.class);
                    startActivityForResult(intent, TO_MAIN_PAGE);
                }
            }
            break;
            case R.id.nonlocal: {
                Intent intent = new Intent(getActivity(), NolocalActivity.class);
                startActivityForResult(intent, TO_MAIN_PAGE);
            }
            break;
        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    }

    @Override
    public void handleCallBack(Message msg) {

    }
}
