package com.qlfsoft.constellationlucky;

import java.util.List;

import com.qlfsoft.constellationlucky.tabItems.MonthItem;
import com.qlfsoft.constellationlucky.tabItems.TodayItem;
import com.qlfsoft.constellationlucky.tabItems.TomorrowItem;
import com.qlfsoft.constellationlucky.tabItems.WeekItem;
import com.qlfsoft.constellationlucky.tabItems.YearItem;

import android.app.Activity;
import android.app.LocalActivityManager;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TextView;

public class MainLucky extends Activity {

	private TabHost tabhost;
	private TextView title;
	private ImageButton btn_back;
	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.main_lucky);
		
		int index = this.getIntent().getExtras().getInt("index");//当前星座值
		String[] con_names = getResources().getStringArray(R.array.ch_constellation);
		
		title = (TextView)findViewById(R.id.top_title);
		title.setText(con_names[index]);
		//返回按钮
		btn_back = (ImageButton)findViewById(R.id.btn_back);
		btn_back.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setClass(MainLucky.this, MainActivity.class);
				startActivity(intent);
				finish();
			}});
		
		tabhost = (TabHost)findViewById(R.id.tabhost);
		tabhost.setup();
		String[] tabNames = getResources().getStringArray(R.array.tabStrs); 
		String[] tabIds = {"tab1","tab2","tab3","tab4","tab5"};
		int[] tabImgs = {R.drawable.btn_today_bk,R.drawable.btn_tomorrow_bk,R.drawable.btn_week_bk,R.drawable.btn_month_bk,R.drawable.btn_year_bk};
		LocalActivityManager localactivityManager = new LocalActivityManager(this,false);
		localactivityManager.dispatchCreate(savedInstanceState);
		tabhost.setup(localactivityManager);
		//int[] tabContents = {R.id.tab_ll_1,R.id.tab_ll_2,R.id.tab_ll_3,R.id.tab_ll_4,R.id.tab_ll_5};
		for(int i = 0; i < tabNames.length; i++)
		{
			Intent intent = new Intent();
			intent.putExtra("index", index);
			switch(i)
			{
			case 0:
				intent.setClass(MainLucky.this, TodayItem.class);
				break;
			case 1:
				intent.setClass(MainLucky.this, TomorrowItem.class);
				break;
			case 2:
				intent.setClass(MainLucky.this, WeekItem.class);
				break;
			case 3:
				intent.setClass(MainLucky.this, MonthItem.class);
				break;
			case 4:
				intent.setClass(MainLucky.this, YearItem.class);
				break;
			}
			tabhost.addTab(tabhost.newTabSpec(tabIds[i]).setIndicator(getMenuItem(tabImgs[i],tabNames[i])).setContent(intent));
		}
	}
	
	private View getMenuItem(int imgID, String name)
	{
		LinearLayout ll = (LinearLayout)LayoutInflater.from(this).inflate(R.layout.tabitem, null);
		ImageView img = (ImageView)ll.findViewById(R.id.btn_icon);
		img.setBackgroundResource(imgID);
		TextView txt = (TextView)ll.findViewById(R.id.btn_name);
		txt.setText(name);
		return ll;
	}
}
