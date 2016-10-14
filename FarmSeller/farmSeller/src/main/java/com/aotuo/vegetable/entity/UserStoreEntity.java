package com.aotuo.vegetable.entity;

public class UserStoreEntity {
	private String member_id;
	private String member_name;
	private String member_nickname;
	private String member_avatar;
	private String store_name;
	private String store_member_id;
	private String store_label;
	public String getMember_name() {
		return member_name;
	}
	public void setMember_name(String member_name) {
		this.member_name = member_name;
	}
	public String getMember_avatar() {
		return member_avatar;
	}
	public void setMember_avatar(String member_avatar) {
		this.member_avatar = member_avatar;
	}
	public String getStore_name() {
		return store_name;
	}
	public void setStore_name(String store_name) {
		this.store_name = store_name;
	}
	public String getStore_label() {
		return store_label;
	}
	public void setStore_label(String store_label) {
		this.store_label = store_label;
	}
	public String getMember_nickname() {
		return member_nickname;
	}
	public void setMember_nickname(String member_nickname) {
		this.member_nickname = member_nickname;
	}
	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	public String getStore_member_id() {
		return store_member_id;
	}
	public void setStore_member_id(String store_member_id) {
		this.store_member_id = store_member_id;
	}
	
}
