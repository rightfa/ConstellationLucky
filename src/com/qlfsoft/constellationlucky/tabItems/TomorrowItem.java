package com.qlfsoft.constellationlucky.tabItems;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import android.app.ActivityGroup;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.qlfsoft.constellationlucky.R;
import com.qlfsoft.constellationlucky.net.TodayLuckySpider;
import com.qlfsoft.constellationlucky.net.TomorrowLuckySpider;
import com.qlfsoft.constellationlucky.net.URLS;

public class TomorrowItem extends ActivityGroup {
	
	public TextView getTv_title() {
		return tv_title;
	}

	public TextView getTv_today() {
		return tv_today;
	}

	public ImageView getImg_total_lucky() {
		return img_total_lucky;
	}

	public ImageView getImg_love_lucky() {
		return img_love_lucky;
	}

	public ImageView getImg_study_lucky() {
		return img_study_lucky;
	}

	public ImageView getImg_wealth_lucky() {
		return img_wealth_lucky;
	}

	public TextView getTv_health_num() {
		return tv_health_num;
	}

	public TextView getTv_talk_num() {
		return tv_talk_num;
	}

	public TextView getTv_lucky_color() {
		return tv_lucky_color;
	}

	public TextView getTv_lucky_num() {
		return tv_lucky_num;
	}

	public TextView getTv_match_constellation() {
		return tv_match_constellation;
	}

	public TextView getTv_short_message() {
		return tv_short_message;
	}

	public TextView getTv_txt_total() {
		return tv_txt_total;
	}

	public TextView getTv_txt_love() {
		return tv_txt_love;
	}

	public TextView getTv_txt_study() {
		return tv_txt_study;
	}

	public TextView getTv_txt_wealth() {
		return tv_txt_wealth;
	}

	public TextView getTv_txt_health() {
		return tv_txt_health;
	}

	private TextView tv_title;//标题
	private TextView tv_today;//时间
	private ImageView img_total_lucky;//整体运势
	private ImageView img_love_lucky;//爱情运势
	private ImageView img_study_lucky;//事业学业
	private ImageView img_wealth_lucky;//财务运势
	private TextView tv_health_num;//健康指数
	private TextView tv_talk_num;//商谈指数
	private TextView tv_lucky_color;//幸运颜色
	private TextView tv_lucky_num;//幸运数字
	private TextView tv_match_constellation;//速配星座
	private TextView tv_short_message;//短评
	private TextView tv_txt_total;//整体运势
	private TextView tv_txt_love;//爱情运势
	private TextView tv_txt_study;//事业学业
	private TextView tv_txt_wealth;//财富运势
	private TextView tv_txt_health;//健康运势
	private TomorrowLuckySpider spider;
	
	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.tomorrow_layout);
		initView();
		Intent curIntent = this.getIntent();
		int index = curIntent.getIntExtra("index",0);
		initData(index);
		String conn = getConn(index);
		spider = new TomorrowLuckySpider(this,conn);
		Thread t = new Thread(spider);
		t.start();
	}
	
	private void initView()
	{
		tv_title = (TextView) findViewById(R.id.tv_title);
		tv_today = (TextView) findViewById(R.id.tv_today);
		img_total_lucky = (ImageView)findViewById(R.id.img_total_lucky);
		img_love_lucky = (ImageView)findViewById(R.id.img_love_lucky);
		img_study_lucky = (ImageView)findViewById(R.id.img_study_lucky);
		img_wealth_lucky = (ImageView)findViewById(R.id.img_wealth_lucky);
		tv_health_num = (TextView)findViewById(R.id.tv_health_num);
		tv_talk_num = (TextView)findViewById(R.id.tv_talk_num);
		tv_lucky_color = (TextView)findViewById(R.id.tv_lucky_color);
		tv_lucky_num = (TextView)findViewById(R.id.tv_lucky_num);
		tv_match_constellation = (TextView) findViewById(R.id.tv_match_constellation);
		tv_short_message = (TextView)findViewById(R.id.tv_short_message);
		tv_txt_total = (TextView)findViewById(R.id.tv_txt_total);
		tv_txt_love = (TextView)findViewById(R.id.tv_txt_love);
		tv_txt_study = (TextView)findViewById(R.id.tv_txt_study);
		tv_txt_wealth = (TextView)findViewById(R.id.tv_txt_wealth);
		tv_txt_health = (TextView)findViewById(R.id.tv_txt_health);
	}
	
	private void initData(int index)
	{
		String[] con_names = getResources().getStringArray(R.array.ch_constellation);
		tv_title.setText(con_names[index] + "明日运势");
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日");
		Date today = new Date();
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(today);
		calendar.add(calendar.DATE, 1);
		Date tomorrow = calendar.getTime();
		tv_today.setText(formatter.format(tomorrow));
		
	}
	
	/*
	 * 获取链接
	 */
	private String getConn(int index)
	{
		switch(index)
		{
		case 0:
			return URLS.ARIES_2;
		case 1:
			return URLS.TAURUS_2;
		case 2:
			return URLS.GEMINI_2;
		case 3:
			return URLS.CANCER_2;
		case 4:
			return URLS.LEO_2;
		case 5:
			return URLS.VIRGO_2;
		case 6:
			return URLS.LIBRO_2;
		case 7:
			return URLS.SCORPIO_2;
		case 8:
			return URLS.SAGITTARIUS_2;
		case 9:
			return URLS.CAPRICORN_2;
		case 10:
			return URLS.AQUARIUS_2;
		case 11:
			return URLS.PISCES_2;
		}
		return URLS.ARIES_2;
	}
}