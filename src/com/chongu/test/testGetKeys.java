package com.chongu.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class testGetKeys {
	public static List<Map<String, Object>> parseJSONtoList(JSONArray jsobj) {
		List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
		JSONObject test = new JSONObject();
		Map<String, Object> map = null;
		for (int j = 0; j < jsobj.length(); j++) {
			map = new HashMap<String, Object>();
			try {
				test = jsobj.getJSONObject(j);
				System.out.println(test);
				Iterator keys = test.keys();
				while (keys.hasNext()) {

					String key = keys.next().toString();
					String value = test.optString(key);
					map.put(key, value);

				}

			} catch (JSONException e) {
				e.printStackTrace();
			}
			listMap.add(map);
		}
		return listMap;
	}
	
	public static List<Map<String, Object>> getJsonKeys(JSONArray testAll) throws JSONException {
		List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
		JSONObject test = new JSONObject();
		String result = null;
		testJsonCompare t = new testJsonCompare();
		System.out.println(testAll.length());
		Map<String, Object> map = new HashMap<String, Object>();
		for (int j = 0; j < testAll.length(); j++) {				
			
			test = testAll.getJSONObject(j);
			Iterator keys = test.keys();
			System.out.println("---test="+test.toString());
			while (keys.hasNext()) {

				try {

					String key = keys.next().toString();
					System.out.println("-----key=" + key);

					String value = test.optString(key);
					System.out.println("-----value=" + value);
					int i = t.testIsArrayORObject(value);
					
					map.put(key, value);
//					System.out.println("---map="+map.toString());
//					listMap.add(map);

					if (result == null || result.equals("")) {
						if (i == 0) {

							result = key + ",";
							System.out.println("i=0 | key=" + key + "| result="
									+ result);

						} else if (i == 1) {

							result = key + ",";
							System.out.println("i=1 | key=" + key + "| result="
									+ result);
							result = getKeys(new JSONObject(value)) + ",";
						} else if (i == 2) {

							result = key + ",";
							System.out.println("i=2 | key=" + key + "| result="
									+ result);
							JSONArray arrays = new JSONArray(value);
							for (int k = 0; k < arrays.length(); k++) {
								JSONObject array = new JSONObject(arrays.get(k));
								result = getKeys(array) + ",";
							}
						}

					} else {
						if (i == 0) {

							result = result + key + ",";
							System.out.println("i=0 | key=" + key + "| result="
									+ result);

						} else if (i == 1) {

							result = result + key + ",";
							System.out.println("i=1 | key=" + key + "| result="
									+ result);
							result = result + getKeys(new JSONObject(value));
						} else if (i == 2) {
							result = result + key + ",";
							System.out.println("i=2 | key=" + key + "| result="
									+ result);
							JSONArray arrays = new JSONArray(value);
							for (int k = 0; k < arrays.length(); k++) {
								JSONObject array = new JSONObject(arrays.get(k));
								result = result + getKeys(array) + ",";
							}
						}
					}

				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
			listMap.add(map);
		}
		System.out.println("---map="+map.toString());
		System.out.println("----listMap=" + listMap.toString());
		return listMap;
	}

	public static String getKeys(JSONObject test) throws JSONException {
		List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();

		String result = null;
		testJsonCompare t = new testJsonCompare();
		Iterator keys = test.keys();
		Map<String, Object> map = new HashMap<String, Object>();
		while (keys.hasNext()) {

			try {

				String key = keys.next().toString();
				System.out.println("-----key=" + key);

				String value = test.optString(key);
				System.out.println("-----value=" + value);
				int i = t.testIsArrayORObject(value);
				
				map.put(key, value);
//				listMap.add(map);
				
				if (result == null || result.equals("")) {
					if (i == 0) {

						result = key + ",";
						System.out.println("i=0 | key=" + key + "| result="
								+ result);

					} else if (i == 1) {

						result = key + ",";
						System.out.println("i=1 | key=" + key + "| result="
								+ result);
						result = getKeys(new JSONObject(value)) + ",";
					} else if (i == 2) {

						result = key + ",";
						System.out.println("i=2 | key=" + key + "| result="
								+ result);
						JSONArray arrays = new JSONArray(value);
						for (int k = 0; k < arrays.length(); k++) {
							JSONObject array = new JSONObject(arrays.get(k));
							result = getKeys(array) + ",";
						}
					}

				} else {
					if (i == 0) {

						result = result + key + ",";
						System.out.println("i=0 | key=" + key + "| result="
								+ result);

					} else if (i == 1) {

						result = result + key + ",";
						System.out.println("i=1 | key=" + key + "| result="
								+ result);
						result = result + getKeys(new JSONObject(value));
					} else if (i == 2) {
						result = result + key + ",";
						System.out.println("i=2 | key=" + key + "| result="
								+ result);
						JSONArray arrays = new JSONArray(value);
						for (int k = 0; k < arrays.length(); k++) {
							JSONObject array = new JSONObject(arrays.get(k));
							result = result + getKeys(array) + ",";
						}
					}
				}

			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		System.out.println("---map="+map.toString());
		System.out.println("----listMap=" + listMap.toString());
		return result;
	}

	public static void main(String args[]) throws org.json.JSONException {

		JSONObject test = new JSONObject();
		JSONObject test1 = new JSONObject();
		JSONObject jsonStr = null;
		JSONArray jsarray = null;
		try {
			test1.put("A", "1");
			test1.put("B", "2");

			test.put("a", "1");
			// test.put("c", test1);
			test.put("b", "2");
			String str = "{\"age\":\"22\",\"sex\":\"ÄÐ\",\"userName\":\"xiapi\"}";
			String strArray = "[{\"age\":\"22\",\"sex\":\"ÄÐ\",\"userName\":\"xiapi111\"},{\"age\":\"22\",\"sex\":\"ÄÐ\",\"userName\":\"xiapi2222\"},{\"age\":\"22\",\"sex\":\"ÄÐ\",\"userName\":\"xiapi\"}]";
			System.out.println(strArray);
			
			jsarray = new JSONArray(strArray);
			
			jsonStr = new JSONObject(str);
//			System.out.println(test.toString());

		} catch (JSONException e) {
			e.printStackTrace();
		}

		String s = null;
		//s= getKeys(jsonStr);
		System.out.println("===="+parseJSONtoList(jsarray));
		

	}
}
