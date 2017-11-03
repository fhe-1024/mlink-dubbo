package com.mlink.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
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
import com.mlink.service.ILineService;
import com.mlink.util.PageUtil;
import com.mlink.view.MlinkLine;

@Controller
@RequestMapping("/line")
public class LineController {

	private Log log = LogFactory.getLog(getClass());

	@Autowired
	private ILineService lineService;

	@RequestMapping(path = "/getLineList")
	public void getLineList(HttpServletRequest request, HttpServletResponse response) {
		String pagesize = request.getParameter("page");
		String rows = request.getParameter("rows");
		PageUtil<MlinkLine> page = new PageUtil<MlinkLine>(Integer.parseInt(rows));
		page.setPageNo(Integer.parseInt(pagesize));
		List<MlinkLine> resultList = new ArrayList<MlinkLine>();
		try {
			resultList = lineService.getList(page, new HashMap<String, Object>());
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

	@RequestMapping(path = "saveLine", method = RequestMethod.POST)
	public void saveLine(HttpServletRequest request, HttpServletResponse response) {
		String line_name = request.getParameter("line_name");
		String line_price = request.getParameter("line_price");
		String line_sort = request.getParameter("line_sort");
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			int effectrow = 0;
			MlinkLine line = new MlinkLine();
			line.setId(UUID.randomUUID().toString());
			line.setName(line_name);
			line.setPrice(Double.parseDouble(line_price));
			line.setSort(Integer.parseInt(line_sort));
			effectrow = lineService.save(line);
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

	@RequestMapping(path = "deleteLine")
	public void deleteLine(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			int effectrow = 0;
			effectrow = lineService.delete(id);
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

	@RequestMapping(path = "updateLine", method = RequestMethod.POST)
	public void updateLine(HttpServletRequest request, HttpServletResponse response) {
		String line_id = request.getParameter("line_id");
		String line_name = request.getParameter("line_name");
		String line_price = request.getParameter("line_price");
		String line_sort = request.getParameter("line_sort");
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			int effectrow = 0;
			MlinkLine line = new MlinkLine();
			line.setId(line_id);
			line.setName(line_name);
			line.setPrice(Double.parseDouble(line_price));
			line.setSort(Integer.parseInt(line_sort));
			effectrow = lineService.update(line);
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
