package com.aotuo.vegetable.act;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.PopupWindow.OnDismissListener;
import android.widget.ScrollView;
import android.widget.TextView;

import com.aotuo.vegetable.R;
import com.aotuo.vegetable.adapter.ClassicPopAdapter;
import com.aotuo.vegetable.entity.ClassicData;
import com.aotuo.vegetable.entity.ClassifyMessage;
import com.aotuo.vegetable.main.fragment.ClassicFragment;
import com.aotuo.vegetable.main.fragment.ClassicFragment.IClassicClick;
import com.aotuo.vegetable.sqlite.ClassifyDBDao;
import com.aotuo.vegetable.util.CommonTools;
import com.aotuo.vegetable.util.SystemStatusManager;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.util.ArrayList;
import java.util.List;

public class ClassicActivity extends FragmentActivity implements
        OnClickListener {
    private ImageView back;
    private TextView txtClassic;
    private TextView toolsTextViews[];
    private View views[];
    private LinearLayout toolsLayout;
    private List<ClassifyMessage> firstClassicList = new ArrayList<ClassifyMessage>();
    private List<List<ClassifyMessage>> secAndThridClassicList = new ArrayList<List<ClassifyMessage>>();
    private List<ClassifyMessage> secClassicList = new ArrayList<ClassifyMessage>();
    private List<ClassifyMessage> thirdClassicList = new ArrayList<ClassifyMessage>();
    private LayoutInflater mInflater;
    private ViewPager shop_pager;
    private int currentItem = 0;
    private ClassicAdapter classicAdapter;
    private View view;
    private ScrollView scrollView;
    private int scrllViewWidth = 0, scrollViewMiddle = 0;
    private String gc_id;
    private ClassicData classicData;
    private ClassifyDBDao dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setTranslucentStatus();
        setContentView(R.layout.act_classic);
        dao = new ClassifyDBDao(this);
        getData();
        initUI();
        initPager();
    }

    private void getData() {
        String oldVer = CommonTools.getOldClassVer(ClassicActivity.this);
        String ver = CommonTools.getClassVer(ClassicActivity.this);
        if (oldVer == null || oldVer != ver) {
            MainActivity.getRequest(1, sHandler, "/GoodsA/AllClass", null);
        } else {
            initTestData();
        }
    }

    private Handler sHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {
                if (msg.arg1 > 0) {
                    classicData = null;
                    try {
                        classicData = new Gson().fromJson(msg.obj.toString(), ClassicData.class);
                    } catch (JsonSyntaxException e) {
                        e.printStackTrace();
                    }
                    if (classicData != null) {
                        if (classicData.getClassX().size() == 0) {
                            //finish();
                        } else {
                            dao.deletetable();
                            dao.addClassifyAll(classicData);
                            CommonTools.setOldClassVer(ClassicActivity.this, classicData.getVer());
                        }
                    }
                    initTestData();
                }
            }
        }
    };

    private void initTestData() {
        secAndThridClassicList.clear();
        firstClassicList.clear();
        secClassicList.clear();

        firstClassicList = dao.getGroupsClassify("0");
        for (ClassifyMessage cm : firstClassicList) {
            List<ClassifyMessage> lcm = dao.getGroupsClassify(cm.getGc_id());
            if (lcm == null || lcm.size() == 0)
                secAndThridClassicList.add(new ArrayList<ClassifyMessage>());
            else
                secAndThridClassicList.add(lcm);
        }
        if (firstClassicList.size() > 0) {
            secClassicList.addAll(dao.getGroupsClassify(firstClassicList.get(0).getGc_id()));
            if (secClassicList.size() > 0) {
                thirdClassicList = dao.getGroupsClassify(secClassicList.get(0).getGc_id());
                if (thirdClassicList == null)
                    thirdClassicList = new ArrayList<ClassifyMessage>();
            }
        }
        popClick(0);
    }

    private void initUI() {
        // TODO Auto-generated method stub
        scrollView = (ScrollView) findViewById(R.id.classify_scrlllview);

        back = (ImageView) findViewById(R.id.back);
        back.setOnClickListener(this);
        txtClassic = (TextView) findViewById(R.id.txtClassic);
        txtClassic.setOnClickListener(this);

        classicAdapter = new ClassicAdapter(getSupportFragmentManager());
        toolsLayout = (LinearLayout) findViewById(R.id.classify);
        mInflater = LayoutInflater.from(this);
        initTab();
    }

    private void initTab() {
        toolsTextViews = new TextView[thirdClassicList.size()];
        views = new View[thirdClassicList.size()];
        toolsLayout.removeAllViews();

        for (int i = 0; i < secClassicList.size(); i++) {
            View view = mInflater.inflate(R.layout.classify_item_layout, null);
            view.setId(i);
            view.setOnClickListener(toolsItemListener);
            TextView textView = (TextView) view.findViewById(R.id.text);
            textView.setText(secClassicList.get(i).getGc_name());
            toolsLayout.addView(view);
            toolsTextViews[i] = textView;
            views[i] = view;
        }
        changeTextColor(0);
    }

    private OnClickListener toolsItemListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            shop_pager.setCurrentItem(v.getId());
        }
    };

    private void initPager() {
        shop_pager = (ViewPager) findViewById(R.id.classify_viewpager);
        shop_pager.setAdapter(classicAdapter);
        shop_pager.setOnPageChangeListener(onPageChangeListener);
    }

    private OnPageChangeListener onPageChangeListener = new OnPageChangeListener() {
        @Override
        public void onPageSelected(int arg0) {
            if (shop_pager.getCurrentItem() != arg0)
                shop_pager.setCurrentItem(arg0);
            if (currentItem != arg0) {
                changeTextColor(arg0);
                changeTextLocation(arg0);
            }
            currentItem = arg0;
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {
        }

        @Override
        public void onPageScrollStateChanged(int arg0) {
        }
    };

    private class ClassicAdapter extends FragmentPagerAdapter {
        public ClassicAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int arg0) {
            Fragment fragment = null;
            Bundle bundle = new Bundle();
            String str = "";

            int calPos = arg0;
            for (int i = 0; i < secAndThridClassicList.size() && !isEqual(thirdClassicList, secAndThridClassicList.get(i));
                 i++) {
                if (secAndThridClassicList == null || i >= secAndThridClassicList.size()
                        || secAndThridClassicList.get(i) == null || secAndThridClassicList.get(i).size() == 0) {
                    calPos -= 1;
                } else
                    calPos -= secAndThridClassicList.get(i).size();
            }

            fragment = new ClassicFragment();
            ((ClassicFragment) fragment).setIClassicClick(icClick);
            str = secClassicList.get(calPos).getGc_id();
            gc_id = secClassicList.get(calPos).getGc_id();
            bundle.putString("id", gc_id);
            bundle.putString("typename", str);
            fragment.setArguments(bundle);
            return fragment;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            // TODO Auto-generated method stub
            Log.i("AAAAAAAA", "instantiateItem    position=" + position
                    + "  id=" + thirdClassicList.get(position).getGc_id());
            int calPos = position;
            for (int i = 0; i < secAndThridClassicList.size() && !isEqual(thirdClassicList, secAndThridClassicList.get(i));
                 i++) {
                if (secAndThridClassicList == null || i >= secAndThridClassicList.size()
                        || secAndThridClassicList.get(i) == null || secAndThridClassicList.get(i).size() == 0) {
                    calPos += 1;
                } else
                    calPos += secAndThridClassicList.get(i).size();
            }

            ClassicFragment f = (ClassicFragment) super.instantiateItem(
                    container, calPos);

            return f;
        }

        @Override
        public int getItemPosition(Object object) {
            // TODO Auto-generated method stub
            return POSITION_NONE;
        }

        @Override
        public int getCount() {
            return thirdClassicList == null ? 0 : thirdClassicList.size();
        }
    }

    private boolean isEqual(List<ClassifyMessage> list1, List<ClassifyMessage> list2){
        if(list1.containsAll(list2) && list2.containsAll(list1))
            return true;
        return false;
    }

    private void changeTextColor(int id) {
        for (int i = 0; i < toolsTextViews.length; i++) {
            if (i != id) {
                toolsTextViews[i].setBackgroundResource(R.color.my_third_color);
                toolsTextViews[i].setTextColor(0xff333333);
            }
        }
        if (id < toolsTextViews.length) {
            toolsTextViews[id].setBackgroundResource(android.R.color.white);
            toolsTextViews[id].setTextColor(0xfffd3636);
        }
    }

    private void changeTextLocation(int clickPosition) {
        int x = (views[clickPosition].getTop() - getScrollViewMiddle() + (getViewheight(views[clickPosition]) / 2));
        scrollView.smoothScrollTo(0, x);
    }

    private int getScrollViewMiddle() {
        if (scrollViewMiddle == 0)
            scrollViewMiddle = getScrollViewheight() / 2;
        return scrollViewMiddle;
    }

    private int getScrollViewheight() {
        if (scrllViewWidth == 0)
            scrllViewWidth = scrollView.getBottom() - scrollView.getTop();
        return scrllViewWidth;
    }

    private int getViewheight(View view) {
        return view.getBottom() - view.getTop();
    }

    @Override
    public void onClick(View arg0) {
        // TODO Auto-generated method stub
        if (arg0.getId() == R.id.back) {
            finish();
        } else if (arg0.getId() == R.id.txtClassic) {
            showPopupWindow(txtClassic);
        }
    }

    private void popClick(int index) {
        txtClassic.setText(firstClassicList.get(index).getGc_name());
        thirdClassicList.clear();
        thirdClassicList.addAll(secAndThridClassicList.get(index));
        secClassicList.clear();
        List<ClassifyMessage> lcm = dao.getGroupsClassify(firstClassicList.get(index).getGc_id());
        if (lcm == null)
            secClassicList.addAll(new ArrayList<ClassifyMessage>());
        else
            secClassicList.addAll(lcm);
        initTab();
        currentItem = 0;
        classicAdapter = new ClassicAdapter(getSupportFragmentManager());
        shop_pager.setAdapter(classicAdapter);
        classicAdapter.notifyDataSetChanged();
    }

    private PopupWindow mPopupWin;
    private ClassicPopAdapter popAdapter;
    private LinearLayout layout;

    private void showPopupWindow(View parent) {
        LayoutInflater layoutInflater = (LayoutInflater) this
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        layout = (LinearLayout) layoutInflater.inflate(
                R.layout.classic_popup, null);

        mPopupWin = new PopupWindow(layout, LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT);

        ListView popList = (ListView) layout.findViewById(R.id.popList);
        popAdapter = new ClassicPopAdapter(ClassicActivity.this, firstClassicList);
        popList.setAdapter(popAdapter);
        popList.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                // TODO Auto-generated method stub
                popClick(arg2);
                mPopupWin.dismiss();
            }
        });

        layout.setOnTouchListener(new OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // TODO Auto-generated method stub
                int height = layout.findViewById(R.id.popList).getTop();
                int y = (int) mPopupWin.getHeight();
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (y < height) {
                        mPopupWin.dismiss();
                    }
                }
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

    private IClassicClick icClick = new IClassicClick() {

        @Override
        public void click(ClassifyMessage ce) {
            // TODO Auto-generated method stub
            Intent intent = new Intent(ClassicActivity.this, CreateGoods.class);
            intent.putExtra("ClassXXXID", ce.getGc_id());
            intent.putExtra("ClassName", ce.getGc_name());
            //setResult(RESULT_OK, intent);
            //finish();
            startActivityForResult(intent, 123);
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 123 && resultCode == RESULT_OK) {
            setResult(RESULT_OK);
            finish();
        } else {

        }
    }

    /**
     * 设置状态栏背景状态
     */
    private void setTranslucentStatus() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window win = getWindow();
            WindowManager.LayoutParams winParams = win.getAttributes();
            final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
            winParams.flags |= bits;
            win.setAttributes(winParams);
        }
        SystemStatusManager tintManager = new SystemStatusManager(this);
        tintManager.setStatusBarTintEnabled(true);
        tintManager.setStatusBarTintResource(0);//状态栏无背景
    }
}
