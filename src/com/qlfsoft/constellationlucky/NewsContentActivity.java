package com.qlfsoft.constellationlucky;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.TextView;

public class NewsContentActivity extends Activity {

	private TextView tv_newsContent;
	private TextView top_title;
	private ImageButton btn_back;
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.newscontent_layout);
		tv_newsContent = (TextView) findViewById(R.id.tv_newsContent);
		top_title = (TextView) findViewById(R.id.top_title);
		btn_back = (ImageButton) findViewById(R.id.btn_back);
		String url = this.getIntent().getExtras().getString("href");
		String title = this.getIntent().getExtras().getString("title");
		this.top_title.setText(title);
		this.tv_newsContent.setText(url);
	}
	
	
}
