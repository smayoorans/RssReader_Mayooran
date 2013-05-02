package org.yit.mayooran.rssreader;


import org.yit.mayooran.rssreader.parser.DomParser;
import org.yit.mayooran.rssreader.parser.RssFeed;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;


public class MainRssReaderActivity extends Activity {

	private String RSSFEEDURL = "http://www.mobilenations.com/rss/mb.xml";
	RssFeed feed;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_rss_reader);
		
		new AsyncLoadXMLFeed().execute();
	}

	private class AsyncLoadXMLFeed extends AsyncTask<Void, Void, Void> {

		@Override
		protected Void doInBackground(Void... params) {

			// Obtain feed
			DomParser myParser = new DomParser();
			feed = myParser.parseXml(RSSFEEDURL);
			return null;

		}

		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);

			Bundle bundle = new Bundle();
			bundle.putSerializable("feed", feed);

			// launch List activity
			Intent intent = new Intent(MainRssReaderActivity.this, ListRssReaderActivity.class);
			intent.putExtras(bundle);
			startActivity(intent);

			// kill this activity
			finish();
		}

	}

}
