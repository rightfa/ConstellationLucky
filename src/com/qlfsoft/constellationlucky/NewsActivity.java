package com.qlfsoft.constellationlucky;

import com.qlfsoft.constellationlucky.net.NewsHttpClient;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class NewsActivity extends Activity {

	private ListView lv_main;
	public ListView getLv_main() {
		return lv_main;
	}
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.news_layout);
		lv_main = (ListView) findViewById(R.id.nl_main_lv);
		
		NewsHttpClient client = new NewsHttpClient(this);
		Thread t = new Thread(client);
		t.start();
		
		lv_main.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
	}
}
