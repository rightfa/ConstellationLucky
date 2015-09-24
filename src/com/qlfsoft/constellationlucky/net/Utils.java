package com.qlfsoft.constellationlucky.net;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

public class Utils {

	/**
	 * 判断是否wifi连接
	 * @param context
	 * @return
	 */
	public static boolean isWifi(Context context)
	{
		ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo net = connectivityManager.getActiveNetworkInfo();
		if(null == net)
		{
			Log.i("test", "无网络连接");
			return false;
		}else
		{
			Log.i("test", "网络连接类型为：" + net.getTypeName());
			return net.getTypeName().equals("WIFI");
		}
	}
	
	/**
	 * 判断是否连接网络
	 * @param context
	 * @return
	 */
	public static boolean isNET(Context context)
	{
		ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo net = connectivityManager.getActiveNetworkInfo();
		if(null!= net)
		{
			return true;
		}else
		{
			return false;
		}
	}
	
	/**
	 * 在非UI线程中使用Toast
	 * @param con
	 * @param msg
	 * @param time
	 */
	public static void showToast(Context con,String msg,int time)
	{
		Looper.prepare();
		Toast.makeText(con, msg, time).show();
		Looper.loop();
	}
	
	/**
	 * 随机乞讨语
	 * @return
	 */
	public static String getMSG(){
		List<String> arr=new ArrayList<String>();
		arr.add("呜呜，小编要买房子给老婆，不然她就甩了我！好心点下广告吧！ T_T");
		arr.add("呜呜，好心呢，福心呢，施舍个发财钱呢，点下广告呢！ T_T");
		arr.add("过于丑b，卖身没人要。T_T 点一下广告");
		arr.add("我老婆被绑架了！我还差0.98美金的赎金！！点下广告呢！ T_T");
		arr.add("慈善的主呀,你也施舍钱给我吧,点下广告呢！ T_T");
		Collections.shuffle(arr);
		return arr.get(0);
	}
	
	/**
	 * 从网络上获取图片
	 * @param imageUrl
	 * @return
	 */
	public static Bitmap GetHttpImage(String imageUrl) 
	{
		HttpGet httpRequest = new HttpGet(imageUrl);
		HttpClient httpClient = new DefaultHttpClient();
		try {
			HttpResponse httpResponse = httpClient.execute(httpRequest);
			HttpEntity httpEntity = httpResponse.getEntity();
			InputStream is = httpEntity.getContent();
			Bitmap bitmap = BitmapFactory.decodeStream(is);
			is.close();
			return bitmap;
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
