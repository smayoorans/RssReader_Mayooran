package org.yit.mayooran.rssreader.parser;

import java.io.Serializable;
import java.util.List;
import java.util.Vector;

public class RssFeed implements Serializable{

	private static final long serialVersionUID = 1L;
	private int itemcount = 0;
	private List<RssItem> itemlist;

	RssFeed() {
		itemlist = new Vector<RssItem>(0);
	}

	void addItem(RssItem item) {
		itemlist.add(item);
		itemcount++;
	}

	public RssItem getItem(int location) {
		return itemlist.get(location);
	}

	public int getItemCount() {
		return itemcount;
	}
}
