package com.dm.search.model;

import org.apache.solr.client.solrj.beans.Field;

public class Index {

	// @Field setter方法上添加Annotation也是可以的

	private String id;

	@Field
	private String title;

	@Field
	private String content;

	@Field
	private String url;

	@Field
	private String channel;

	@Field
	private String auther;

	public String getId() {

		return id;

	}

	@Field
	public void setId(String id) {

		this.id = id;

	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public String getAuther() {
		return auther;
	}

	public void setAuther(String auther) {
		this.auther = auther;
	}

}
