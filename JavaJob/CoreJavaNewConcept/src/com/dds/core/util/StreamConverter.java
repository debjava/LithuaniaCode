package com.dds.core.util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
//Final and stable
public class StreamConverter 
{
	static void writeOutput(String str) {

	       try {
	           FileOutputStream fos = new FileOutputStream("D:/test.txt");
	           Writer out = new OutputStreamWriter(fos, "UTF8");
	           out.write(str);
	           out.close();
	       } catch (IOException e) {
	           e.printStackTrace();
	       }
	   }

	   static String readInput() {

	      StringBuffer buffer = new StringBuffer();
	      try {
	          FileInputStream fis = new FileInputStream("D:/test.txt");
	          InputStreamReader isr = new InputStreamReader(fis, "UTF8");
	          Reader in = new BufferedReader(isr);
	          int ch;
	          while ((ch = in.read()) > -1) {
	             buffer.append((char)ch);
	          }
	          in.close();
	          return buffer.toString();
	      } catch (IOException e) {
	          e.printStackTrace();
	          return null;
	      }
	   }

	   public static void main(String[] args) {

	      String jaString  = 
	         new String("\u65e5\u672c\u8a9e\u6587\u5b57\u5217");

	      writeOutput(jaString);
	      String inputString = readInput();
	      String displayString = jaString + " " + inputString;
	      System.out.println("display :::: "+displayString );
	      System.out.println(readInput());
//	      new ShowString(displayString, "Conversion Demo");
	   }

}
