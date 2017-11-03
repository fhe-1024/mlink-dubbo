package com.mlink.service;

import java.util.List;
import java.util.Map;

import com.mlink.util.PageUtil;
import com.mlink.view.MlinkExchange;

public interface IExchangeService {
	public int save(MlinkExchange exchange) throws Exception;

	public MlinkExchange getLastExchange() throws Exception;

	public List<MlinkExchange> getList(PageUtil<MlinkExchange> page, Map<String, Object> map) throws Exception;
}
