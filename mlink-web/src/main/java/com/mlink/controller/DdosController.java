package com.mlink.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/ddos")
public class DdosController {

	@RequestMapping(path = "/index")
	public String index() {
		return "ddos";
	}
}
