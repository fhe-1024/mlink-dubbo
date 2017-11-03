package com.mlink.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mlink.dao.CountryDao;
import com.mlink.service.ICountryService;
import com.mlink.util.PageUtil;
import com.mlink.view.MlinkCountry;


@Service
@Transactional
public class CountryServiceImpl implements ICountryService {

	@Autowired
	private CountryDao countryDao;

	public List<Map<String, Object>> get() throws Exception {
		// TODO Auto-generated method stub
		return countryDao.get();
	}

	public int save(MlinkCountry country) throws Exception {
		// TODO Auto-generated method stub
		return countryDao.save(country);
	}

	public List<Map<String, Object>> getAllMapList(Map<String, Object> map) throws Exception {
		// TODO Auto-generated method stub

		return countryDao.getAllMapList(map);
	}

	public List<MlinkCountry> getList(PageUtil<MlinkCountry> page, Map<String, Object> map) throws Exception {
		// TODO Auto-generated method stub
		return	countryDao.getList(page, map);
	}

	public int delete(String id) throws Exception {
		// TODO Auto-generated method stub
		return countryDao.delete(id);
	}

	public int update(MlinkCountry country) throws Exception {
		// TODO Auto-generated method stub
		return countryDao.update(country);
	}

	public MlinkCountry getCountryById(String id) throws Exception {
		// TODO Auto-generated method stub
		return countryDao.getCountryById(id);
	}

}
