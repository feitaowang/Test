package com.chongu.test;

import java.io.IOException;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

public class Test {
	// 在webService服务端中可以找到NAMESPAC的具体内容
			private static final String SERVICE_NAMESPACE = "http://test.com/";
			// URL
//			private static final String SERVICE_URL = "http://192.168.1.104:8080/Tt/TestPort?wsdl";
			private static final String SERVICE_URL = "http://192.168.66.14:8080/Tt/TestPort";
		//// 调用的方法
			private final static String mathodName = "getUserById";
	public static void main(String[] args){
	
		// 创建HttpTransportSE对象
		HttpTransportSE ht = new HttpTransportSE(SERVICE_URL,2000);		
		ht.debug = true;
		// 使用soap1.1协议创建Envelop对象
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);	
		// 实例化SoapObject对象
		SoapObject object = new SoapObject(SERVICE_NAMESPACE, mathodName);
		// 设置参数
		
		object.addProperty("arg0", 3);
		object.addProperty("arg1", "0001");
		
		// 将SoapObject对象设置为SoapSerializationEnvelope对象的传出SOAP消息
		envelope.bodyOut = object;
		
		try {
			// 调用webService
			ht.call(null, envelope);
			
//			Toast.makeText(getApplicationContext(), envelope.getResponse().toString(),
//                    Toast.LENGTH_SHORT).show();
			System.out.println("---------------"+object);
			if (envelope.getResponse() != null) {
				SoapObject result = (SoapObject) envelope.bodyIn;
//				SoapObject msg = (SoapObject) result.getProperty(0);
//				StringBuilder sb = new StringBuilder();
				if(result.getProperty(0).toString() == "1"){
					//验证通过
					
				}else
				{//验证未通过
					
				}
				System.out.println("+++++++++++++++++"+result.getProperty(0).toString());
					
				
			} else {
				
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		} catch (XmlPullParserException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			
		}	
	
	}
}
