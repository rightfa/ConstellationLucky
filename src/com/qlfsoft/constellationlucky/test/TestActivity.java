package com.qlfsoft.constellationlucky.test;
import java.util.ArrayList;
import java.util.List;

import com.qlfsoft.constellationlucky.R;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class TestActivity extends Activity {
	private ListView lv_main;
	private List<String> summarizes;
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.news_layout);
		lv_main = (ListView) findViewById(R.id.nl_main_lv);
		
		summarizes = new ArrayList<String>();
		String dbPath= this.getFilesDir() + "/" + "constellation.db";
		TestDBOpenHelper dbHelper = new TestDBOpenHelper(dbPath);
		summarizes = dbHelper.getAllSummarize();
		
		TestListAdapter adapter = new TestListAdapter(this);
		lv_main.setAdapter(adapter);
		lv_main.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				String selSum = summarizes.get(arg2);
				Intent intent = new Intent();
				intent.setClass(TestActivity.this, DoTestActivity.class);
				intent.putExtra("summarize", selSum);
				startActivity(intent);
				
			}
			
		});
	}
	
	public class TestListAdapter extends BaseAdapter
	{

		private LayoutInflater flater;
		public TestListAdapter(Context context)
		{
			flater = LayoutInflater.from(context);
		}
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return summarizes.size();
		}

		@Override
		public Object getItem(int arg0) {
			// TODO Auto-generated method stub
			return summarizes.get(arg0);
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			ViewHolder holder = null;
			if(convertView == null)
			{
				holder = new ViewHolder();
				convertView = flater.inflate(R.layout.testlist_item, null);
				holder.tv_title = (TextView) convertView.findViewById(R.id.tv_summarize);
				convertView.setTag(holder);
			}else
			{
				holder = (ViewHolder) convertView.getTag();
			}
			holder.tv_title.setText(summarizes.get(position));
			return convertView;
		}
		
	}
	
	public final class ViewHolder{
		public TextView tv_title;
	}
}
