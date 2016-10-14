package com.aotuo.vegetable.entity;

import java.util.List;

public class BaseTMessage<T> {
	private int count;//返回数据总记录数
	private int curr_page;//当前页数
	private int page_count;//总页数
	private int perpage;//每页显示条数
	
	private int countnum;//返回数据总记录数
	private int curpage;//当前页数
	private int pagezs;//总页数
	private int page;//每页显示条数
	
	private List<T> list_data;
	private List<T> newlist;
	
	public void luangao(){
		list_data = newlist;
		count = countnum;
		curr_page = curpage;
		page_count = pagezs;
		perpage = page;
	}
	
	public BaseTMessage() {
		super();
	}
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
	public int getPerpage() {
		return perpage;
	}
	public void setPerpage(int perpage) {
		this.perpage = perpage;
	}
	public List<T> getList_data() {
		return list_data;
	}
	public void setList_data(List<T> list_data) {
		this.list_data = list_data;
	}
	public int getCountnum() {
		return countnum;
	}
	public void setCountnum(int countnum) {
		this.countnum = countnum;
	}
	public int getCurpage() {
		return curpage;
	}
	public void setCurpage(int curpage) {
		this.curpage = curpage;
	}
	public int getPagezs() {
		return pagezs;
	}
	public void setPagezs(int pagezs) {
		this.pagezs = pagezs;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public List<T> getNewlist() {
		return newlist;
	}
	public void setNewlist(List<T> newlist) {
		this.newlist = newlist;
	}
	
}
