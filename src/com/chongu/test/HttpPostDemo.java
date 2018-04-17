package com.chongu.test;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

public class HttpPostDemo {

	public static void main(String[] args) {
		HttpPost post = new HttpPost(
				"http://192.168.66.36:8081/Card/Test");
//		post.addHeader("Content-Type","application/json");
		//通过下面方法设置参数
//		JSONObject params = new JSONObject();
		try {
//			params.put("userid","chongu");
//			params.put("password","1232");
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("userid","冲谷"));
			params.add(new BasicNameValuePair("password","123333"));
			post.setEntity(new UrlEncodedFormEntity(params,"UTF-8"));
//			post.setEntity(new StringEntity(params.toString()));
			
			HttpResponse resp = new DefaultHttpClient().execute(post);
			if (resp.getStatusLine().getStatusCode() == 200){
				System.out.println("ok");
			}else{
				System.out.println("error");
			}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
