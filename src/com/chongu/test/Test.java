package com.chongu.test;

import java.io.IOException;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

public class Test {
	// ��webService������п����ҵ�NAMESPAC�ľ�������
			private static final String SERVICE_NAMESPACE = "http://test.com/";
			// URL
//			private static final String SERVICE_URL = "http://192.168.1.104:8080/Tt/TestPort?wsdl";
			private static final String SERVICE_URL = "http://192.168.66.14:8080/Tt/TestPort";
		//// ���õķ���
			private final static String mathodName = "getUserById";
	public static void main(String[] args){
	
		// ����HttpTransportSE����
		HttpTransportSE ht = new HttpTransportSE(SERVICE_URL,2000);		
		ht.debug = true;
		// ʹ��soap1.1Э�鴴��Envelop����
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);	
		// ʵ����SoapObject����
		SoapObject object = new SoapObject(SERVICE_NAMESPACE, mathodName);
		// ���ò���
		
		object.addProperty("arg0", 3);
		object.addProperty("arg1", "0001");
		
		// ��SoapObject��������ΪSoapSerializationEnvelope����Ĵ���SOAP��Ϣ
		envelope.bodyOut = object;
		
		try {
			// ����webService
			ht.call(null, envelope);
			
//			Toast.makeText(getApplicationContext(), envelope.getResponse().toString(),
//                    Toast.LENGTH_SHORT).show();
			System.out.println("---------------"+object);
			if (envelope.getResponse() != null) {
				SoapObject result = (SoapObject) envelope.bodyIn;
//				SoapObject msg = (SoapObject) result.getProperty(0);
//				StringBuilder sb = new StringBuilder();
				if(result.getProperty(0).toString() == "1"){
					//��֤ͨ��
					
				}else
				{//��֤δͨ��
					
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
