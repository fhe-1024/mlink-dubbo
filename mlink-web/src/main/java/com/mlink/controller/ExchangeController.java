package com.mlink.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.gson.Gson;
import com.mlink.service.IExchangeService;
import com.mlink.util.PageUtil;
import com.mlink.view.MlinkExchange;

@Controller
@RequestMapping("/exchange")
public class ExchangeController {
	private Log log = LogFactory.getLog(getClass());

	@Autowired
	private IExchangeService exchangeService;

	@RequestMapping(path = "/getExchangeList")
	public void getExchangeList(HttpServletRequest request, HttpServletResponse response) {
		String pagesize = request.getParameter("page");
		String rows = request.getParameter("rows");
		PageUtil<MlinkExchange> page = new PageUtil<MlinkExchange>(Integer.parseInt(rows));
		page.setPageNo(Integer.parseInt(pagesize));
		List<MlinkExchange> resultList = new ArrayList<MlinkExchange>();
		try {
			resultList = exchangeService.getList(page, new HashMap<String, Object>());
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("total", page.getTotalCount());
			map.put("rows", resultList);
			response.setContentType("application/json;charset=utf-8");
			response.getWriter().write(new Gson().toJson(map));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error(e);
		}
	}
}
