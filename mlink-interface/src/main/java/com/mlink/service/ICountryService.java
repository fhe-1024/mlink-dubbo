package com.mlink.service;

import java.util.List;
import java.util.Map;

import com.mlink.util.PageUtil;
import com.mlink.view.MlinkCountry;


public interface ICountryService {
	public List<Map<String, Object>> get() throws Exception;

	public int save(MlinkCountry country) throws Exception;

	public List<Map<String, Object>> getAllMapList(Map<String, Object> map) throws Exception;

	public List<MlinkCountry> getList(PageUtil<MlinkCountry> page,Map<String, Object> map) throws Exception;
	
	public int delete(String id) throws Exception;
	
	public int update(MlinkCountry country)throws Exception;
	
	public MlinkCountry getCountryById(String id)throws Exception;
}
