package org.yit.mayooran.rssreader.parser;

import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;


public class DomParser {

	private RssFeed feed = new RssFeed();
	
	public RssFeed parseXml(String xml) {
		
		URL url = null;
		try {
			url = new URL(xml);
		} catch (MalformedURLException e1) {
			e1.printStackTrace();
		}
		
		try {
			// Create required instances
			DocumentBuilderFactory dbf;
			dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();

			// Parse the xml
			Document doc = db.parse(new InputSource(url.openStream()));
			doc.getDocumentElement().normalize();

			// Get all <item> tags.
			NodeList nl = doc.getElementsByTagName("item");
			int length = nl.getLength();

			for (int i = 0; i < length; i++) {
				Node currentNode = nl.item(i);
				RssItem item = new RssItem();

				NodeList nchild = currentNode.getChildNodes();
				int clength = nchild.getLength();

				// Get the required elements from each Item
				for (int j = 1; j < clength; j = j + 2) {

					Node thisNode = nchild.item(j);
					String theString = null;
					String nodeName = thisNode.getNodeName();

					theString = nchild.item(j).getFirstChild().getNodeValue();

					if (theString != null) {
						if ("title".equals(nodeName)) {
							
							item.setTitle(theString);
						}

						else if ("description".equals(nodeName)) {
							item.setDescription(theString);														
						}

						else if ("pubDate".equals(nodeName)) {							
							String formatedDate = theString.replace(" +0000","");
							item.setDate(formatedDate);
						}

					}
				}

				// add item to the list
				feed.addItem(item);
			}

		} catch (Exception e) {
		}
		
		return feed;
	}
}
