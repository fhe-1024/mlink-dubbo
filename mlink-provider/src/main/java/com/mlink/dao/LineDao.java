package com.mlink.dao;

import java.util.List;
import java.util.Map;

import com.mlink.util.PageUtil;
import com.mlink.view.MlinkLine;


public interface LineDao {
	public int save(MlinkLine line) throws Exception;

	public List<Map<String, Object>> getAllMapList(Map<String, Object> map) throws Exception;

	public List<MlinkLine> getList(PageUtil<MlinkLine> page, Map<String, Object> map) throws Exception;

	public int delete(String id) throws Exception;

	public int update(MlinkLine line) throws Exception;
}
