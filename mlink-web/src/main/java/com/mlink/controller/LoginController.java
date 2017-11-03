package com.mlink.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.gson.Gson;

@Controller
@RequestMapping("/login")
public class LoginController {

	private Log log = LogFactory.getLog(getClass());

	@RequestMapping(path = "/manage", method = RequestMethod.POST)
	public void login(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		Map<String, Object> resultMap = new HashMap<String, Object>();
		if ("mlink".equals(username) && "mlink".equals(password)) {
			resultMap.put("result", true);
			session.setAttribute("mlink", "mlink");
		} else {
			resultMap.put("result", false);
		}
		try {
			response.setContentType("application/json;charset=utf-8");
			response.getWriter().write(new Gson().toJson(resultMap));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			log.error(e);
		}

	}

}
