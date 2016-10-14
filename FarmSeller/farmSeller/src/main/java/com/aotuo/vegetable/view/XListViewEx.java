package com.aotuo.vegetable.view;

import java.util.List;

import android.widget.BaseAdapter;

import com.aotuo.vegetable.entity.BaseTMessage;
import com.aotuo.vegetable.view.XListView.IXListViewListener;

public abstract class XListViewEx<T> implements IXListViewListener{
	private List<T> list;
	private XListView listView;
	private BaseAdapter adapter;
	private int count, curr_page, page_count, perpage;
	
	public void setListView(XListView listView, BaseAdapter adapter){
		this.listView = listView;
		this.adapter = adapter;
	}
	
	public void setData(BaseTMessage<T> btm, List<T> listData){
		count = btm.getCount();
		curr_page = btm.getCurr_page();
		page_count = btm.getPage_count();
		perpage = btm.getPerpage();
		list = btm.getList_data();

		if (list != null) {
			listView.stopRefresh();
			if (list.size() == 0 && curr_page == 1) {
				listView.setPullLoadEnable(false);
			} else if (list.size() == 0 && curr_page != 1) {
				listView.setPullLoadEnable(false);
			}
			if (list.size() < 8) {
				listView.setPullLoadEnable(false);
			} else {
				listView.setPullLoadEnable(true);
			}
			if (curr_page == 1) {
				listData.clear();
			}
			
			listData.addAll(list);
			adapter.notifyDataSetChanged();
		} else if (list == null && curr_page == 1) {
			listData.clear();
			adapter.notifyDataSetChanged();
		} else if (list == null && curr_page != 1) {
			listView.setPullLoadEnable(false);
		}
	}
	
	public void onLoad() {
		listView.stopRefresh();
		listView.stopLoadMore();
	}
	
	public void onFresh(){
		listView.onFresh();
	}
	
	@Override
	public void onRefresh() {
		// TODO Auto-generated method stub
		curr_page = 1;
		loadData(curr_page);
		listView.setPullLoadEnable(true);
	}

	@Override
	public void onLoadMore() {
		// TODO Auto-generated method stub
		curr_page++;
		try {
			if (((long) count / (long) perpage) + 1 > (long) curr_page) {
				loadData(curr_page);
			} else if ((count / perpage) + 1 == curr_page) {
				loadData(curr_page);
				listView.setPullLoadEnable(false);
			}
		} catch (Exception e) {
			e.printStackTrace();
			listView.setPullLoadEnable(false);
		}
	}
	
	public abstract void loadData(int curPage);
}
