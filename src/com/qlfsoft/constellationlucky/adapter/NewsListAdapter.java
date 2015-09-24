package com.qlfsoft.constellationlucky.adapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.graphics.Bitmap;
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
		Log.i("position", String.valueOf(position));
		ll.setGravity(Gravity.LEFT);
		ll.setPadding(10, 5, 5, 5);
		int tag =( position == 0)? 0 : ((position == 1 || position == 2)? 1: 2);
		if(null == convertView || tag != Integer.valueOf(convertView.getTag().toString()))
		{
			if(0 == position)
			{
				ll.setOrientation(LinearLayout.VERTICAL);
				ImageView img = new ImageView(context);
				LinearLayout.LayoutParams img_params = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
				ll.addView(img,img_params);
				TextView tv = new TextView(context);
				tv.setTextSize(30);
				TextPaint tp = tv.getPaint();
				tp.setFakeBoldText(true);
				LinearLayout.LayoutParams tv_params = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
				ll.addView(tv,tv_params);
				ll.setTag(0);
				
			}else if(position == 1 || position == 2)
			{
				ll.setOrientation(LinearLayout.HORIZONTAL);
				TextView tv = new TextView(context);
				tv.setTextSize(30);
				LinearLayout.LayoutParams tv_params = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
				tv_params.weight = 7;
				ll.addView(tv,tv_params);
				ImageView img = new ImageView(context);
				LinearLayout.LayoutParams img_params = new LinearLayout.LayoutParams(300, 200);
				img_params.weight = 3;
				ll.addView(img,img_params);
				ll.setTag(1);
			}else
			{
				TextView tv = new TextView(context);
				tv.setTextSize(20);
				LinearLayout.LayoutParams tv_params = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
				ll.addView(tv,tv_params);
				ll.setTag(2);
			}
		}else
		{
			ll = (LinearLayout) convertView;
		}
		
		TextView tv;
		ImageView img;
		if(position == 0)
		{
			tv = (TextView)ll.getChildAt(1);
			img = (ImageView) ll.getChildAt(0);
			img.setAdjustViewBounds(true);
			img.setImageBitmap(bitmaps.get(position));
		}
		else if (position == 1 || position== 2)
		{
			tv= (TextView) ll.getChildAt(0);
			img = (ImageView)ll.getChildAt(1);
			img.setImageBitmap(bitmaps.get(position));
		}else{
			tv= (TextView) ll.getChildAt(0);
		}
		tv.setText(oneNew.first);
		
		return ll;
	}

}
