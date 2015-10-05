package com.qlfsoft.constellationlucky.test;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class TestDBOpenHelper {

	private String path;
	public TestDBOpenHelper(String path)
	{
		this.path = path;
	}

	/**
	 * 获取所有测试的概况信息
	 * @return
	 */
	public List<String> getAllSummarize()
	{
		SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(path, null);
		List<String> summarizes = new ArrayList<String>();
		String sql = "select summarize from test";
		Cursor cursor = db.rawQuery(sql, null);
		while(cursor!= null && cursor.moveToNext())
		{
			String summarize = cursor.getString(cursor.getColumnIndex("summarize"));
			summarizes.add(summarize);
		}
		if(cursor!= null)
		{
			cursor.close();
		}
		db.close();
		return summarizes;
	}
	
	/**
	 * 获取单个测试的信息
	 * @param summarize
	 * @return
	 */
	public TestEntity getEntity(String summarize)
	{
		SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(path, null);
		TestEntity entity = new TestEntity();
		entity.setSummarize(summarize);
		String sql = "select * from test where summarize= ?";
		Cursor cursor = db.rawQuery(sql, new String[]{summarize});
		if(cursor!= null && cursor.moveToNext())
		{
			int id = cursor.getInt(cursor.getColumnIndex("Id"));
			entity.setId(id);
			String title = cursor.getString(cursor.getColumnIndex("title"));
			entity.setTitle(title);
			String questionA = cursor.getString(cursor.getColumnIndex("questionA"));
			entity.setQuestionA(questionA);
			String questionB = cursor.getString(cursor.getColumnIndex("questionB"));
			entity.setQuestionB(questionB);
			String questionC = cursor.getString(cursor.getColumnIndex("questionC"));
			entity.setQuestionC(questionC);
			String questionD = cursor.getString(cursor.getColumnIndex("questionD"));
			entity.setQuestionD(questionD);
			String questionE = cursor.getString(cursor.getColumnIndex("questionE"));
			entity.setQuestionE(questionE);
			String questionF = cursor.getString(cursor.getColumnIndex("questionF"));
			entity.setQuestionF(questionF);
			String answerA = cursor.getString(cursor.getColumnIndex("answerA"));
			entity.setAnswerA(answerA);
			String answerB = cursor.getString(cursor.getColumnIndex("answerB"));
			entity.setAnswerB(answerB);
			String answerC = cursor.getString(cursor.getColumnIndex("answerC"));
			entity.setAnswerC(answerC);
			String answerD = cursor.getString(cursor.getColumnIndex("answerD"));
			entity.setAnswerD(answerD);
			String answerE = cursor.getString(cursor.getColumnIndex("answerE"));
			entity.setAnswerE(answerE);
			String answerF = cursor.getString(cursor.getColumnIndex("answerF"));
			entity.setAnswerF(answerF);
			int flag = cursor.getInt(cursor.getColumnIndex("flag"));
			entity.setFlag(flag);
		}
		db.close();
		return entity;
	}
}
