package com.chongu.test;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class testJsonCompare {

	public int testIsArrayORObject(String sJSON){
		/*
		 * return 0:�Ȳ���arrayҲ����object
		 * return 1����object
		 * return 2 ����Array
		 */
		try {
			JSONArray array = new JSONArray(sJSON);
			return 2;
		} catch (JSONException e) {// �״� ˵��JSON�ַ��������������Ͳ���JSON
			try {
				JSONObject object = new JSONObject(sJSON);
				return 1;
			} catch (JSONException e2) {// �״� ˵��JSON�ַ������Ͳ���JSON
				System.out.println("�Ƿ���JSON�ַ���");
				return 0;
			}
		}

	}
}
