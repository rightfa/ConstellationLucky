package com.qlfsoft.constellationlucky.personal;

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
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.CompoundButton.OnCheckedChangeListener;

public class RegisterActivity extends Activity implements OnClickListener{

	private EditText et_register_name;
	private EditText et_register_pwd;
	private TextView tv_useraggree;
	private CheckBox cb_switchBtn;
	private Button btn_register;
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_register);
		initView();
	}
	@Override
	public void onClick(View v) {
		switch(v.getId())
		{
		case R.id.register_btn:
			register();
			break;
		case R.id.tv_useraggree:
			showAggreement();
			break;
		}
		
	}
	
	private void showAggreement() {
		Intent intent = new Intent(this,AggreementActivity.class);
		startActivity(intent);
	}
	private void register() {
		String email = et_register_name.getText().toString().trim();
		String pwd = et_register_pwd.getText().toString().trim();
		if(TextUtils.isEmpty(email) || TextUtils.isEmpty(pwd))
		{
			Toast.makeText(this, "” œ‰ªÚ√‹¬Î¥ÌŒÛ", Toast.LENGTH_SHORT).show();
			return;
		}else
		{
			SharedPreferences sp = this.getSharedPreferences("User", Context.MODE_PRIVATE);
			SharedPreferences.Editor editor = sp.edit();
			editor.putString("name", email);
			editor.putString("password", pwd);
			editor.commit();
			Toast.makeText(this, "◊¢≤·≥…π¶£°", Toast.LENGTH_SHORT).show();
			Intent intent = new Intent(this,UserDetailActivity.class);
			intent.putExtra("From", 1);
			startActivity(intent);
			finish();
		}
		
	}
	private void initView()
	{
		et_register_name = (EditText) this.findViewById(R.id.register_input_name);
		et_register_pwd = (EditText) this.findViewById(R.id.register_input_password);
		tv_useraggree = (TextView) this.findViewById(R.id.tv_useraggree);
		cb_switchBtn = (CheckBox) this.findViewById(R.id.register_switchBtn);
		btn_register = (Button) this.findViewById(R.id.register_btn);
		
		tv_useraggree.setOnClickListener(this);
		btn_register.setOnClickListener(this);
		cb_switchBtn.setOnCheckedChangeListener(new OnCheckedChangeListener(){

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				if(isChecked)
				{
					et_register_pwd.setTransformationMethod(HideReturnsTransformationMethod
		                    .getInstance());
				}else{
					et_register_pwd.setTransformationMethod(PasswordTransformationMethod
		                    .getInstance());
				}
				
			}
			
		});
	}
	

}

	
	
