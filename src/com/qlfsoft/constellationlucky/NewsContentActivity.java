package com.qlfsoft.constellationlucky;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.qlfsoft.constellationlucky.net.Utils;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class NewsContentActivity extends Activity {

	private TextView tv_newsContent;
	private TextView top_title;
	private ImageButton btn_back;
	private String title;//文章标题
	private String htmlContent;//文章主内容
	public static final int NEWCONTENTRECEIVED = 1;
	public static final int NEWCONTENTUPDATED = 2;
	
	private Handler myHandler = new Handler(){
		public void handleMessage(Message msg)
		{
			switch(msg.what)
			{
			case NewsContentActivity.NEWCONTENTRECEIVED:
				String content = msg.getData().getString("content");
				htmlContent = content.replace(title, "").replace("http://img1.gtimg.com/astro/pics/hv1/91/165/1741/113250691.jpg", "");
				String reg1 = "<script>[\\s\\S]*</script>";
				String reg2 = "<style>[\\s\\S]*</style>";
				String reg3 = "<p class=\"v-runtime\">[\\s\\S]*正在播放</p>";
				htmlContent = htmlContent.replaceAll(reg1, "").replaceAll(reg2, "").replaceAll(reg3, "");
				tv_newsContent.setText(Html.fromHtml(htmlContent, imageGetter, null));
				break;
			case NewsContentActivity.NEWCONTENTUPDATED:
				tv_newsContent.setText(Html.fromHtml(htmlContent, imageGetter, null));
				break;
			}
		}
	};
	
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.newscontent_layout);
		tv_newsContent = (TextView) findViewById(R.id.tv_newsContent);
		top_title = (TextView) findViewById(R.id.top_title);
		btn_back = (ImageButton) findViewById(R.id.btn_back);
		btn_back.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				finish();
			}
			
		});
		String url = this.getIntent().getExtras().getString("href");
		title = this.getIntent().getExtras().getString("title");
		this.top_title.setText(title);
		NewsContentSpider spider = new NewsContentSpider(url);
		Thread t = new Thread(spider);
		t.start();
		//this.tv_newsContent.setText(url);
	}
	
	/**
	 * 获取星闻内容
	 * @author qlf
	 *
	 */
	public class NewsContentSpider implements Runnable{

		private String url;
		public NewsContentSpider(String url)
		{
			super();
			this.url = url;
		}
		@Override
		public void run() {
			// TODO Auto-generated method stub
			Document doc = null;
			Elements eles = null;
			if(!Utils.isNET(NewsContentActivity.this))
			{
				Utils.showToast(NewsContentActivity.this, "网络不可用哦，亲！", Toast.LENGTH_SHORT);
			}else
			{
				try {
					doc = Jsoup.connect(url).timeout(8000).get();
					if(null == doc)
					{
						Utils.showToast(NewsContentActivity.this, "网络不给力哦，亲,请返回再进入吧！", Toast.LENGTH_SHORT);
						return;
					}
					eles = doc.select("#Cnt-Main-Article-QQ P");
					StringBuilder sb = new StringBuilder();
					for(int i = 0; i < eles.size(); i++)
					{
						sb.append(eles.get(i).outerHtml());
					}
					Message msg = new Message();
					Bundle bundle = new Bundle();
					bundle.putString("content", sb.toString());
					Log.i("content", sb.toString());
					msg.setData(bundle);
					msg.what = NewsContentActivity.NEWCONTENTRECEIVED;
					myHandler.sendMessage(msg);
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
	}
	final Html.ImageGetter imageGetter = new Html.ImageGetter() {
		
		@Override
		public Drawable getDrawable(String source) {
			if(source.equals(""))
				return NewsContentActivity.this.getResources().getDrawable(R.drawable.white);
			Drawable drawable = null;
			String fileString = NewsContentActivity.this.getCacheDir() + "/"+ String.valueOf(source.hashCode());
			if(new File(fileString).exists())
			{
				drawable = Drawable.createFromPath(fileString);
				drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
				return drawable;
			}else
			{
				DownLoadImage downLoadImage  = new DownLoadImage(source,fileString);
				Thread t = new Thread(downLoadImage);
				t.start();
				return drawable;
			}
		}
	};
	
	/**
	 * 获取内部图片
	 * @author hyn
	 *
	 */
	public class DownLoadImage implements Runnable{

		private String url;
		private String imgpath;
		public DownLoadImage(String url,String imgpath)
		{
			this.url = url;
			this.imgpath = imgpath;
		}
		@Override
		public void run() {
			// TODO Auto-generated method stub
			HttpGet httpRequest = new HttpGet(url);
			HttpClient httpClient = new DefaultHttpClient();
			try {
				HttpResponse response = httpClient.execute(httpRequest);
				HttpEntity entity = response.getEntity();
				InputStream is = entity.getContent();
				File file = new File(imgpath);
				FileOutputStream fos = new FileOutputStream(file);
				byte[] buffer = new byte[1024];
				int length = 0;
				while((length = is.read(buffer))!= -1)
				{
					fos.write(buffer, 0, length);
				}
				fos.close();
				is.close();
				Message msg = new Message();
				msg.what = NewsContentActivity.NEWCONTENTUPDATED;
				myHandler.sendMessage(msg);
			} catch (ClientProtocolException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
}
