package test;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class HelloWorld {
	public static void main(String[] args) {
		try {

			ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
					new String[] { "applicationContext.xml", "dubbo-provider.xml" });
			context.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
		synchronized (HelloWorld.class) {
			while (true) {
				try {
					HelloWorld.class.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
