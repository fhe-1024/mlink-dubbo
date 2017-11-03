package com.mlink.service;

import java.util.List;
import java.util.Map;

import com.mlink.util.PageUtil;
import com.mlink.view.MlinkInternational;

public interface IInternationalService {
	public int save(MlinkInternational international) throws Exception;

	public List<Map<String, Object>> getAllMapList(Map<String, Object> map) throws Exception;

	public List<MlinkInternational> getList(PageUtil<MlinkInternational> page, Map<String, Object> map) throws Exception;

	public int delete(String id) throws Exception;
	
	public int update(MlinkInternational international)throws Exception;
}
