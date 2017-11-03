package com.mlink.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.gson.Gson;
import com.mlink.service.ICityService;
import com.mlink.service.ICountryService;
import com.mlink.view.MlinkCountry;
import com.mlink.view.TreeData;

@Controller
@RequestMapping("/area")
public class AreaController {

	private Log log = LogFactory.getLog(AreaController.class);

	private ICountryService countryService;

	private ICityService cityService;

	@Autowired
	public void setCityService(ICityService cityService) {
		this.cityService = cityService;
	}

	@Autowired
	public void setCountryService(ICountryService countryService) {
		this.countryService = countryService;
	}

	@RequestMapping(path = "get", method = RequestMethod.GET)
	public String get(HttpServletResponse response) {
		log.info("/area/get invoked");
		TreeData first = new TreeData();
		first.setId("0");
		first.setText("mlink");
		first.setLevel("mlink");
		try {
			List<Map<String, Object>> countrylist = countryService.get();
			List<TreeData> firstchildren = new ArrayList<TreeData>();
			if (countrylist != null && countrylist.size() > 0) {
				Iterator<Map<String, Object>> secondit = countrylist.iterator();
				while (secondit.hasNext()) {
					Map<String, Object> map = secondit.next();
					TreeData second = new TreeData();
					second.setId((String) map.get("id"));
					second.setText((String) map.get("name"));
					second.setLevel("country");
					List<TreeData> secondchildren = new ArrayList<TreeData>();
					List<Map<String, Object>> citylist = cityService.getValueByCountryID((String) map.get("id"));
					if (citylist != null && citylist.size() > 0) {
						Iterator<Map<String, Object>> thirdit = citylist.iterator();
						while (thirdit.hasNext()) {
							Map<String, Object> thirdmap = thirdit.next();
							TreeData third = new TreeData();
							third.setId((String) thirdmap.get("id"));
							third.setText((String) thirdmap.get("name"));
							third.setLevel("city");
							secondchildren.add(third);
						}
					}
					second.setChildren(secondchildren);
					firstchildren.add(second);
				}
			}
			first.setChildren(firstchildren);

			response.setContentType("application/json;charset=utf-8");
			List<Object> resultList = new ArrayList<Object>();
			resultList.add(first);
			response.getWriter().write(new Gson().toJson(resultList));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(path = "getNodeList")
	public void getNodeList(HttpServletResponse response, HttpServletRequest request) {
		log.info("/area/getNodeList invoked");
		String page = request.getParameter("page");
		String rows = request.getParameter("rows");
		String level = request.getParameter("level");
		String id = request.getParameter("id");
		try {
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			if ("mlink".equals(level) || StringUtils.isBlank(level)) {
				list = countryService.get();
			} else if ("country".equals(level)) {
				list = cityService.getValueByCountryID(id);
			}
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("total", 100);
			map.put("rows", list);
			response.setContentType("application/json;charset=utf-8");
			response.getWriter().write(new Gson().toJson(map));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping(path = "saveNode")
	public void saveNode(HttpServletResponse response, HttpServletRequest request) {
		log.info("/area/saveNode invoked");
		String nodeid = request.getParameter("nodeid");
		String nodelevel = request.getParameter("nodelevel");
		String name = request.getParameter("name");
		String sort = request.getParameter("sort");
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			int effectrow = 0;
			String id = UUID.randomUUID().toString();
			String level = "";
			if ("mlink".equals(nodelevel) || StringUtils.isBlank(nodelevel)) {
				MlinkCountry country = new MlinkCountry();
				country.setId(id);
				country.setName(name);
				country.setSort(Integer.parseInt(sort));
				effectrow = countryService.save(country);
				level = "country";
			}
			

			if (effectrow > 0) {
				resultMap.put("id", id);
				resultMap.put("level", level);
				resultMap.put("result", true);
			} else {
				resultMap.put("result", false);
			}

		} catch (Exception e) {
			resultMap.put("result", false);
			log.error(e);
		} finally {
			try {
				response.setContentType("application/json;charset=utf-8");
				response.getWriter().write(new Gson().toJson(resultMap));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				log.error(e);
			}
		}

	}

}
