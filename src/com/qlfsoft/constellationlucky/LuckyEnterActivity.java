package com.qlfsoft.constellationlucky;

import java.util.ArrayList;
import java.util.List;

import com.qlfsoft.constellationlucky.adapter.GridAdapter;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

public class LuckyEnterActivity extends Activity {

	private List<String> grid_names;
	private List<Drawable> grid_icons;
	private GridView gridView; 
	private TextView title;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.lucky_activity_main);
		gridView = (GridView)findViewById(R.id.gridView1);
		title = (TextView)findViewById(R.id.top_title);
		initData();
		initView();
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	private void initData()
	{
		grid_names = new ArrayList<String>();
		grid_icons = new ArrayList<Drawable>();
		String[] names = getResources().getStringArray(R.array.ch_constellation);
		int[] icons = {R.drawable.aries,R.drawable.tauus,R.drawable.gemini,R.drawable.cancer,R.drawable.leo,R.drawable.virgo,
				R.drawable.libra,R.drawable.scorpio,R.drawable.sagittarius,R.drawable.capricorn,R.drawable.aquarius,R.drawable.pisces};
		for(int i = 0; i < 12; i++)
		{
			grid_names.add(names[i]);
			grid_icons.add(getResources().getDrawable(icons[i]));
		}
	}
	
	@SuppressWarnings("deprecation")
	private void initView()
	{
		WindowManager wm = this.getWindowManager();
		int width = wm.getDefaultDisplay().getWidth();
		int padding = width/ 9;
		gridView.setVerticalSpacing(padding);
		gridView.setHorizontalSpacing(padding);
		gridView.setAdapter(new GridAdapter(LuckyEnterActivity.this,grid_names,grid_icons));
		
		gridView.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,
					long id) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setClass(LuckyEnterActivity.this, MainLucky.class);
				Bundle bundle = new Bundle();
				bundle.putInt("index", position);
				intent.putExtras(bundle);
				startActivity(intent);
				finish();
			}
			
		});
		title.setText(R.string.constellation_title);
	}

}
