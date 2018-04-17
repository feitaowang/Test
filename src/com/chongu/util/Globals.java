package com.chongu.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class Globals {
	public static final String BASE_URL = "http://192.168.1.104:8081/Card";
	public static final String CAR_URT = BASE_URL + "/Car?method=list";

	public static String getResult(String surl) throws IOException {
		// URL url = new URL("http://192.168.1.104:8081/Card/Test?"
		// + "userid=chongu&password=123");
		URL url = new URL(surl);
		URLConnection uconn = url.openConnection();
		// ���շ��ؽ������������������
		InputStream is = uconn.getInputStream();
		// �������� �������ݣ�ͨ���ڴ������ն�������ݣ���ת����string
		ByteArrayOutputStream ou = new ByteArrayOutputStream();

		byte data[] = new byte[1024];

		int length = 0;
		while ((length = is.read(data)) != -1) {
			ou.write(data, 0, length);
		}
		is.close();
		String result = ou.toString();
		ou.close();
		// System.out.println(result);
		return result;
	}
}
