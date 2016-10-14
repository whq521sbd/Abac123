package com.aotuo.vegetable.main.fragment;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.aotuo.vegetable.R;
import com.aotuo.vegetable.act.CustomerActivity;
import com.aotuo.vegetable.act.MainActivity;
import com.aotuo.vegetable.adapter.AllGoodsAdapter;
import com.aotuo.vegetable.base.BaseFragment;
import com.aotuo.vegetable.entity.GoodsA;
import com.aotuo.vegetable.entity.GoodsMessage;
import com.aotuo.vegetable.util.CommonTools;
import com.aotuo.vegetable.util.MyToast;
import com.aotuo.vegetable.view.XListView;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class GoodsFragment extends BaseFragment {

    private Bundle savedInstanceState;
    private XListView listView;
    private View view;
    private View titleGap;
    private List<GoodsA> list = new ArrayList<GoodsA>();
    private AllGoodsAdapter adapter;
    private int curPage = 1;
    private int pageSize = 1;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.savedInstanceState = savedInstanceState;
        if (view == null) {
            view = inflater.inflate(R.layout.act_goods, null);

            initUI(view);
            getGoods(curPage);
        }
        ViewGroup parent = (ViewGroup) view.getParent();
        if (parent != null) {
            parent.removeView(view);
        }
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        curPage = 1;
        getGoods(curPage);
    }

    private void initUI(View view) {
        listView = (XListView) view.findViewById(R.id.listView);
        adapter = new AllGoodsAdapter(getActivity(), list);
        listView.setPullLoadEnable(true);
        listView.setPullRefreshEnable(true);
        listView.setXListViewListener(new XListView.IXListViewListener() {
            @Override
            public void onRefresh() {
                curPage = 1;
                getGoods(curPage);
            }

            @Override
            public void onLoadMore() {
                if (curPage <= pageSize)
                    getGoods(curPage + 1);
            }
        });
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int pos = position - 1;
                if (pos >= 0 && pos < list.size()) {
                    String meNum = CommonTools.getUserSn();
                    if(meNum != null && !meNum.equals(list.get(pos).getSellerNum())){
                        Intent intent = new Intent(getActivity(), CustomerActivity.class);
                        intent.putExtra("num", list.get(pos).getSellerNum());
                        startActivity(intent);
                    } else {
                        MyToast.showToast(getActivity(), "这是本店商品！", 2);
                    }

                }
            }
        });

        titleGap = view.findViewById(R.id.titleGap);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            titleGap.setVisibility(View.VISIBLE);
        } else {
            titleGap.setVisibility(View.GONE);
        }
    }

    /**
     * 参数：ClassID(0 所有)，Token, curPage,Market(可设置也可不设)
     */
    private void getGoods(int page) {
        HashMap<String, Object> param = new HashMap<String, Object>();
        param.put("Token", CommonTools.getToken(getActivity()));
        param.put("curPage", "" + page);
        param.put("ClassID", "0");
        MainActivity.postRequest(1, sHandler, "/GoodsA/GoodsMarket", param);
    }

    private Handler sHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            listView.stopLoadMore();
            listView.stopRefresh();
            if (msg.what == 1) {
                if (msg.arg1 > 0) {
                    GoodsMessage gm = null;
                    try {
                        gm = new Gson().fromJson(msg.obj.toString(), GoodsMessage.class);
                    } catch (JsonSyntaxException e) {
                        e.printStackTrace();
                    }
                    if (gm != null) {
                        if (gm.getCurPage() == 1) {
                            list.clear();
                        }
                        if (gm.getGoods() != null && gm.getGoods().size() > 0)
                            list.addAll(gm.getGoods());
                        pageSize = gm.getPageSize();
                        curPage = gm.getCurPage();
                    } else {
                        if (curPage == 1) {
                            list.clear();
                        }
                    }
                    adapter.notifyDataSetChanged();
                }
            }
        }
    };

    @Override
    public void handleCallBack(Message msg) {

    }
}
