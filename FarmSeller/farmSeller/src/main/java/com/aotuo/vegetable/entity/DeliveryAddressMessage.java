package com.aotuo.vegetable.entity;

import java.util.List;

public class DeliveryAddressMessage {
	private int count;// 返回数据总记录数
	private int curr_page;// 当前页数
	private int page_count;// 总页数
	private int prepage;// 每页显示条数
	private List<DeliveryAddressInfoMessage> list_data;// 具体数据列
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getCurr_page() {
		return curr_page;
	}
	public void setCurr_page(int curr_page) {
		this.curr_page = curr_page;
	}
	public int getPage_count() {
		return page_count;
	}
	public void setPage_count(int page_count) {
		this.page_count = page_count;
	}
	public int getPrepage() {
		return prepage;
	}
	public void setPrepage(int prepage) {
		this.prepage = prepage;
	}
	public List<DeliveryAddressInfoMessage> getList_data() {
		return list_data;
	}
	public void setList_data(List<DeliveryAddressInfoMessage> list_data) {
		this.list_data = list_data;
	}

}
