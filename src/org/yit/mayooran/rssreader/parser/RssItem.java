package org.yit.mayooran.rssreader.parser;

import java.io.Serializable;

public class RssItem implements Serializable {

	private static final long serialVersionUID = 1L;
	private String title = null;
	private String description = null;
	private String date = null;
	
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
	
	
}
