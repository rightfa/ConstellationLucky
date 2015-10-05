package com.qlfsoft.constellationlucky.test;
import com.qlfsoft.constellationlucky.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;

public class DoTestActivity extends Activity {

	private TestDBOpenHelper dbHelper;
	private TestEntity testEntity;
	private TextView do_test_name;
	private RadioGroup questionGroup;
	private RadioButton questionABtn;
	private RadioButton questionBBtn;
	private RadioButton questionCBtn;
	private RadioButton questionDBtn;
	private RadioButton questionEBtn;
	private RadioButton questionFBtn;
	private LinearLayout ll_answerA;
	private LinearLayout ll_answerB;
	private LinearLayout ll_answerC;
	private LinearLayout ll_answerD;
	private LinearLayout ll_answerE;
	private LinearLayout ll_answerF;
	private TextView tv_answerA;
	private TextView tv_answerB;
	private TextView tv_answerC;
	private TextView tv_answerD;
	private TextView tv_answerE;
	private TextView tv_answerF;
	private ImageButton btn_back;
	private TextView simple_tab_title;
	private View answerGBtn;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.do_simple_testing);
		initData();
		initView();
	}

	public void initView() {
		do_test_name = (TextView) findViewById(R.id.do_simple_test_name);
		questionGroup = (RadioGroup) findViewById(R.id.simple_questionGroup);
		questionGroup.setOnCheckedChangeListener(new OnCheckedChangeListener(){

			@Override
			public void onCheckedChanged(RadioGroup arg0, int arg1) {
				// TODO Auto-generated method stub
				ll_answerA.setVisibility(View.INVISIBLE);
				ll_answerB.setVisibility(View.INVISIBLE);
				ll_answerC.setVisibility(View.INVISIBLE);
				ll_answerD.setVisibility(View.INVISIBLE);
				ll_answerE.setVisibility(View.INVISIBLE);
				ll_answerF.setVisibility(View.INVISIBLE);
				switch(arg1)
				{
				case R.id.simple_questionA:
					ll_answerA.setVisibility(View.VISIBLE);
					break;
				case R.id.simple_questionB:
					ll_answerB.setVisibility(View.VISIBLE);
					break;
				case R.id.simple_questionC:
					ll_answerC.setVisibility(View.VISIBLE);
					break;
				case R.id.simple_questionD:
					ll_answerD.setVisibility(View.VISIBLE);
					break;
				case R.id.simple_questionE:
					ll_answerE.setVisibility(View.VISIBLE);
					break;
				case R.id.simple_questionF:
					ll_answerF.setVisibility(View.VISIBLE);
					break;
				}
			}
			
		});
		questionABtn = (RadioButton) findViewById(R.id.simple_questionA);
		questionBBtn = (RadioButton) findViewById(R.id.simple_questionB);
		questionCBtn = (RadioButton) findViewById(R.id.simple_questionC);
		questionDBtn = (RadioButton) findViewById(R.id.simple_questionD);
		questionEBtn = (RadioButton) findViewById(R.id.simple_questionE);
		questionFBtn = (RadioButton) findViewById(R.id.simple_questionF);
		ll_answerA = (LinearLayout) findViewById(R.id.simple_answerA);
		ll_answerB = (LinearLayout) findViewById(R.id.simple_answerB);
		ll_answerC = (LinearLayout) findViewById(R.id.simple_answerC);
		ll_answerD = (LinearLayout) findViewById(R.id.simple_answerD);
		ll_answerE = (LinearLayout) findViewById(R.id.simple_answerE);
		ll_answerF = (LinearLayout) findViewById(R.id.simple_answerF);
		tv_answerA = (TextView) findViewById(R.id.tv_answerA);
		tv_answerB = (TextView) findViewById(R.id.tv_answerB);
		tv_answerC = (TextView) findViewById(R.id.tv_answerC);
		tv_answerD = (TextView) findViewById(R.id.tv_answerD);
		tv_answerE = (TextView) findViewById(R.id.tv_answerE);
		tv_answerF = (TextView) findViewById(R.id.tv_answerF);
		btn_back = (ImageButton) findViewById(R.id.btn_back);
		simple_tab_title = (TextView) findViewById(R.id.simple_tab_tittle);
		initAll();
		
		//·µ»Ø
		btn_back.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				DoTestActivity.this.finish();
			}
			
		});
	}

	public void initData() {
		dbHelper = new TestDBOpenHelper(this.getFilesDir() + "/" + "constellation.db");
		String summarize = this.getIntent().getExtras().getString("summarize");
		testEntity = dbHelper.getEntity(summarize);
	}
	public void initOther(){
		switch(testEntity.getFlag()){
		case 3:
			questionDBtn.setVisibility(View.GONE);
			questionEBtn.setVisibility(View.GONE);
			questionFBtn.setVisibility(View.GONE);
			break;
		case 4:
			questionDBtn.setVisibility(View.VISIBLE);
			questionEBtn.setVisibility(View.GONE);
			questionFBtn.setVisibility(View.GONE);
			break;
		case 5:
			questionDBtn.setVisibility(View.VISIBLE);
			questionEBtn.setVisibility(View.VISIBLE);
			questionFBtn.setVisibility(View.GONE);
			break;
		case 6:
			questionDBtn.setVisibility(View.VISIBLE);
			questionEBtn.setVisibility(View.VISIBLE);
			questionFBtn.setVisibility(View.VISIBLE);
			break;
		default:
			break;
		}
	}
	public void initAll() {
		do_test_name.setText(testEntity.getTitle());
		simple_tab_title.setText(testEntity.getSummarize());
		questionABtn.setText(testEntity.getQuestionA());
		questionBBtn.setText(testEntity.getQuestionB());
		questionCBtn.setText(testEntity.getQuestionC());
		questionDBtn.setText(testEntity.getQuestionD());
		questionEBtn.setText(testEntity.getQuestionE());
		questionFBtn.setText(testEntity.getQuestionF());
		tv_answerA.setText(testEntity.getAnswerA());
		tv_answerB.setText(testEntity.getAnswerB());
		tv_answerC.setText(testEntity.getAnswerC());
		tv_answerD.setText(testEntity.getAnswerD());
		tv_answerE.setText(testEntity.getAnswerE());
		tv_answerF.setText(testEntity.getAnswerF());
		initOther();
	}

}
