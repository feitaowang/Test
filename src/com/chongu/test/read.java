package com.chongu.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Types;

public class read {

	public static void main(String[] args) {
		
		File f = new File("d:\\sql");
		tree(f,1);

	}
	public static void tree(File f, int level)  {
		String preStr = "";
		String dir = "";
		OutputStreamWriter osw = null;
		for (int i = 0; i < level; i++) {
			preStr += "    ";
		}

		File[] childs = f.listFiles();
		for (int i = 0; i < childs.length; i++) {
			System.out.println(preStr + childs[i].getName());			
			
			if (childs[i].isFile()) {
				// System.out.println("file");
				try {
					osw = new OutputStreamWriter(
							new FileOutputStream(
									childs[i].getPath()+".txt", true));
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				try {

					System.out.println(childs[i].getPath());
					BufferedReader br = new BufferedReader(new FileReader(childs[i].getPath()));
					
					String line = br.readLine();

					while (line != null) {
						System.out.println(line);
						osw.write(line
								+ "\r\n");
						line = br.readLine();
					}
					osw.close();
					br.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (childs[i].isDirectory()) {

				dir = childs[i].getName();

				tree(childs[i], level + 1);
			}
		}
	}
}
