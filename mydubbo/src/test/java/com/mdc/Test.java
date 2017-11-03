package com.mdc;

import java.io.IOException;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {
	@org.junit.Test
	public void test() throws IOException {
		try {
			ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
					new String[] { "dubbo-demo-provider.xml" });
			context.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
