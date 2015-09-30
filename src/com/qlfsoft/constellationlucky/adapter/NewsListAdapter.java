package com.qlfsoft.constellationlucky.adapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.qlfsoft.constellationlucky.R;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.text.TextPaint;
import android.util.Log;
import android.util.Pair;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class NewsListAdapter extends BaseAdapter {

	private List<Pair<String,String>> news = new ArrayList<Pair<String,String>> ();
	private Context context;
	private List<Bitmap> bitmaps = new ArrayList<Bitmap>();

	public NewsListAdapter(List<Pair<String,String>> news,Context context,List<Bitmap> bitmaps ) {
		super();
		this.news = news;
		this.context = context;
		this.bitmaps = bitmaps;
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return news.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return news.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		LinearLayout ll = new LinearLayout(context);
		Pair<String,String> oneNew = news.get(position);
		//Log.i("position", String.valueOf(position));
		ll.setGravity(Gravity.LEFT);
		ll.setPadding(10, 5, 5, 5);
		if(null == convertView)
		{
			TextView tv = new TextView(context);
			ll.addView(tv);
			ImageView img = new ImageView(context);
			ll.addView(img);
		}else
		{
			ll = (LinearLayout) convertView;
		}
		
		TextView tv;
		ImageView img;
		tv = (TextView) ll.getChildAt(0);
		img = (ImageView)ll.getChildAt(1);
		LinearLayout.LayoutParams tv_params = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		tv.setLayoutParams(tv_params);
		TextPaint tp = tv.getPaint();
		Bitmap bitmap = null;
		if(position < 3)
			bitmap =bitmaps.get(position);
		if(position == 0)
		{
			ll.setOrientation(LinearLayout.VERTICAL);
			img.setVisibility(View.VISIBLE);
			img.setAdjustViewBounds(true);
			img.setImageBitmap(bitmap);
			LinearLayout.LayoutParams img_params = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
			img.setLayoutParams(img_params);
			tv.setTextSize(30);	
			tp.setFakeBoldText(true);
		}
		else if (position == 1 || position== 2)
		{
			ll.setOrientation(LinearLayout.HORIZONTAL);
			img.setVisibility(View.VISIBLE);
			img.setImageBitmap(bitmap);
			LinearLayout.LayoutParams img_params = new LinearLayout.LayoutParams(300, 200);
			img.setLayoutParams(img_params);
			tv.setTextSize(25);
			tp.setFakeBoldText(false);
		}else
		{
			img.setVisibility(View.GONE);
			tv.setTextSize(20);
			tp.setFakeBoldText(false);
		}
		tv.setText(oneNew.first);
		ll.setTag(oneNew);
		return ll;
	}

}
