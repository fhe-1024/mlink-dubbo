package com.mdc.service.impl;

import com.mdc.service.IDemoService;

public class DemoServiceImpl implements IDemoService {

	@Override
	public String sayHello(String name) throws Exception {
		// TODO Auto-generated method stub
		
		return "hello" + name;
	}

}
