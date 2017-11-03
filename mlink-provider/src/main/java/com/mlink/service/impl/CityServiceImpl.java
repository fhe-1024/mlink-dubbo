package com.mlink.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mlink.dao.CityDao;
import com.mlink.service.ICityService;
import com.mlink.util.PageUtil;
import com.mlink.view.MlinkCity;


@Service
@Transactional
public class CityServiceImpl implements ICityService {

	@Autowired
	private CityDao cityDao;

	public List<Map<String, Object>> getValueByCountryID(String countryid) throws Exception {
		// TODO Auto-generated method stub
		return cityDao.getValueByCountryID(countryid);
	}

	public int save(MlinkCity city) throws Exception {
		// TODO Auto-generated method stub
		return cityDao.save(city);
	}

	public List<Map<String, Object>> getAllMapList(Map<String, Object> map) throws Exception {
		// TODO Auto-generated method stub
		return cityDao.getAllMapList(map);
	}

	public List<MlinkCity> getList(PageUtil<MlinkCity> page, Map<String, Object> map) throws Exception {
		// TODO Auto-generated method stub
		return cityDao.getList(page, map);
	}

	public int delete(String id) throws Exception {
		// TODO Auto-generated method stub
		return cityDao.delete(id);
	}

	public int update(MlinkCity city) throws Exception {
		// TODO Auto-generated method stub
		return cityDao.update(city);
	}

}
