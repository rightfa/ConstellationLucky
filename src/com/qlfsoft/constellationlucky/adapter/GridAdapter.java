package com.qlfsoft.constellationlucky.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.qlfsoft.constellationlucky.R;

public class GridAdapter extends BaseAdapter {

	private List<String> grid_names = new ArrayList<String>();
	private List<Drawable> grid_icons = new ArrayList<Drawable>();
	private Context context;
	private LayoutInflater infalter;
	
	public GridAdapter(Context context,List<String> grid_names,List<Drawable> grid_icons)
	{
		this.context = context;
		this.grid_names = grid_names;
		this.grid_icons = grid_icons;
		this.infalter = LayoutInflater.from(context);
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return grid_names.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return grid_names.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup viewGroup) {
		// TODO Auto-generated method stub
		ItemViewTag item;
		if(convertView == null)
		{
			convertView = infalter.inflate(R.layout.gridview_item,null);
			item = new ItemViewTag((ImageView)convertView.findViewById(R.id.grid_icon),(TextView)convertView.findViewById(R.id.grid_name));
			convertView.setTag(item);
		}else
		{
			item = (ItemViewTag)convertView.getTag();
		}
		item.icon.setBackgroundDrawable(grid_icons.get(position));
		item.name.setText(grid_names.get(position));
		return convertView;
	}

	class ItemViewTag
	{
		protected ImageView icon;
		protected TextView name;
		public ItemViewTag(ImageView icon,TextView name)
		{
			this.icon = icon;
			this.name = name;
		}
	}
}
