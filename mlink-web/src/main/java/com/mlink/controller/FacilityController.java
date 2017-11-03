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
import com.mlink.service.IFacilityService;
import com.mlink.service.IInternationalService;
import com.mlink.util.PageUtil;
import com.mlink.view.MlinkFacility;
import com.mlink.view.TreeData;

@Controller
@RequestMapping("/facility")
public class FacilityController {

	private Log log = LogFactory.getLog(getClass());

	@Autowired
	private IInternationalService internationalService;
	@Autowired
	private ICountryService countryService;
	@Autowired
	private IFacilityService facilityService;

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

	@RequestMapping(path = "getFacilityList")
	public void getFacilityList(HttpServletRequest request, HttpServletResponse response) {
		String pagesize = request.getParameter("page");
		String rows = request.getParameter("rows");
		String level = request.getParameter("level");
		String id = request.getParameter("id");
		try {
			PageUtil<MlinkFacility> page = new PageUtil<MlinkFacility>(Integer.parseInt(rows));
			page.setPageNo(Integer.parseInt(pagesize));
			List<MlinkFacility> list = new ArrayList<MlinkFacility>();
			Map<String, Object> argsMap = new HashMap<String, Object>();
			if ("country".equals(level)) {
				argsMap.put("countryid", id);
				list = facilityService.getList(page, argsMap);
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

	@RequestMapping(path = "saveFacility", method = RequestMethod.POST)
	public void saveFacility(HttpServletRequest request, HttpServletResponse response) {
		String facility_countryid = request.getParameter("facility_countryid");
		String facility_type = request.getParameter("facility_type");
		String facility_sort = request.getParameter("facility_sort");
		String facility_name = request.getParameter("facility_name");
		String facility_standard = request.getParameter("facility_standard");
		String facility_power = request.getParameter("facility_power");
		String facility_price = request.getParameter("facility_price");
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			int effectrow = 0;
			MlinkFacility facility = new MlinkFacility();
			facility.setId(UUID.randomUUID().toString());
			facility.setCountryid(facility_countryid);
			facility.setName(facility_name);
			facility.setType(Integer.parseInt(facility_type));
			facility.setSort(Integer.parseInt(facility_sort));
			facility.setStandard(facility_standard);
			facility.setPower(facility_power);
			facility.setPrice(facility_price);
			effectrow = facilityService.save(facility);
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

	@RequestMapping(path = "deleteFacility")
	public void deleteFacility(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			int effectrow = 0;
			effectrow = facilityService.delete(id);
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

	@RequestMapping(path = "updateFacility", method = RequestMethod.POST)
	public void updateFacility(HttpServletRequest request, HttpServletResponse response) {
		String facility_id = request.getParameter("facility_id");
		String facility_type = request.getParameter("facility_type");
		String facility_sort = request.getParameter("facility_sort");
		String facility_name = request.getParameter("facility_name");
		String facility_standard = request.getParameter("facility_standard");
		String facility_power = request.getParameter("facility_power");
		String facility_price = request.getParameter("facility_price");
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			int effectrow = 0;
			MlinkFacility facility = new MlinkFacility();
			facility.setId(facility_id);
			facility.setName(facility_name);
			facility.setType(Integer.parseInt(facility_type));
			facility.setSort(Integer.parseInt(facility_sort));
			facility.setStandard(facility_standard);
			facility.setPower(facility_power);
			facility.setPrice(facility_price);
			effectrow = facilityService.update(facility);
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
