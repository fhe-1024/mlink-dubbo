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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.gson.Gson;
import com.mlink.service.ICityService;
import com.mlink.service.ICountryService;
import com.mlink.service.IInternationalService;
import com.mlink.service.INodeService;
import com.mlink.service.ISingleService;
import com.mlink.util.PageUtil;
import com.mlink.view.MlinkSingle;
import com.mlink.view.TreeData;

@Controller
@RequestMapping("/single")
public class SingleController {
	private Log log = LogFactory.getLog(getClass());

	@Autowired
	private IInternationalService internationalService;
	@Autowired
	private ICountryService countryService;
	@Autowired
	private ICityService cityService;
	@Autowired
	private INodeService nodeService;
	@Autowired
	private ISingleService singleService;

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
							Map<String, Object> searchCityMap = new HashMap<String, Object>();
							searchCityMap.put("countryid", second.getId());
							List<Map<String, Object>> cityList = cityService.getAllMapList(searchCityMap);
							List<TreeData> secondChildren = new ArrayList<TreeData>();
							if (cityList != null && cityList.size() > 0) {
								Iterator<Map<String, Object>> cityit = cityList.iterator();
								while (cityit.hasNext()) {
									Map<String, Object> cityMap = cityit.next();
									TreeData three = new TreeData();
									three.setId((String) cityMap.get("id"));
									three.setText((String) cityMap.get("name"));
									three.setLevel("city");
									secondChildren.add(three);
									Map<String, Object> searchNodeMap = new HashMap<String, Object>();
									searchNodeMap.put("cityid", cityMap.get("id"));
									List<Map<String, Object>> nodeList = nodeService.getAllMapList(searchNodeMap);
									List<TreeData> threeChildren = new ArrayList<TreeData>();
									if (nodeList != null && nodeList.size() > 0) {
										Iterator<Map<String, Object>> nodeit = nodeList.iterator();
										while (nodeit.hasNext()) {
											Map<String, Object> nodeMap = nodeit.next();
											TreeData four = new TreeData();
											four.setId((String) nodeMap.get("id"));
											four.setText((String) nodeMap.get("name"));
											four.setLevel("node");
											threeChildren.add(four);
										}
									}
									three.setChildren(threeChildren);
								}
							}
							second.setChildren(secondChildren);
						}
					}
					first.setChildren(firstChildren);
				}
			}
			node.setChildren(nodeChildren);
			response.setContentType("application/json;charset=utf-8");
			List<Object> resultList = new ArrayList<Object>();
			resultList.add(node);
			response.getWriter().write(new Gson().toJson(resultList));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error(e);
		}
	}

	@RequestMapping(path = "getSingleList")
	public void getSingleList(HttpServletRequest request, HttpServletResponse response) {
		String pagesize = request.getParameter("page");
		String rows = request.getParameter("rows");
		String level = request.getParameter("level");
		String id = request.getParameter("id");
		try {
			PageUtil<MlinkSingle> page = new PageUtil<MlinkSingle>(Integer.parseInt(rows));
			page.setPageNo(Integer.parseInt(pagesize));
			List<MlinkSingle> list = new ArrayList<MlinkSingle>();
			Map<String, Object> argsMap = new HashMap<String, Object>();
			if ("node".equals(level)) {
				argsMap.put("nodeid", id);
				list = singleService.getList(page, argsMap);
			}
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("total", page.getTotalCount());
			map.put("rows", list);
			response.setContentType("application/json;charset=utf-8");
			response.getWriter().write(new Gson().toJson(map));
		} catch (Exception e) {
			log.error(e);
		}
	}

	@RequestMapping(path = "saveSingle", method = RequestMethod.POST)
	public void saveSingle(HttpServletRequest request, HttpServletResponse response) {
		String single_nodeid = request.getParameter("single_nodeid");
		String single_area = request.getParameter("single_area");
		String single_protocol = request.getParameter("single_protocol");
		String single_electricity = request.getParameter("single_electricity");
		String single_authentication = request.getParameter("single_authentication");
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			int effectrow = 0;
			MlinkSingle single = new MlinkSingle();
			single.setId(UUID.randomUUID().toString());
			single.setNodeid(single_nodeid);
			single.setArea(single_area);
			single.setProtocol(single_protocol);
			single.setElectricity(single_electricity);
			single.setAuthentication(single_authentication);
			effectrow = singleService.save(single);
			if (effectrow > 0) {
				resultMap.put("result", true);
			} else {
				resultMap.put("result", false);
			}
		} catch (Exception e) {
			// TODO: handle exception
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

	@RequestMapping(path = "deleteSingle")
	public void deleteSingle(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			int effectrow = 0;
			effectrow = singleService.delete(id);
			if (effectrow > 0) {
				resultMap.put("result", true);
			} else {
				resultMap.put("result", false);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			resultMap.put("result", false);
			e.printStackTrace();
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

	@RequestMapping(path = "updateSingle", method = RequestMethod.POST)
	public void updateSingle(HttpServletRequest request, HttpServletResponse response) {
		String single_id = request.getParameter("single_id");
		String single_area = request.getParameter("single_area");
		String single_protocol = request.getParameter("single_protocol");
		String single_electricity = request.getParameter("single_electricity");
		String single_authentication = request.getParameter("single_authentication");
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			int effectrow = 0;
			MlinkSingle single = new MlinkSingle();
			single.setId(single_id);
			single.setArea(single_area);
			single.setProtocol(single_protocol);
			single.setElectricity(single_electricity);
			single.setAuthentication(single_authentication);
			effectrow = singleService.update(single);
			if (effectrow > 0) {
				resultMap.put("result", true);
			} else {
				resultMap.put("result", false);
			}
		} catch (Exception e) {
			// TODO: handle exception
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
