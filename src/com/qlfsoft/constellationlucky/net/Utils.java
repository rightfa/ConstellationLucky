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
	 * �ж��Ƿ�wifi����
	 * @param context
	 * @return
	 */
	public static boolean isWifi(Context context)
	{
		ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo net = connectivityManager.getActiveNetworkInfo();
		if(null == net)
		{
			Log.i("test", "����������");
			return false;
		}else
		{
			Log.i("test", "������������Ϊ��" + net.getTypeName());
			return net.getTypeName().equals("WIFI");
		}
	}
	
	/**
	 * �ж��Ƿ���������
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
	 * �ڷ�UI�߳���ʹ��Toast
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
	 * ���������
	 * @return
	 */
	public static String getMSG(){
		List<String> arr=new ArrayList<String>();
		arr.add("���أ�С��Ҫ���Ӹ����ţ���Ȼ����˦���ң����ĵ��¹��ɣ� T_T");
		arr.add("���أ������أ������أ�ʩ�������Ǯ�أ����¹���أ� T_T");
		arr.add("���ڳ�b������û��Ҫ��T_T ��һ�¹��");
		arr.add("�����ű�����ˣ��һ���0.98�������𣡣����¹���أ� T_T");
		arr.add("���Ƶ���ѽ,��Ҳʩ��Ǯ���Ұ�,���¹���أ� T_T");
		Collections.shuffle(arr);
		return arr.get(0);
	}
	
	/**
	 * �������ϻ�ȡͼƬ
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
