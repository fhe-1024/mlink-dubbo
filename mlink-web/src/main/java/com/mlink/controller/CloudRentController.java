package com.mlink.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cloudrent")
public class CloudRentController {
	@RequestMapping(path = "/index")
	public String index() {
		return "cloudrent";
	}
}
