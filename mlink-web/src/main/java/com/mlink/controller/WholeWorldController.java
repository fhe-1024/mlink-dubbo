package com.mlink.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.gson.Gson;
import com.mlink.service.ICountryService;
import com.mlink.service.IInternationalService;
import com.mlink.view.TreeData;

@Controller
@RequestMapping("/wholeworld")
public class WholeWorldController {

	private Log log = LogFactory.getLog(getClass());

	@Autowired
	private IInternationalService internationalService;
	@Autowired
	private ICountryService countryService;

	@RequestMapping(path = "/index")
	public String index() {
		return "wholeworld";
	}

	@RequestMapping(path = "getTreeMenu", method = RequestMethod.GET)
	public void getTreeMenu(HttpServletResponse response) {
		TreeData node = new TreeData();
		node.setId("0");
		node.setText("mlink");
		node.setLevel("mlink");
		List<TreeData> nodeChildren = new ArrayList<TreeData>();
		try {
			List<Map<String, Object>> internationalList = internationalService
					.getAllMapList(new HashMap<String, Object>());
			if (internationalList != null && internationalList.size() > 0) {
				Iterator<Map<String, Object>> internationalit = internationalList.iterator();
				while (internationalit.hasNext()) {
					Map<String, Object> internationalMap = internationalit.next();
					TreeData first = new TreeData();
					first.setId((String) internationalMap.get("id"));
					first.setText((String) internationalMap.get("name"));
					first.setLevel("international");
					nodeChildren.add(first);
					Map<String, Object> searchCountryMap = new HashMap<String, Object>();
					searchCountryMap.put("internationalid", internationalMap.get("id"));
					List<Map<String, Object>> countryList = countryService.getAllMapList(searchCountryMap);
					List<TreeData> firstChildren = new ArrayList<TreeData>();
					if (countryList != null && countryList.size() > 0) {
						Iterator<Map<String, Object>> countryit = countryList.iterator();
						while (countryit.hasNext()) {
							Map<String, Object> countryMap = countryit.next();
							TreeData second = new TreeData();
							second.setId((String) countryMap.get("id"));
							second.setText((String) countryMap.get("name"));
							second.setLevel("country");
							firstChildren.add(second);
						}
					}
					first.setChildren(firstChildren);
				}
			}
			node.setChildren(nodeChildren);
			response.setContentType("application/json;charset=utf-8");
			List<Object> resultList = new ArrayList<Object>();
			resultList.add(node);

			Map<String, Object> map = new HashMap<String, Object>();
			map.put("result", true);
			map.put("data", nodeChildren);

			response.getWriter().write(new Gson().toJson(map));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error(e);
		}
	}

}
