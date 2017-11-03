package com.mlink.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mlink.view.MlinkCity;

@Controller
public class IndexController {

	private Log log = LogFactory.getLog(getClass());

	@RequestMapping("/index")
	public String index() {
		log.info("访问首页");
		return "index";
	}
	
	@RequestMapping("/test")
	@ResponseBody
	public MlinkCity test() {
		log.info("访问首页");
		MlinkCity city=new MlinkCity();
		return city;
	}

	@RequestMapping("/")
	public String mlink() {
		log.info("访问首页");
		return "redirect:/index";
	}
	
	@RequestMapping("/login")
	public String login() {
		return "login";
	}
}