package com.qlfsoft.constellationlucky.net;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import android.graphics.Bitmap;
import android.util.Log;
import android.util.Pair;
import android.widget.ListView;
import android.widget.Toast;

import com.qlfsoft.constellationlucky.NewsActivity;
import com.qlfsoft.constellationlucky.adapter.NewsListAdapter;

public class NewsHttpClient implements Runnable {

	private NewsActivity newsactivity;
	private List<Pair<String,String>> datas;
	private List<Bitmap> bitmaps;
	
	public NewsHttpClient(NewsActivity nea)
	{
		this.newsactivity = nea;
		datas = new ArrayList<Pair<String,String>>();
		bitmaps = new ArrayList<Bitmap>();
	}
	@Override
	public void run() {
		Document doc = null;
		Elements eles = null;
		if(!Utils.isNET(newsactivity))
		{
			Utils.showToast(newsactivity, "网络不可用哦，亲！", Toast.LENGTH_SHORT);
			return;
		}else
		{
			try {
				doc = Jsoup.connect(URLS.NEW_FROM).timeout(8000).get();
				if(null == doc)
				{
					Utils.showToast(newsactivity, "网络不给力哦，亲,请返回再进入吧！", Toast.LENGTH_SHORT);
					return;
				}
				eles = doc.select("table tr td a.f2a");
				for(int i = 0; i < eles.size(); i++)
				{
					Element ele = eles.get(i);
					String title = ele.text();
					String href = ele.attr("abs:href");
					Pair<String,String> data = new Pair<String,String>(title, href);
					//Log.i("title", title);
					//Log.i("href",href);
					datas.add(data);
				}
				
				for(int j = 0; j< 3; j++)//获取前面3张图片
				{
					String url = datas.get(j).second;
					doc = Jsoup.connect(url).timeout(8000).get();
					eles = doc.select("#Cnt-Main-Article-QQ IMG");
					String img_url = eles.first().attr("src");
					Bitmap bmp = Utils.GetHttpImage(img_url);
					bitmaps.add(bmp);
				}
				
				ListViewPost(newsactivity.getLv_main());
				
			}catch(IOException e)
			{
				e.printStackTrace();
				Utils.showToast(newsactivity, "网络不给力哦，亲,请返回再进入吧！", Toast.LENGTH_LONG);
			}

	}
	}
	
	private void ListViewPost(final ListView lv)
	{
		lv.post(new Runnable(){

			@Override
			public void run() {
				// TODO Auto-generated method stub
				NewsListAdapter adapter = new NewsListAdapter(datas,newsactivity,bitmaps);
				lv.setAdapter(adapter);
			}
			
		});
	}

}
