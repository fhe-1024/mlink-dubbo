package com.mdc;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.mdc.service.IDemoService;

public class Test {
	@org.junit.Test
	public void test() {
		try {
			ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
					new String[] { "dubbo-demo-provider.xml" });
			context.start();
			IDemoService demoService = (IDemoService) context.getBean("demoService");
			String content = demoService.sayHello("helloworld");
			System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++" + content);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
