package com.wy.form;

public class ArticleForm {
	private Integer id=-1;
	private Integer typeId=-1;
	private String title="";
	private String content="";
	private String phTime="";
	private Integer number=-1;
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getNumber() {
		return number;
	}
	public void setNumber(Integer number) {
		this.number = number;
	}
	public String getPhTime() {
		return phTime;
	}
	public void setPhTime(String phTime) {
		this.phTime = phTime;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Integer getTypeId() {
		return typeId;
	}
	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}


}
