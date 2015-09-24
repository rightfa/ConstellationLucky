package com.qlfsoft.constellationlucky.net;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.qlfsoft.constellationlucky.R;
import com.qlfsoft.constellationlucky.entity.TodayLuckyEntity;
import com.qlfsoft.constellationlucky.entity.WeekLuckyEntity;
import com.qlfsoft.constellationlucky.tabItems.TodayItem;
import com.qlfsoft.constellationlucky.tabItems.WeekItem;

public class WeekLuckySpider implements Runnable {

	public WeekLuckySpider() {

	}
	public WeekLuckySpider(WeekItem myView,String conn) {
		super();
		this.data = new WeekLuckyEntity();
		this.weekView = myView;
		this.conn = conn;
	}
	private WeekLuckyEntity data;
	private WeekItem weekView;
	private String conn;
	@Override
	public void run() {
		// TODO Auto-generated method stub
		Document doc = null;
		Elements eles = null;
		Element ele = null;
		String str = null;
		int length = 0;
		if(!Utils.isNET(weekView))
		{
			Utils.showToast(weekView, "网络不可用哦，亲！", Toast.LENGTH_SHORT);
			return;
		}else
		{
			try {
				doc = Jsoup.connect(conn).timeout(8000).get();
				if(null == doc)
				{
					Utils.showToast(weekView, "网络不给力哦，亲,请返回再进入吧！", Toast.LENGTH_SHORT);
					return;
				}
				eles = doc.select(".c_main dl dd ul li span em");
				str = eles.get(0).attr("style");
				Log.i("test", str);
				data.setInt_today_lucky(getStarImage(str));
				str = eles.get(1).attr("style");
				Log.i("test", str);
				data.setInt_love_lucky(getStarImage(str));
				str = eles.get(2).attr("style");
				Log.i("test", str);
				data.setInt_study_lucky(getStarImage(str));
				str = eles.get(3).attr("style");
				Log.i("test", str);
				data.setInt_wealth_lucky(getStarImage(str));
				str = eles.get(4).attr("style");
				data.setInt_health_lucky(getStarImage(str));
				eles = doc.select(".c_main dl dd ul li");
				for(int i = 0; i < eles.size();i++)
				{
					str = eles.get(i).text();
					if(str.contains("幸运星座："))
					{
						str = str.replace("幸运星座：", "");
						Log.i("test", str);
						data.setStr_lucky_constellation(str);
					}else if(str.contains("提防星座："))
					{
						str = str.replace("提防星座：", "");
						Log.i("test", str);
						data.setStr_bad_constellation(str);
					}else if(str.contains("幸运颜色："))
					{
						str = str.replace("幸运颜色：", "");
						Log.i("test", str);
						data.setStr_lucky_color(str);
					}else if(str.contains("短评："))
					{
						str = str.replace("短评：", "");
						Log.i("test", str);
						data.setStr_short_message(str);
					}
				}
				
				eles = doc.select(".c_main .c_box .c_cont p span");
				Log.i("size",String.valueOf(eles.size()));
				str = eles.get(0).text();
				Log.i("test", str);
				data.setStr_txt_total(str);
				str = eles.get(1).text();
				Log.i("test", str);
				data.setStr_txt_love(str);
				str = eles.get(2).text();
				Log.i("test", str);
				data.setStr_txt_study(str);
				str = eles.get(3).text();
				Log.i("test", str);
				data.setStr_txt_wealth(str);
				str = eles.get(4).text();
				Log.i("test", str);
				data.setStr_txt_health(str);
				setViewContent();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				Utils.showToast(weekView, "网络不给力哦，亲,请返回再进入吧！", Toast.LENGTH_LONG);
			}
		}
	}

	/**
	 * 获取图片路径
	 * @param txt
	 * @return
	 */
	private int getStarImage(String txt)
	{
		if(txt.contains("16"))
			return R.drawable.star_1;
		else if(txt.contains("32"))
			return R.drawable.star_2;
		else if(txt.contains("48"))
			return R.drawable.star_3;
		else if(txt.contains("64"))
			return R.drawable.star_4;
		else
			return R.drawable.star_5;
	}
	
	/**
	 * 更新图片UI
	 * @param img
	 * @param id
	 */
	private void ImagePost(final ImageView img,final int id)
	{
		img.post(new Runnable(){

			@Override
			public void run() {
				// TODO Auto-generated method stub
				img.setImageResource(id);
			}
			
		});
	}
	
	/**
	 * 更新文本框UI
	 * @param txt
	 * @param str
	 */
	private void TextPost(final TextView txt, final String str)
	{
		txt.post(new Runnable(){

			@Override
			public void run() {
				// TODO Auto-generated method stub
				txt.setText(str);
			}
			
		});
	}
	
	private void setViewContent()
	{
		ImagePost(weekView.getImg_total_lucky(),data.getInt_today_lucky());
		ImagePost(weekView.getImg_love_lucky(),data.getInt_love_lucky());
		ImagePost(weekView.getImg_study_lucky(),data.getInt_study_lucky());
		ImagePost(weekView.getImg_wealth_lucky(),data.getInt_wealth_lucky());
		ImagePost(weekView.getImg_health_lucky(),data.getInt_health_lucky());
		TextPost(weekView.getTv_lucky_color(),data.getStr_lucky_color());
		TextPost(weekView.getTv_lucky_constellation(),data.getStr_lucky_constellation());
		TextPost(weekView.getTv_bad_constellation(),data.getStr_bad_constellation());
		TextPost(weekView.getTv_short_message(),data.getStr_short_message());
		TextPost(weekView.getTv_txt_total(),data.getStr_txt_total());
		TextPost(weekView.getTv_txt_love(),data.getStr_txt_love());
		TextPost(weekView.getTv_txt_study(),data.getStr_txt_study());
		TextPost(weekView.getTv_txt_wealth(),data.getStr_txt_wealth());
		TextPost(weekView.getTv_txt_health(),data.getStr_txt_health());
	}

}
