package com.chongu.test;

import java.lang.ref.SoftReference;

public class GcTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Object obj = new Object();
		String str = "helllo";
		obj = null;
		SoftReference<Object> ref  = new SoftReference<Object>(obj);
		try{
		for(int x=0;x<Integer.MAX_VALUE;x++){
			str += str+x;
			str.intern();
		}
		} catch(Throwable e){}
		System.gc();
		System.out.println(ref.get()+"###########################");

	}

}
