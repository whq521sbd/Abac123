package com.aotuo.vegetable.entity;

import java.io.Serializable;

public class ClassifyMessage implements Serializable{
	/**
	 * "gc_id":"1128",                                索引ID
		"gc_name":"\u793c\u54c1\u6d3b\u52a8",           分类名称
		"type_id":"0",                               类型id
		"type_name":"",                              类型名称
		"gc_parent_id":"0",                          父id
		"commis_rate":"0",                       佣金比例
		"gc_sort":"0",                            排序
		"gc_virtual":"0",                       是否允许发布虚拟商品，1是，0否
		"gc_title":"",                           名称
		"gc_keywords":"",                        关键字
		"gc_description":"",                       描述
		"image":"",
		"text":"\u793c\u54c1"
	 */
	private String image;
	private String text;
	private String gc_id;
	private String gc_name;
	private String gc_img;
	private String gc_parent_id;
	private int gc_sort;
	public ClassifyMessage(String gc_id, String gc_name) {
		super();
		this.gc_id = gc_id;
		this.gc_name = gc_name;
	}

	public ClassifyMessage(String gc_id, String gc_name, String gc_parent_id,int gc_sort, String gc_img) {
		super();
		this.gc_id = gc_id;
		this.gc_name = gc_name;
		this.gc_parent_id = gc_parent_id;
		this.gc_sort=gc_sort;
		this.gc_img=gc_img;
	}

	public String getGc_parent_id() {
		return gc_parent_id;
	}

	public void setGc_parent_id(String gc_parent_id) {
		this.gc_parent_id = gc_parent_id;
	}

	public String getGc_id() {
		return gc_id;
	}
	public void setGc_id(String gc_id) {
		this.gc_id = gc_id;
	}
	public String getGc_name() {
		return gc_name;
	}
	public void setGc_name(String gc_name) {
		this.gc_name = gc_name;
	}

	public int getGc_sort() {
		return gc_sort;
	}

	public void setGc_sort(int gc_sort) {
		this.gc_sort = gc_sort;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getGc_img() {
		return gc_img;
	}

	public void setGc_img(String gc_img) {
		this.gc_img = gc_img;
	}
	
}
