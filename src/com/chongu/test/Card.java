package com.chongu.test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.chongu.util.Globals;

public class Card {

	public static void main(String[] args) {
		try {
			String res = Globals.getResult(Globals.CAR_URT);
			System.out.println(res);
			//Í¨¹ýjson½âÎö
			JSONObject root = new JSONObject(res);
			JSONArray all = root.getJSONArray("allNews");
			for(int i = 0;i < all.length();i++){
				JSONObject obj = all.getJSONObject(i);
				System.out.println(obj.getString("title"));
			}
		} catch (IOException | JSONException e) {
			
			e.printStackTrace();
		}
	}
}
