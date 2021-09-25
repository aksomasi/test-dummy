package com.example.demo;

import java.io.File;
import java.io.IOException;

public class testClass {

	public static void main(String[] args) {
		String s = "abc%26def%7C";
		s = s.replace("%26", "&").replace("%7C", "|");
		System.out.println(s);
	}
	
	void print(String[] args) {
		System.out.println("running");
		String filePath = "";
		String fileName = "conversionCsv.csv";
		System.out.println(args[2]);
		if(args[2].length()>0) {
			System.out.println("inside if "+args[2]);
			filePath = args[2] + "\\";
		}
		if(args[3].length()>0) {
			fileName = args[3];
		}
		System.out.println(filePath);
		 try {
		      File myObj = new File(filePath + fileName);
		      if (myObj.createNewFile()) {
		        System.out.println("File created: " + myObj.getName());
		      } else {
		        System.out.println("File already exists.");
		      }
		    } catch (IOException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
		  
	}
}
