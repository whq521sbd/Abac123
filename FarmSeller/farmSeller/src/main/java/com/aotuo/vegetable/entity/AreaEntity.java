package com.aotuo.vegetable.entity;

import java.io.Serializable;
import java.util.List;


public class AreaEntity implements Serializable{
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private String type_id;
	private String type_name;
	private List<AreaEntity> next;

	public AreaEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AreaEntity(String type_id, String type_name) {
		super();
		this.type_id = type_id;
		this.type_name = type_name;
	}

	public List<AreaEntity> getNext() {
		return next;
	}

	public void setNext(List<AreaEntity> next) {
		this.next = next;
	}

	public String getType_id() {
		return type_id;
	}

	public void setType_id(String type_id) {
		this.type_id = type_id;
	}

	public String getType_name() {
		return type_name;
	}

	public void setType_name(String type_name) {
		this.type_name = type_name;
	}

}
