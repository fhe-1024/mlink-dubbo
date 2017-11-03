package com.mlink.service;

import java.util.List;
import java.util.Map;

import com.mlink.util.PageUtil;
import com.mlink.view.MlinkTip;

public interface ITipService {
	public int save(MlinkTip tip) throws Exception;

	public List<Map<String, Object>> getAllMapList(Map<String, Object> map) throws Exception;

	public List<MlinkTip> getList(PageUtil<MlinkTip> page, Map<String, Object> map) throws Exception;

	public int delete(String id) throws Exception;

	public int update(MlinkTip tip) throws Exception;

	public MlinkTip getTipByCountryId(String countryid) throws Exception;
}
