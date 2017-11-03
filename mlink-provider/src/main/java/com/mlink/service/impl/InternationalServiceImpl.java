package com.mlink.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mlink.dao.InternationalDao;
import com.mlink.service.IInternationalService;
import com.mlink.util.PageUtil;
import com.mlink.view.MlinkInternational;

@Transactional
@Service
public class InternationalServiceImpl implements IInternationalService {

	@Autowired
	private InternationalDao internationalDao;

	public int save(MlinkInternational international) throws Exception {
		// TODO Auto-generated method stub
		return internationalDao.save(international);
	}

	public List<Map<String, Object>> getAllMapList(Map<String, Object> map) throws Exception {
		// TODO Auto-generated method stub
		return internationalDao.getAllMapList(map);
	}

	public List<MlinkInternational> getList(PageUtil<MlinkInternational> page, Map<String, Object> map)
			throws Exception {
		// TODO Auto-generated method stub
		return internationalDao.getList(page, map);
	}

	public int delete(String id) throws Exception {
		// TODO Auto-generated method stub
		return internationalDao.delete(id);
	}

	public int update(MlinkInternational international) throws Exception {
		// TODO Auto-generated method stub
		return internationalDao.update(international);
	}

}
