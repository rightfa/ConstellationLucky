package com.qlfsoft.constellationlucky.personal;

import java.text.SimpleDateFormat;

import com.qlfsoft.constellationlucky.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class UserDetailActivity extends Activity implements OnClickListener {

	private TextView about_title;//����
	private Button about_back;//���ذ�ť
	private Button about_submit;//�ύ��ť
	private ImageButton about_avatar;//ͷ��
	private Button about_avatar_change;//�ı�ͷ��ť
	private TextView about_name;//����
	private TextView about_signature;//ǩ��
	private TextView about_nickname;//�ǳ�
	private TextView about_date;//����
	private ImageView about_date_icon;//�ı����հ�ť
	private TextView about_constellation;//����
	private TextView about_home;//����
	private TextView about_address;//�־�ס��
	private ImageView about_address_icon;//�־�ס�ظı䰴ť
	private TextView about_phone;//�ֻ�
	private TextView about_education;//��������
	private TextView about_sex;//�Ա�
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_guide);
		
		initView();
		initData();
	}
	
	@Override
	public void onClick(View v) {
		switch(v.getId())
		{
		case R.id.about_back:
			finish();
			break;
			
		}

	}
	
	private void initView()
	{
		about_title = (TextView) findViewById(R.id.about_title);
		about_back = (Button) findViewById(R.id.about_back);
		about_back.setOnClickListener(this);
		about_submit = (Button) findViewById(R.id.about_submit);
		about_submit.setOnClickListener(this);
		about_avatar = (ImageButton) findViewById(R.id.about_avatar);
		about_avatar.setOnClickListener(this);
		about_avatar_change = (Button) findViewById(R.id.about_avatar_change);
		about_avatar_change.setOnClickListener(this);
		about_name = (TextView) findViewById(R.id.about_name);
		about_signature = (TextView) findViewById(R.id.about_signature);
		about_nickname = (TextView) findViewById(R.id.about_nickname);
		about_date = (TextView) findViewById(R.id.about_date);
		about_date_icon = (ImageView) findViewById(R.id.about_date_icon);
		about_date_icon.setOnClickListener(this);
		about_constellation = (TextView) findViewById(R.id.about_constellation);
		about_home = (TextView) findViewById(R.id.about_home);
		about_address = (TextView) findViewById(R.id.about_address);
		about_address_icon = (ImageView) findViewById(R.id.about_address_icon);
		about_address_icon.setOnClickListener(this);
		about_phone = (TextView) findViewById(R.id.about_telephone);
		about_education = (TextView) findViewById(R.id.about_education);
		about_sex = (TextView) findViewById(R.id.about_sex);
	}
	
	private void initData()
	{
		Intent intent = this.getIntent();
		int from = intent.getIntExtra("From", 0);
		UserEntity user = new UserEntity();
		switch(from)
		{
		case 0://��ʾ��ϸ��ϸ
			about_back.setVisibility(View.INVISIBLE);
			about_submit.setText("����");
			about_title.setText("������Ϣ");
			user.GetUser(this);
			about_name.setText(user.getNickName());
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			about_date.setText(sdf.format(user.getBirthday()));
			about_nickname.setText(user.getNickName());
			about_constellation.setText(user.getConstellation());
			about_home.setText(user.getHome());
			about_address.setText(user.getAddress());
			about_phone.setText(user.getTelephone());
			about_education.setText(user.getEducation());
			about_sex.setText(user.getSex());
			break;
		case 1:
			about_back.setVisibility(View.VISIBLE);
			about_submit.setText("�ύ");
			about_title.setText("�����Ƹ�����Ϣ");
			break;
		}
	}

}
