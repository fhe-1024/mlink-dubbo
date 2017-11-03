package com.mdc.dubbotest;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Provider {
	public static void main(String[] args) {
		try {
			ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
					new String[] { "provider.xml" });
			context.start();
			System.in.read(); // press any key to exit
			System.out.println(11);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
