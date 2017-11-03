package com.mlink.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mlink.dao.TipDao;
import com.mlink.service.ITipService;
import com.mlink.util.PageUtil;
import com.mlink.view.MlinkTip;

@Service
@Transactional
public class TipServiceImpl implements ITipService {

	@Autowired
	private TipDao tipDao;

	public int save(MlinkTip tip) throws Exception {
		// TODO Auto-generated method stub
		return tipDao.save(tip);
	}

	public List<Map<String, Object>> getAllMapList(Map<String, Object> map) throws Exception {
		// TODO Auto-generated method stub
		return tipDao.getAllMapList(map);
	}

	public List<MlinkTip> getList(PageUtil<MlinkTip> page, Map<String, Object> map) throws Exception {
		// TODO Auto-generated method stub
		return tipDao.getList(page, map);
	}

	public int delete(String id) throws Exception {
		// TODO Auto-generated method stub
		return tipDao.delete(id);
	}

	public int update(MlinkTip tip) throws Exception {
		// TODO Auto-generated method stub
		return tipDao.update(tip);
	}

	public MlinkTip getTipByCountryId(String countryid) throws Exception {
		// TODO Auto-generated method stub
		return tipDao.getTipByCountryId(countryid);
	}

}
