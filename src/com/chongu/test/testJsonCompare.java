package com.chongu.test;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class testJsonCompare {

	public int testIsArrayORObject(String sJSON){
		/*
		 * return 0:既不是array也不是object
		 * return 1：是object
		 * return 2 ：是Array
		 */
		try {
			JSONArray array = new JSONArray(sJSON);
			return 2;
		} catch (JSONException e) {// 抛错 说明JSON字符不是数组或根本就不是JSON
			try {
				JSONObject object = new JSONObject(sJSON);
				return 1;
			} catch (JSONException e2) {// 抛错 说明JSON字符根本就不是JSON
				System.out.println("非法的JSON字符串");
				return 0;
			}
		}

	}
}
