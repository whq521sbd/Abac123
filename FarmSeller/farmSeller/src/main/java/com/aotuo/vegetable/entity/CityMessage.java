package com.aotuo.vegetable.entity;

public class CityMessage {
	private int area_id;
	private String area_name;
	private String area_parent_id;
	private String area_deep;
	private String area_region;
	private String letter;
	private boolean isSelect;
	public CityMessage() {
		super();
	}
	public CityMessage(int area_id, String area_name, String area_parent_id,
			String area_deep, String letter, String area_region) {
		super();
		this.area_id = area_id;
		this.area_name = area_name;
		this.area_parent_id = area_parent_id;
		this.area_deep = area_deep;
		this.letter = letter;
		this.area_region = area_region;
	}
	public int getArea_id() {
		return area_id;
	}
	public void setArea_id(int area_id) {
		this.area_id = area_id;
	}
	public String getArea_name() {
		return area_name;
	}
	public void setArea_name(String area_name) {
		this.area_name = area_name;
	}
	public String getArea_parent_id() {
		return area_parent_id;
	}
	public void setArea_parent_id(String area_parent_id) {
		this.area_parent_id = area_parent_id;
	}
	public String getArea_deep() {
		return area_deep;
	}
	public void setArea_deep(String area_deep) {
		this.area_deep = area_deep;
	}
	public String getArea_region() {
		return area_region;
	}
	public void setArea_region(String area_region) {
		this.area_region = area_region;
	}
	public boolean isSelect() {
		return isSelect;
	}
	public void setSelect(boolean isSelect) {
		this.isSelect = isSelect;
	}
	public String getLetter() {
		return letter;
	}
	public void setLetter(String letter) {
		this.letter = letter;
	}
	
}
