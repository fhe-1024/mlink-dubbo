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
import com.mlink.service.ICountryService;
import com.mlink.service.IInternationalService;
import com.mlink.service.ITipService;
import com.mlink.util.PageUtil;
import com.mlink.view.MlinkTip;
import com.mlink.view.TreeData;

@Controller
@RequestMapping("/tip")
public class TipController {
	private Log log = LogFactory.getLog(getClass());

	@Autowired
	private IInternationalService internationalService;
	@Autowired
	private ICountryService countryService;
	@Autowired
	private ITipService tipService;

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
			response.getWriter().write(new Gson().toJson(resultList));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error(e);
		}
	}

	@RequestMapping(path = "getTipList")
	public void getTipList(HttpServletRequest request, HttpServletResponse response) {
		String pagesize = request.getParameter("page");
		String rows = request.getParameter("rows");
		String level = request.getParameter("level");
		String id = request.getParameter("id");
		try {
			PageUtil<MlinkTip> page = new PageUtil<MlinkTip>(Integer.parseInt(rows));
			page.setPageNo(Integer.parseInt(pagesize));
			List<MlinkTip> list = new ArrayList<MlinkTip>();
			Map<String, Object> argsMap = new HashMap<String, Object>();
			if ("country".equals(level)) {
				argsMap.put("countryid", id);
				list = tipService.getList(page, argsMap);
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

	@RequestMapping(path = "saveTip", method = RequestMethod.POST)
	public void saveTip(HttpServletRequest request, HttpServletResponse response) {
		String tip_countryid = request.getParameter("tip_countryid");
		String tip_tip = request.getParameter("tip_tip");
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			int effectrow = 0;
			MlinkTip tip = new MlinkTip();
			tip.setId(UUID.randomUUID().toString());
			tip.setTip(tip_tip);
			tip.setCountryid(tip_countryid);
			effectrow = tipService.save(tip);
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

	@RequestMapping(path = "deleteTip")
	public void deleteTip(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			int effectrow = 0;
			effectrow = tipService.delete(id);
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

	@RequestMapping(path = "updateTip", method = RequestMethod.POST)
	public void updateTip(HttpServletRequest request, HttpServletResponse response) {
		String tip_id = request.getParameter("tip_id");
		String tip_tip = request.getParameter("tip_tip");
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			int effectrow = 0;
			MlinkTip tip = new MlinkTip();
			tip.setId(tip_id);
			tip.setTip(tip_tip);
			effectrow = tipService.update(tip);
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
