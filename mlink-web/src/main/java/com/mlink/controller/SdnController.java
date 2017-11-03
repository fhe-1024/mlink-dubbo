package com.mlink.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
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
import com.mlink.service.ILineService;
import com.mlink.view.MlinkExchange;
import com.mlink.view.MlinkLine;

@Controller
@RequestMapping("/sdn")
public class SdnController {

	private Log log = LogFactory.getLog(getClass());

	@Autowired
	private ILineService lineService;
	@Autowired
	private IExchangeService exchangeService;

	@RequestMapping(path = "/index")
	public String index() {
		return "sdn";
	}

	@RequestMapping(path = "/getLineList")
	public void getLineList(HttpServletRequest request, HttpServletResponse response) {
		List<Map<String, Object>> list = null;
		List<MlinkLine> resultList = new ArrayList<MlinkLine>();
		try {
			double charge = 1.0;
			MlinkExchange exchange = exchangeService.getLastExchange();
			if (exchange != null) {
				charge = exchange.getExchange();
			}
			BigDecimal chargebg = new BigDecimal(charge);
			list = lineService.getAllMapList(new HashMap<String, Object>());
			if (list != null) {
				Iterator<Map<String, Object>> it = list.iterator();
				while (it.hasNext()) {
					Map<String, Object> map = it.next();
					MlinkLine line = new MlinkLine();
					line.setId((String) map.get("id"));
					line.setName((String) map.get("name"));
					double price = (Double) map.get("price");
					BigDecimal origin = new BigDecimal(price);
					line.setPrice(
							Double.parseDouble(origin.multiply(chargebg).setScale(0, BigDecimal.ROUND_UP).toString()));
					resultList.add(line);
				}
			}
			Map<String, Object> resultMap = new HashMap<String, Object>();
			resultMap.put("result", true);
			resultMap.put("data", resultList);
			response.setContentType("application/json;charset=utf-8");
			response.getWriter().write(new Gson().toJson(resultMap));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error(e);
		}
	}
}
