package com.qlfsoft.constellationlucky;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

public class SplashActivity extends Activity {

	private int[] splashes;
	private LinearLayout ll;
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.splash_layout);
		ll = (LinearLayout) findViewById(R.id.sp_ll);
		
		splashes = new int[]{R.drawable.splash_1,R.drawable.splash_2,R.drawable.splash_3,R.drawable.splash_4,
				R.drawable.splash_5,R.drawable.splash_6,R.drawable.splash_7,R.drawable.splash_8,R.drawable.splash_9
				,R.drawable.splash_10,R.drawable.splash_11,R.drawable.splash_12,R.drawable.splash_13,R.drawable.splash_14
				,R.drawable.splash_15,R.drawable.splash_16,R.drawable.splash_17,R.drawable.splash_18,R.drawable.splash_19
				,R.drawable.splash_20};
		
		Random rnd = new Random(System.currentTimeMillis());
		int show = rnd.nextInt(20);
		
		ll.setBackgroundResource(splashes[show]);
		
		new Handler().postDelayed(new Runnable(){

			@Override
			public void run() {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setClass(SplashActivity.this, MainActivity.class);
				startActivity(intent);
				finish();
			}
			
		}, 3000);
		
		//拷贝assets下的数据库到Data文件夹下
		File dbFile = new File(this.getFilesDir(),"constellation.db");
		if(!dbFile.exists())
		{
			try {
				InputStream is = this.getAssets().open("constellation.db");
				FileOutputStream fos = new FileOutputStream(dbFile);
				int len = 0;
				byte[] buffer = new byte[1024];
				while((len = is.read(buffer))!= -1)
				{
					fos.write(buffer, 0, len);
				}
				is.close();
				fos.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
}
