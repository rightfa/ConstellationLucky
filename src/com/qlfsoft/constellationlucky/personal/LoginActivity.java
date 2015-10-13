package com.qlfsoft.constellationlucky.personal;

import com.qlfsoft.constellationlucky.MainActivity;
import com.qlfsoft.constellationlucky.R;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends Activity implements OnClickListener {

	private EditText et_login_name;//µÇÂ¼Ãû
	private EditText et_login_pwd;//µÇÂ¼ÃÜÂë
	private TextView tv_login_findPwd;//ÕÒ»ØÃÜÂë
	private TextView tv_login_register;//×¢²á
	private Button btn_login;//µÇÂ¼°´Å¥
	private CheckBox cb_login_switch;//ÊÇ·ñÒþ²ØÃÜÂë
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_login);
	
		initView();
	}
	
	private void initView()
	{
		et_login_name = (EditText) findViewById(R.id.login_input_name);
		et_login_pwd = (EditText) findViewById(R.id.login_input_password);
		tv_login_findPwd = (TextView) findViewById(R.id.tv_login_findpwd);
		tv_login_register = (TextView) findViewById(R.id.tv_login_register);
		btn_login = (Button) findViewById(R.id.login_btn);
		btn_login.setOnClickListener(this);
		cb_login_switch = (CheckBox) findViewById(R.id.login_switchBtn);
		cb_login_switch.setOnCheckedChangeListener(new OnCheckedChangeListener(){

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				if(isChecked)
				{
					et_login_pwd.setTransformationMethod(HideReturnsTransformationMethod
		                    .getInstance());
				}else{
					et_login_pwd.setTransformationMethod(PasswordTransformationMethod
		                    .getInstance());
				}
				
			}
			
		});
		tv_login_findPwd.setOnClickListener(this);
		tv_login_register.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch(v.getId())
		{
		case R.id.login_btn:
			login();
			break;
		case R.id.tv_login_findpwd:
			findPwd();
			break;
		case R.id.tv_login_register:
			register();
			break;
		}
		
	}
	
	/**
	 * µÇÂ¼
	 */
	private void login()
	{
		String name = et_login_name.getText().toString().trim();
		String pwd = et_login_pwd.getText().toString().trim();
		if(TextUtils.isEmpty(name) || TextUtils.isEmpty(pwd))
		{
			Toast.makeText(this, "ÓÃ»§Ãû»òÃÜÂë²»ÄÜÎª¿Õ", Toast.LENGTH_SHORT).show();
			return;
		}
		SharedPreferences sp = this.getSharedPreferences("User", Context.MODE_PRIVATE);
		String sp_name = sp.getString("name", "");
		String sp_pwd = sp.getString("password", "");
		if(sp_name.equals(name) && sp_pwd.equals(pwd))
		{
			Intent intent = new Intent(this,MainActivity.class);
			startActivity(intent);
			finish();
		}else
		{
			Toast.makeText(this, "ÓÃ»§Ãû»òÃÜÂë´íÎó", Toast.LENGTH_SHORT).show();
			return;
		}
	}
	
	/**
	 * ÕÒ»ØÃÜÂë
	 */
	private void findPwd()
	{
		SharedPreferences sp = this.getSharedPreferences("User", Context.MODE_PRIVATE);
		String sp_pwd = sp.getString("password", "");
		Toast.makeText(this, "ÄúµÄÃÜÂëÊÇ£º" + sp_pwd, Toast.LENGTH_SHORT).show();
	}
	
	/**
	 * ×¢²á
	 */
	private void register()
	{
		Intent intent = new Intent(this,RegisterActivity.class);
		startActivity(intent);
		finish();
	}
}
