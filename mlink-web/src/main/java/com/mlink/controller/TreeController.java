package com.mlink.controller;

import java.io.File;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.google.gson.Gson;
import com.mlink.service.ICityService;
import com.mlink.service.ICountryService;
import com.mlink.service.IInternationalService;
import com.mlink.service.INodeService;
import com.mlink.util.PageUtil;
import com.mlink.util.PropertiesUtil;
import com.mlink.view.MlinkCity;
import com.mlink.view.MlinkCountry;
import com.mlink.view.MlinkInternational;
import com.mlink.view.MlinkNode;
import com.mlink.view.TreeData;

@Controller
@RequestMapping("/tree")
public class TreeController {

	private Log log = LogFactory.getLog(getClass());

	@Autowired
	private IInternationalService internationalService;
	@Autowired
	private ICountryService countryService;
	@Autowired
	private ICityService cityService;
	@Autowired
	private INodeService nodeService;

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
					first.setSort((Integer) internationalMap.get("sort"));
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
							second.setSort((Integer) countryMap.get("sort"));
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
									three.setSort((Integer) cityMap.get("sort"));
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
											four.setSort((Integer) nodeMap.get("sort"));
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

	@RequestMapping(path = "getNodeList")
	@SuppressWarnings(value = {"unchecked" })
	public void getNodeList(HttpServletResponse response, HttpServletRequest request) {
		String pagesize = request.getParameter("page");
		String rows = request.getParameter("rows");
		String level = request.getParameter("level");
		String id = request.getParameter("id");
		try {
			PageUtil<?> page = null;
			List<?> list = new ArrayList<Object>();
			Map<String, Object> argsMap = new HashMap<String, Object>();
			if ("mlink".equals(level) || StringUtils.isBlank(level)) {
				page = new PageUtil<MlinkInternational>(Integer.parseInt(rows));
				page.setPageNo(Integer.parseInt(pagesize));
				list = internationalService.getList((PageUtil<MlinkInternational>) page, argsMap);
			} else if ("international".equals(level)) {
				argsMap.put("internationalid", id);
				page = new PageUtil<MlinkCountry>(Integer.parseInt(rows));
				page.setPageNo(Integer.parseInt(pagesize));
				list = countryService.getList((PageUtil<MlinkCountry>) page, argsMap);
			} else if ("country".equals(level)) {
				argsMap.put("countryid", id);
				page = new PageUtil<MlinkCity>(Integer.parseInt(rows));
				page.setPageNo(Integer.parseInt(pagesize));
				list = cityService.getList((PageUtil<MlinkCity>) page, argsMap);
			} else if ("city".equals(level)) {
				argsMap.put("cityid", id);
				page = new PageUtil<MlinkNode>(Integer.parseInt(rows));
				page.setPageNo(Integer.parseInt(pagesize));
				list = nodeService.getList((PageUtil<MlinkNode>) page, argsMap);
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

	@RequestMapping(path = "saveLevel")
	public void saveLevel(HttpServletResponse response, HttpServletRequest request) {
		log.info("/area/saveLevel invoked");
		String nodeid = request.getParameter("levelid");
		String nodelevel = request.getParameter("levelname");
		String name = request.getParameter("level_name");
		String sort = request.getParameter("sort");
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			int effectrow = 0;
			String id = UUID.randomUUID().toString();
			String level = "";
			if ("mlink".equals(nodelevel) || StringUtils.isBlank(nodelevel)) {
				MlinkInternational international = new MlinkInternational();
				international.setId(id);
				international.setName(name);
				international.setSort(Integer.parseInt(sort));
				effectrow = internationalService.save(international);
				level = "international";
			} else if ("international".equals(nodelevel)) {
				MlinkCountry country = new MlinkCountry();
				country.setId(id);
				country.setName(name);
				country.setSort(Integer.parseInt(sort));
				country.setInternationalid(nodeid);
				effectrow = countryService.save(country);
				level = "country";
			} else if ("country".equals(nodelevel)) {
				MlinkCity city = new MlinkCity();
				city.setId(id);
				city.setName(name);
				city.setSort(Integer.parseInt(sort));
				city.setCountryid(nodeid);
				effectrow = cityService.save(city);
				level = "city";
			} else if ("city".equals(nodelevel)) {
				MlinkNode node = new MlinkNode();
				node.setId(id);
				node.setName(name);
				node.setSort(Integer.parseInt(sort));
				node.setCityid(nodeid);
				effectrow = nodeService.save(node);
				level = "node";
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

	@RequestMapping(path = "deleteLevel")
	public void deleteLevel(HttpServletRequest request, HttpServletResponse response) {
		String nodeid = request.getParameter("nodeid");
		String nodelevel = request.getParameter("nodelevel");
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			int effectrow = 0;
			if ("mlink".equals(nodelevel) || StringUtils.isBlank(nodelevel)) {

			} else if ("international".equals(nodelevel)) {
				effectrow = internationalService.delete(nodeid);
			} else if ("country".equals(nodelevel)) {
				effectrow = countryService.delete(nodeid);
			} else if ("city".equals(nodelevel)) {
				effectrow = cityService.delete(nodeid);
			} else if ("node".equals(nodelevel)) {
				effectrow = nodeService.delete(nodeid);
			}
			if (effectrow > 0) {
				resultMap.put("id", nodeid);
				resultMap.put("level", nodelevel);
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
			} catch (Exception e) {
				// TODO: handle exception
				log.error(e);
			}
		}

	}

	@RequestMapping(path = "updateLevel")
	public void updateLevel(HttpServletResponse response, HttpServletRequest request) {
		log.info("/area/saveLevel invoked");
		String nodeid = request.getParameter("levelid");
		String nodelevel = request.getParameter("levelname");
		String name = request.getParameter("level_name");
		String sort = request.getParameter("sort");
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			int effectrow = 0;
			if ("international".equals(nodelevel) || StringUtils.isBlank(nodelevel)) {
				MlinkInternational international = new MlinkInternational();
				international.setId(nodeid);
				international.setName(name);
				international.setSort(Integer.parseInt(sort));
				effectrow = internationalService.update(international);
			} else if ("country".equals(nodelevel)) {
				MlinkCountry country = new MlinkCountry();
				country.setId(nodeid);
				country.setName(name);
				country.setSort(Integer.parseInt(sort));
				effectrow = countryService.update(country);
			} else if ("city".equals(nodelevel)) {
				MlinkCity city = new MlinkCity();
				city.setId(nodeid);
				city.setName(name);
				city.setSort(Integer.parseInt(sort));
				effectrow = cityService.update(city);
			} else if ("node".equals(nodelevel)) {
				MlinkNode node = new MlinkNode();
				node.setId(nodeid);
				node.setName(name);
				node.setSort(Integer.parseInt(sort));
				effectrow = nodeService.update(node);
			}

			if (effectrow > 0) {
				resultMap.put("id", nodeid);
				resultMap.put("level", nodelevel);
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

	@Deprecated
	@RequestMapping(path = "saveNode")
	public void saveNode(@RequestParam("pic") CommonsMultipartFile file, HttpServletResponse response,
			HttpServletRequest request) {
		log.info("/area/saveNode invoked");
		String nodeid = request.getParameter("nodeid");
		String nodelevel = request.getParameter("nodelevel");
		String name = request.getParameter("node_name");
		String sort = request.getParameter("sort");
		String area = request.getParameter("area");
		String protocol = request.getParameter("protocol");
		String electricity = request.getParameter("electricity");
		String authentication = request.getParameter("authentication");
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {

			// 处理上传文件
			if (file.getSize() > 0) {
				file.transferTo(new File(PropertiesUtil.MLINK_PIC + "\\11.txt"));
			}

			int effectrow = 0;
			String id = UUID.randomUUID().toString();
			String level = "";
			if ("city".equals(nodelevel)) {
				MlinkNode node = new MlinkNode();
				node.setId(id);
				node.setName(name);
				node.setSort(Integer.parseInt(sort));
				node.setCityid(nodeid);
				node.setArea(area);
				node.setProtocol(protocol);
				node.setElectricity(electricity);
				node.setAuthentication(authentication);

				effectrow = nodeService.save(node);
				level = "node";
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
