package com.aotuo.vegetable.entity;

public class InformDataMessage {
	private int message_id;// 消息id
	private String message_parent_id;// 回复短消息message_id
	private String from_member_id;// 短消息发送人
	private String to_member_id;// 短消息接收人
	private String message_title;// 短消息标题
	private String message_body;// 短消息内容
	private String message_time;// 短消息发送时间（s）
	private String message_update_time;// 短消息回复更新时间
	private String message_open;//'短消息打开状态', 1为已看，0表示未看
	private String message_state;// 短消息状态，0为正常状态，1为发送人删除状态，2为接收人删除状态
	private String message_type;// 0为私信、1为系统消息、2为留言
	private String read_member_id;// 已经读过该消息的会员id
	private String del_member_id;// 已经删除该消息的会员id
	private String message_ismore;// 站内信是否为一条发给多个用户 0为否 1为多条
	private String from_member_name;// 发信息人用户名
	private String to_member_name;// 接收人用户名
	private String order_id;// 订单id

	public int getMessage_id() {
		return message_id;
	}

	public void setMessage_id(int message_id) {
		this.message_id = message_id;
	}

	public String getMessage_parent_id() {
		return message_parent_id;
	}

	public void setMessage_parent_id(String message_parent_id) {
		this.message_parent_id = message_parent_id;
	}

	public String getFrom_member_id() {
		return from_member_id;
	}

	public void setFrom_member_id(String from_member_id) {
		this.from_member_id = from_member_id;
	}

	public String getTo_member_id() {
		return to_member_id;
	}

	public void setTo_member_id(String to_member_id) {
		this.to_member_id = to_member_id;
	}

	public String getMessage_title() {
		return message_title;
	}

	public void setMessage_title(String message_title) {
		this.message_title = message_title;
	}

	public String getMessage_body() {
		return message_body;
	}

	public void setMessage_body(String message_body) {
		this.message_body = message_body;
	}

	public String getMessage_time() {
		return message_time;
	}

	public void setMessage_time(String message_time) {
		this.message_time = message_time;
	}

	public String getMessage_update_time() {
		return message_update_time;
	}

	public void setMessage_update_time(String message_update_time) {
		this.message_update_time = message_update_time;
	}

	public String getMessage_open() {
		return message_open;
	}

	public void setMessage_open(String message_open) {
		this.message_open = message_open;
	}

	public String getMessage_state() {
		return message_state;
	}

	public void setMessage_state(String message_state) {
		this.message_state = message_state;
	}

	public String getMessage_type() {
		return message_type;
	}

	public void setMessage_type(String message_type) {
		this.message_type = message_type;
	}

	public String getRead_member_id() {
		return read_member_id;
	}

	public void setRead_member_id(String read_member_id) {
		this.read_member_id = read_member_id;
	}

	public String getDel_member_id() {
		return del_member_id;
	}

	public void setDel_member_id(String del_member_id) {
		this.del_member_id = del_member_id;
	}

	public String getMessage_ismore() {
		return message_ismore;
	}

	public void setMessage_ismore(String message_ismore) {
		this.message_ismore = message_ismore;
	}

	public String getFrom_member_name() {
		return from_member_name;
	}

	public void setFrom_member_name(String from_member_name) {
		this.from_member_name = from_member_name;
	}

	public String getTo_member_name() {
		return to_member_name;
	}

	public void setTo_member_name(String to_member_name) {
		this.to_member_name = to_member_name;
	}

	public String getOrder_id() {
		return order_id;
	}

	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}

}
