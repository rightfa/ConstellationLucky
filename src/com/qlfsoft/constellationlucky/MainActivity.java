package com.qlfsoft.constellationlucky;

import android.app.Activity;
import android.app.LocalActivityManager;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TextView;

public class MainActivity extends Activity {

	private TabHost tabhost;
	@SuppressWarnings("deprecation")
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.main_layout);	
		tabhost = (TabHost) findViewById(R.id.maintabhost);		
		tabhost.setup();
		
		String tabNames[] = getResources().getStringArray(R.array.main_menu);
		String tabIds[] = {"tab1","tab2","tab3","tab4"};
		int tabImgs[] = {R.drawable.tab_news_bk,R.drawable.tab_lucky_bk,R.drawable.tab_love_bk,R.drawable.tab_personal_bk};
		@SuppressWarnings("deprecation")
		LocalActivityManager localActivityManager = new LocalActivityManager(this,false);
		localActivityManager.dispatchCreate(savedInstanceState);
		tabhost.setup(localActivityManager);
		for(int i = 0; i < tabNames.length; i++)
		{
			Intent intent = new Intent();
			switch(i)
			{
			case 0:
				intent.setClass(MainActivity.this, NewsActivity.class);
				break;
			case 1:
			case 2:
			case 3:
				intent.setClass(MainActivity.this, LuckyEnterActivity.class);
				break;
			}
			tabhost.addTab(tabhost.newTabSpec(tabIds[i]).setIndicator(getMenuItem(tabImgs[i],tabNames[i])).setContent(intent));
		}
		
	}
	
	public View getMenuItem(int imgId,String name)
	{
		LinearLayout ll = (LinearLayout) LayoutInflater.from(this).inflate(R.layout.tabitem, null);
		ImageView img_view = (ImageView) ll.findViewById(R.id.btn_icon);
		img_view.setBackgroundResource(imgId);
		TextView tv = (TextView) ll.findViewById(R.id.btn_name);
		tv.setText(name);
		return ll;
	}
}
