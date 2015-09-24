package com.qlfsoft.constellationlucky.tabItems;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import com.qlfsoft.constellationlucky.R;
import com.qlfsoft.constellationlucky.net.MonthLuckySpider;
import com.qlfsoft.constellationlucky.net.URLS;
import com.qlfsoft.constellationlucky.net.WeekLuckySpider;

import android.app.ActivityGroup;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

public class MonthItem extends ActivityGroup {
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

	public TextView getTv_txt_decompression() {
		return tv_txt_decompression;
	}

	public TextView getTv_txt_golucky() {
		return tv_txt_golucky;
	}

	private TextView tv_title;//����
	private TextView tv_today;//ʱ��
	private ImageView img_total_lucky;//��������
	private ImageView img_love_lucky;//��������
	private ImageView img_study_lucky;//��ҵѧҵ
	private ImageView img_wealth_lucky;//��������
	private TextView tv_short_message;//����
	private TextView tv_txt_total;//��������
	private TextView tv_txt_love;//��������
	private TextView tv_txt_study;//��ҵѧҵ
	private TextView tv_txt_wealth;//�Ƹ�����
	private TextView tv_txt_health;//��������
	private TextView tv_txt_decompression;//��ѹ��ʽ
	private TextView tv_txt_golucky;//����С�ؾ�
	private MonthLuckySpider spider;
	
	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.month_layout);
		initView();
		Intent curIntent = this.getIntent();
		int index = curIntent.getIntExtra("index",0);
		initData(index);
		String conn = getConn(index);
		spider = new MonthLuckySpider(this,conn);
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
		tv_txt_decompression = (TextView) findViewById(R.id.tv_txt_decompression);
		tv_txt_golucky = (TextView) findViewById(R.id.tv_txt_golucky);
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
		tv_title.setText(con_names[index] + "��������");
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy��MM��dd��");
		Date today = new Date();
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(today);
		calendar.add(calendar.MONTH, 1);
		Date nextweek = calendar.getTime();
		tv_today.setText(formatter.format(today) + "-" + formatter.format(nextweek));
		
	}
	
	/*
	 * ��ȡ����
	 */
	private String getConn(int index)
	{
		switch(index)
		{
		case 0:
			return URLS.ARIES_4;
		case 1:
			return URLS.TAURUS_4;
		case 2:
			return URLS.GEMINI_4;
		case 3:
			return URLS.CANCER_4;
		case 4:
			return URLS.LEO_4;
		case 5:
			return URLS.VIRGO_4;
		case 6:
			return URLS.LIBRO_4;
		case 7:
			return URLS.SCORPIO_4;
		case 8:
			return URLS.SAGITTARIUS_4;
		case 9:
			return URLS.CAPRICORN_4;
		case 10:
			return URLS.AQUARIUS_4;
		case 11:
			return URLS.PISCES_4;
		}
		return URLS.ARIES_4;
	}
}
