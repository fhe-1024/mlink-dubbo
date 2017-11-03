package com.mlink.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mlink.dao.FacilityDao;
import com.mlink.service.IFacilityService;
import com.mlink.util.PageUtil;
import com.mlink.view.MlinkFacility;

@Service
@Transactional
public class FacilityServiceImpl implements IFacilityService {

	@Resource
	private FacilityDao facilityDao;

	public int save(MlinkFacility facility) throws Exception {
		// TODO Auto-generated method stub
		return facilityDao.save(facility);
	}

	public List<Map<String, Object>> getAllMapList(Map<String, Object> map) throws Exception {
		// TODO Auto-generated method stub
		return facilityDao.getAllMapList(map);
	}

	public List<MlinkFacility> getList(PageUtil<MlinkFacility> page, Map<String, Object> map) throws Exception {
		// TODO Auto-generated method stub
		return facilityDao.getList(page, map);
	}

	public int delete(String id) throws Exception {
		// TODO Auto-generated method stub
		return facilityDao.delete(id);
	}

	public int update(MlinkFacility facility) throws Exception {
		// TODO Auto-generated method stub
		return facilityDao.update(facility);
	}

}
