package com.qlfsoft.constellationlucky;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.qlfsoft.constellationlucky.adapter.NewsListAdapter;
import com.qlfsoft.constellationlucky.net.NewsHttpClient;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
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
	@SuppressWarnings("unchecked")
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.news_layout);
		lv_main = (ListView) findViewById(R.id.nl_main_lv);
		
		//获取缓存数据（标题和内容）
		List<Pair<String,String>> datas = new ArrayList<Pair<String,String>>();
		SharedPreferences sp0 = getSharedPreferences("newsTitle", Context.MODE_PRIVATE);
		SharedPreferences sp1 = getSharedPreferences("newsURL",Context.MODE_PRIVATE);
		int sp0Size = sp0.getAll().size();
		for(int i = 0;i< sp0Size;i++)
		{
			String title = sp0.getString(String.valueOf(i), "");
			String url = sp1.getString(String.valueOf(i), "");
			Pair<String,String> pair = new Pair<String,String>(title,url);
			datas.add(pair);
		}	
		//获取缓存图片
		SharedPreferences sp2 = getSharedPreferences("newsImage", Context.MODE_PRIVATE);
		List<Bitmap> bitmaps = new ArrayList<Bitmap>();
		if(datas.size() >= 3)
		{
			for(int i = 0; i < 3; i++)
			{
				String imgPath = String.valueOf((sp2.getString(String.valueOf(i), "")).hashCode());
				Bitmap bmp = BitmapFactory.decodeFile(imgPath);
				bitmaps.add(bmp);
			}
		}
		NewsListAdapter adapter = new NewsListAdapter(datas,this,bitmaps);
		lv_main.setAdapter(adapter);
		
		NewsHttpClient client = new NewsHttpClient(this);
		Thread t = new Thread(client);
		t.start();
		
		lv_main.setOnItemClickListener(new OnItemClickListener(){

			@SuppressWarnings("unchecked")
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,
					long id) {
				// TODO Auto-generated method stub
				Pair<String,String> data = (Pair<String, String>) view.getTag();
				String hrf = data.second;
				Intent intent =  new Intent();
				intent.putExtra("href", hrf);
				intent.putExtra("title", data.first);
				intent.setClass(NewsActivity.this, NewsContentActivity.class);
				startActivity(intent);
			}
			
		});
		
	}
}
