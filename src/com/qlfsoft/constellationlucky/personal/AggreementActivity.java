package com.qlfsoft.constellationlucky.personal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;

import com.qlfsoft.constellationlucky.R;

import android.app.Activity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.TextView;

public class AggreementActivity extends Activity {
	private ImageButton btn_back;
	private TextView tv_agreement;
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.register_contact);
		
		btn_back = (ImageButton) findViewById(R.id.btn_back);
		btn_back.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				finish();
				
			}
			
		});
		
		tv_agreement = (TextView) findViewById(R.id.tv_agreement);
		try {
			InputStream is = getResources().getAssets().open("useragreement.txt");
			BufferedReader br = new BufferedReader(new InputStreamReader(is,"gbk"));
			StringBuilder sb = new StringBuilder();
			String line = "";
			while((line = br.readLine())!= null)
			{
				sb.append(line);
			}
			line = sb.toString();
			tv_agreement.setText(Html.fromHtml(line));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
