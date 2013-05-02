package org.yit.mayooran.rssreader;



import org.yit.mayooran.rssreader.parser.RssFeed;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class ListRssReaderActivity extends Activity{

	Application myApp;
	RssFeed feed;
	ListView lv;
	CustomListAdapter adapter;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.feed_list_rss_reader);

		myApp = getApplication();

		// Get feed form the file
		feed = (RssFeed) getIntent().getExtras().get("feed");

		// Initialize the variables:
		lv = (ListView) findViewById(R.id.listView);
		lv.setVerticalFadingEdgeEnabled(true);

		// Set an Adapter to the ListView
		adapter = new CustomListAdapter(this);
		lv.setAdapter(adapter);

		// Set on item click listener to the ListView
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,long arg3) {
				// actions to be performed when a list item clicked
				int pos = arg2;

				Bundle bundle = new Bundle();
				bundle.putSerializable("feed", feed);
				Intent intent = new Intent(ListRssReaderActivity.this,DetailRssReaderActivity.class);
				intent.putExtras(bundle);
				intent.putExtra("pos", pos);
				startActivity(intent);

			}
		});

	}
	
	
	
	class CustomListAdapter extends BaseAdapter {

		private LayoutInflater layoutInflater;
		

		public CustomListAdapter(ListRssReaderActivity activity) {

			layoutInflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
		}

		@Override
		public int getCount() {

			// Set the total list item count
			return feed.getItemCount();
		}

		@Override
		public Object getItem(int position) {
			return position;
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {

			// Inflate the item layout and set the views
			View listItem = convertView;
			int pos = position;
			if (listItem == null) {
				listItem = layoutInflater.inflate(R.layout.list_item_rss_reader, null);
			}

			// Initialize the views in the layout			
			TextView tvTitle = (TextView) listItem.findViewById(R.id.title);
			TextView tvDate = (TextView) listItem.findViewById(R.id.date);

			// Set the views in the layout			
			tvTitle.setText(feed.getItem(pos).getTitle());
			tvDate.setText(feed.getItem(pos).getDate());

			return listItem;
		}

	}
	
}
