package com.mlink.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.gson.Gson;
import com.mlink.service.IConsultService;
import com.mlink.view.MlinkConsult;

@Controller
@RequestMapping("/aboutus")
public class AboutUsController {

	private Log log = LogFactory.getLog(getClass());

	@Autowired
	private IConsultService consultService;

	@RequestMapping(path = "/index")
	public String index() {
		return "aboutus";
	}

	@RequestMapping(path = "/saveConsult", method = RequestMethod.POST)
	public void saveConsult(HttpServletRequest request, HttpServletResponse response) {
		String name = request.getParameter("name");
		String sex = request.getParameter("sex");
		String phone = request.getParameter("phone");
		String position = request.getParameter("position");
		String company = request.getParameter("company");
		String text = request.getParameter("text");

		MlinkConsult consult = new MlinkConsult();
		consult.setId(UUID.randomUUID().toString());
		consult.setName(name);
		consult.setSex(Integer.parseInt(sex));
		consult.setPhone(phone);
		consult.setPosition(position);
		consult.setCompany(company);
		consult.setText(text);
		consult.setCreatetime(new Timestamp(System.currentTimeMillis()));
		int effectrow = 0;
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			effectrow = consultService.save(consult);
			if (effectrow > 0) {
				resultMap.put("result", true);
			} else {
				resultMap.put("result", false);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			resultMap.put("result", false);
			log.error(e);
		} finally {
			response.setContentType("application/json;charset=utf-8");
			try {
				response.getWriter().write(new Gson().toJson(resultMap));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				log.error(e);
			}
		}

	}
}
