package com.mlink.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/renthost")
public class RentHostController {
	@RequestMapping(path = "/index")
	public String index() {
		return "renthost";
	}
}
