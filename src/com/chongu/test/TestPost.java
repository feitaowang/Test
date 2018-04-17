package com.chongu.test;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;

public class TestPost {

	  public static String URL = "http://192.168.1.103:8080/Web01/TestServlet";

	    public static void main(String[] args) {	            
	    	
	    	final Map<String,String>map=new HashMap<String, String>();
	        map.put("name", "aaa");
	        map.put("pwd", "111");
	        post("?name = abc&pwd=hhhh");
	        //post("name = abc");

	    }

	    public static String post(String str) {

	        HttpClient client = new DefaultHttpClient();
	        HttpPost post = new HttpPost(URL);
	        
	        //post.setHeader("Content-Type", "application/json");
	       // post.addHeader("Authorization", "Basic YWRtaW46");
	        String result = "";
	        
	        try {

	            StringEntity s = new StringEntity(str.toString(), "utf-8");
	            s.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE,
	                    "application/json"));
	            post.setEntity(s);

	            // 发送请求
	            HttpResponse httpResponse = client.execute(post);

	            // 获取响应输入流
	            InputStream inStream = httpResponse.getEntity().getContent();
	            BufferedReader reader = new BufferedReader(new InputStreamReader(
	                    inStream, "utf-8"));
	            StringBuilder strber = new StringBuilder();
	            String line = null;
	            while ((line = reader.readLine()) != null)
	            	System.out.println(line+"");
	                strber.append(line + "\n");
	            inStream.close();

	            result = strber.toString();
	            System.out.println(result);
	            
	            if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
	                
	                    System.out.println("请求服务器成功");
	                
	            } else {
	                
	                System.out.println("请求服务端失败");
	                
	            }
	            

	        } catch (Exception e) {
	            System.out.println("请求异常");
	            throw new RuntimeException(e);
	        }

	        return result;
	    }

}
