package com.mlink.controller;

import java.io.IOException;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.gson.Gson;
import com.mlink.service.ICityService;
import com.mlink.service.ICountryService;
import com.mlink.service.IExchangeService;
import com.mlink.service.IFacilityService;
import com.mlink.service.INodeService;
import com.mlink.service.ISingleService;
import com.mlink.service.ITipService;
import com.mlink.view.MlinkCountry;
import com.mlink.view.MlinkExchange;
import com.mlink.view.MlinkFacility;
import com.mlink.view.MlinkSingle;
import com.mlink.view.MlinkTip;

@Controller
@RequestMapping("/machine")
public class MachineController {
	private Log log = LogFactory.getLog(getClass());
	@Autowired
	private ICountryService countryService;
	@Autowired
	private ICityService cityService;
	@Autowired
	private INodeService nodeService;
	@Autowired
	private IFacilityService facilityService;
	@Autowired
	private ISingleService singleService;
	@Autowired
	private IExchangeService exchangeService;
	@Autowired
	private ITipService tipService;

	@RequestMapping(path = "/index/{countryid}")
	public String index(@PathVariable String countryid, HttpServletRequest request) {
		MlinkCountry country = new MlinkCountry();
		try {
			country = countryService.getCountryById(countryid);
			if (country != null) {
				request.setAttribute("countryname", country.getName());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error(e);
		}
		request.setAttribute("countryid", countryid);
		if (1 == country.getFlag()) {
			return "machine_china";
		} else {
			return "machine";
		}
	}

	@RequestMapping(path = "/getFacilityList")
	public void getFacilityList(HttpServletRequest request, HttpServletResponse response) {
		String countryid = request.getParameter("countryid");
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> argsMap = new HashMap<String, Object>();
		argsMap.put("countryid", countryid);
		List<FacilityData> resultList = new ArrayList<FacilityData>();
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {

			double charge = 1.0;
			MlinkExchange exchange = exchangeService.getLastExchange();
			if (exchange != null) {
				charge = exchange.getExchange();
			}
			BigDecimal chargebg = new BigDecimal(charge);

			list = facilityService.getAllMapList(argsMap);
			if (list != null && list.size() > 0) {
				Iterator<Map<String, Object>> it = list.iterator();
				while (it.hasNext()) {
					Map<String, Object> map = it.next();
					List<MlinkFacility> datalist = new ArrayList<MlinkFacility>();
					FacilityData data = new FacilityData();
					if ("0".equals((String) map.get("type"))) {
						data.setType("0");
						data.setSize("1");
						MlinkFacility facility = new MlinkFacility();
						facility.setName((String) map.get("name"));
						facility.setPower((String) map.get("power"));
						BigDecimal origin = new BigDecimal((String) map.get("price"));
						facility.setPrice(origin.multiply(chargebg).setScale(0, BigDecimal.ROUND_UP).toString());
						facility.setStandard((String) map.get("standard"));
						datalist.add(facility);
					} else if ("1".equals((String) map.get("type"))) {
						data.setType("1");
						String name = map.get("name").toString();
						String[] standard = map.get("standard").toString().split(";");
						String[] power = map.get("power").toString().split(";");
						String[] price = map.get("price").toString().split(";");
						data.setSize(String.valueOf(standard.length));
						for (int i = 0; i < standard.length; i++) {
							MlinkFacility facility = new MlinkFacility();
							facility.setName(name);
							facility.setPower(power[i]);
							BigDecimal origin = new BigDecimal(price[i]);
							facility.setPrice(origin.multiply(chargebg).setScale(0, BigDecimal.ROUND_UP).toString());
							facility.setStandard(standard[i]);
							datalist.add(facility);
						}
					} else if ("2".equals((String) map.get("type"))) {
						data.setType("2");
						// (机房-托管规格)-机柜功率-机柜价格
						String name = map.get("name").toString();
						String[] power = map.get("power").toString().split(";");
						String[] price = map.get("price").toString().split(";");
						data.setSize(String.valueOf(power.length));
						for (int i = 0; i < power.length; i++) {
							MlinkFacility facility = new MlinkFacility();
							facility.setName(name);
							facility.setPower(power[i]);
							BigDecimal origin = new BigDecimal(price[i]);
							facility.setPrice(origin.multiply(chargebg).setScale(0, BigDecimal.ROUND_UP).toString());
							datalist.add(facility);
						}
					} else if ("3".equals((String) map.get("type"))) {
						data.setType("3");
						// (机房-托管规格)-(机柜功率-机柜价格)
						String name = map.get("name").toString();
						String power = map.get("power").toString();
						BigDecimal origin = new BigDecimal(power);
						data.setSize("1");
						MlinkFacility facility = new MlinkFacility();
						facility.setName(name);
						facility.setPower(origin.multiply(chargebg).setScale(0, BigDecimal.ROUND_UP).toString());
						datalist.add(facility);
					}
					data.setData(datalist);
					resultList.add(data);
				}
				resultMap.put("result", true);
				resultMap.put("data", resultList);
			} else {
				resultMap.put("result", false);
			}

			response.setContentType("application/json;charset=utf-8");
			response.getWriter().write(new Gson().toJson(resultMap));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error(e);
		}
	}

	@RequestMapping(path = "/getNodeList")
	public void getNodeList(HttpServletRequest request, HttpServletResponse response) {
		String countryid = request.getParameter("countryid");
		Map<String, Object> searchCityMap = new HashMap<String, Object>();
		searchCityMap.put("countryid", countryid);
		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<NodeData> resultList = new ArrayList<NodeData>();
		try {
			List<Map<String, Object>> cityList = cityService.getAllMapList(searchCityMap);
			if (cityList != null) {
				Iterator<Map<String, Object>> cityIt = cityList.iterator();
				while (cityIt.hasNext()) {
					Map<String, Object> cityMap = cityIt.next();
					NodeData data = new NodeData();
					data.setId((String) cityMap.get("id"));
					data.setName((String) cityMap.get("name"));
					List<NodeData> children = new ArrayList<NodeData>();
					Map<String, Object> searchNodeMap = new HashMap<String, Object>();
					searchNodeMap.put("cityid", cityMap.get("id"));
					List<Map<String, Object>> nodeList = nodeService.getAllMapList(searchNodeMap);
					if (nodeList != null) {
						Iterator<Map<String, Object>> nodeIt = nodeList.iterator();
						while (nodeIt.hasNext()) {
							Map<String, Object> nodeMap = nodeIt.next();
							NodeData child = new NodeData();
							child.setId((String) nodeMap.get("id"));
							child.setName((String) nodeMap.get("name"));
							child.setSize(nodeList.size());
							children.add(child);
						}
						data.setSize(nodeList.size());
					}
					data.setNode(children);
					resultList.add(data);
				}
			}
			resultMap.put("result", true);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			resultMap.put("result", false);
			log.error(e);
		} finally {
			try {
				resultMap.put("ret", resultList);
				response.setContentType("application/json;charset=utf-8");
				response.getWriter().write(new Gson().toJson(resultMap));
			} catch (Exception e) {
				// TODO: handle exception
				log.error(e);
			}
		}

	}

	@RequestMapping(path = "/getSingleEntity")
	public void getSingleEntity(HttpServletRequest request, HttpServletResponse response) {
		String nodeid = request.getParameter("nodeid");
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			MlinkSingle single = singleService.getSingleByNodeId(nodeid);
			resultMap.put("result", true);
			resultMap.put("single", single);
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

	@RequestMapping(path = "/getTipEntity")
	public void getTipEntity(HttpServletRequest request, HttpServletResponse response) {
		String countryid = request.getParameter("countryid");
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			MlinkTip tip = tipService.getTipByCountryId(countryid);
			resultMap.put("result", true);
			resultMap.put("tip", tip);
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

class FacilityData {
	private String type;
	private String size;
	private List<MlinkFacility> data;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public List<MlinkFacility> getData() {
		return data;
	}

	public void setData(List<MlinkFacility> data) {
		this.data = data;
	}

}

class NodeData {
	private String id;
	private String name;
	private int size;
	private List<NodeData> node;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public List<NodeData> getNode() {
		return node;
	}

	public void setNode(List<NodeData> node) {
		this.node = node;
	}

}
