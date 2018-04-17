package com.chongu.test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import net.sf.json.JSON;
import net.sf.json.JSONObject;

import org.apache.http.HttpResponse;

public class Test1 {

	public static void main(String[] args) {
		
		URL url;
		URLConnection con = null;
		try {
			//url = new URL("http://192.168.1.103:8080/Web01/TestServlet");
			url = new URL("http://shuju.hbncdg.com/param101.php?key=5475589ad");
			try {
				con = url.openConnection();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// post请求必须设置下面两项
			con.setDoOutput(true);
			con.setDoInput(true);
			// 不使用缓存
			con.setUseCaches(false);
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		JSONObject  personjson = new JSONObject ();
		personjson.put("shop_code", "1.1.1.1");
		personjson.put("goods_code", "3");
		personjson.put("sale_num", "200");
		personjson.put("type", "0");
		personjson.put("report_time", "2016-07-21 16:39:47");
		personjson.put("order_num", "10");	  
		
		//String personjson = "[{\"xingming\":\"namezxc\",\"xingbie\":0}]";
		//String personjson1 = "[{\"shop_code\":\"192.168.1.10\",\"goods_code\":\"3\",\"sale_num\":\"200\",\"type\":\"0\",\"report_time\":\"2016-07-21 16:39:47\",\"order_num\":\"10\"}]";
		//String zhengzhuangjson = "[{\"kesou\":0}]";

		// 设置自定义的请求头，也可以用这个方法得到发送数据
		//con.setRequestProperty("personjson", personjson);
		con.setRequestProperty("param", personjson.toString());
		//con.setRequestProperty("zhengzhuangjson", zhengzhuangjson);
		// 这句是打开链接
		OutputStream out;
		String result;
		try {
			out = con.getOutputStream();
			// 把数据写到报文
			//out.write(("zhengzhuangjson=" + zhengzhuangjson).getBytes());
			// &号是数据间隔，
			//out.write("&".getBytes());
			out.write(("param=" + personjson).getBytes());

			out.flush();
			out.close();
			// 这句才是真正发送请求
			InputStream inStream = con.getInputStream();
			
			//输出流
			//OutputStream ou = con.getOutputStream();
			 
			//BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(ou));
			
			
			// 获取响应输入流
            //InputStream inStream = httpResponse.getEntity().getContent();
            
            BufferedReader reader = new BufferedReader(new InputStreamReader(
            		inStream, "utf-8"));
            StringBuilder strber = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null)
            	//System.out.println(line+"");
                strber.append(line + "\n");
            inStream.close();

            result = strber.toString();
            System.out.println(result);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

}
