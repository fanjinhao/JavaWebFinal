package com.wy.form;

public class DiscussForm {
	private Integer id=-1;
	private String discussTitle=null;
	private String discussContent=null;
	private String discussTime=null;
	
	
	public String getDiscussContent() {
		return discussContent;
	}
	public void setDiscussContent(String discussContent) {
		this.discussContent = discussContent;
	}
	public String getDiscussTime() {
		return discussTime;
	}
	public void setDiscussTime(String discussTime) {
		this.discussTime = discussTime;
	}
	public String getDiscussTitle() {
		return discussTitle;
	}
	public void setDiscussTitle(String discussTitle) {
		this.discussTitle = discussTitle;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
}
