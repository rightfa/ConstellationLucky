package com.qlfsoft.constellationlucky.net;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.qlfsoft.constellationlucky.NewsActivity;
import com.qlfsoft.constellationlucky.R;
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
					//去掉**期的内容
					if(title.contains("期"))
						continue;
					String href = ele.attr("abs:href");
					Pair<String,String> data = new Pair<String,String>(title, href);
					//Log.i("title", title);
					//Log.i("href",href);
					datas.add(data);
				}
				
				SaveData();
				
				List<Pair<String,String>> title_imgs = new ArrayList<Pair<String,String>>();//图片标题对应
				for(int j = 0; j< 3; j++)//获取前面3张图片
				{
					String url = datas.get(j).second;
					doc = Jsoup.connect(url).timeout(8000).get();
					eles = doc.select("#Cnt-Main-Article-QQ IMG");
					String img_url = eles.first().attr("src");
					Bitmap bmp = null;
					//去除加载的空图片
					if(img_url.contains("ajax-loadernone.gif"))
					{
						datas.remove(j);
						j--;
						continue;
					}else
					{
						//根据url从网络上获取图片
						bmp = Utils.GetHttpImage(img_url);
						
						//保存图片到缓存
						String path = newsactivity.getCacheDir() + "/" + String.valueOf(img_url.hashCode());
						title_imgs.add(new Pair<String,String>(datas.get(j).first,path));
						File f = new File(path);
						if(!f.exists())
						{
							FileOutputStream out = new FileOutputStream(f);
							if(img_url.contains(".png"))
							{
								bmp.compress(Bitmap.CompressFormat.PNG, 100, out);
							}else if(img_url.contains(".jpg") || img_url.contains(".jpeg"))
							{
								bmp.compress(Bitmap.CompressFormat.JPEG, 100, out);
							}
							out.flush();
							out.close();
						}
					}
					bitmaps.add(bmp);
				}
				SaveImageTitle(title_imgs);
				
				ListViewPost(newsactivity.getLv_main());
				LinearLayoutPost(newsactivity.getLl_loading());
				
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
	
	private void LinearLayoutPost(final LinearLayout ll)
	{
		ll.post(new Runnable(){

			@Override
			public void run() {
				// TODO Auto-generated method stub
				ll.setVisibility(View.INVISIBLE);
			}
			
		});
	}

	/**
	 * 存储星闻列表
	 */
	private void SaveData()
	{
		SharedPreferences sp0 = newsactivity.getSharedPreferences("newsTitle", Context.MODE_PRIVATE);
		SharedPreferences.Editor editor0 = sp0.edit();
		SharedPreferences sp1 = newsactivity.getSharedPreferences("newsURL", Context.MODE_PRIVATE);
		SharedPreferences.Editor editor1 = sp1.edit();
		editor0.clear();
		editor1.clear();
		for(int i = 0; i< datas.size(); i++)
		{
			editor0.putString(String.valueOf(i), datas.get(i).first);
			editor1.putString(String.valueOf(i), datas.get(i).second);
		}
		editor0.commit();
		editor1.commit();
	}
	
	/**
	 * 保存标题对应的图片
	 * @param datas
	 */
	private void SaveImageTitle(List<Pair<String,String>> datas)
	{
		SharedPreferences sp = newsactivity.getSharedPreferences("newsImage", Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = sp.edit();
		editor.clear();
		for(int i = 0;i < datas.size();i++)
		{
			editor.putString(String.valueOf(i), datas.get(i).second);
		}
		editor.commit();
	}
}
