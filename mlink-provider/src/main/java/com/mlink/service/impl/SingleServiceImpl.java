package com.mlink.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mlink.dao.SingleDao;
import com.mlink.service.ISingleService;
import com.mlink.util.PageUtil;
import com.mlink.view.MlinkSingle;

@Service
@Transactional
public class SingleServiceImpl implements ISingleService {

	@Autowired
	private SingleDao singleDao;

	public int save(MlinkSingle single) throws Exception {
		// TODO Auto-generated method stub
		return singleDao.save(single);
	}

	public List<Map<String, Object>> getAllMapList(Map<String, Object> map) throws Exception {
		// TODO Auto-generated method stub
		return singleDao.getAllMapList(map);
	}

	public List<MlinkSingle> getList(PageUtil<MlinkSingle> page, Map<String, Object> map) throws Exception {
		// TODO Auto-generated method stub
		return	singleDao.getList(page, map);
	}

	public int delete(String id) throws Exception {
		// TODO Auto-generated method stub
		return singleDao.delete(id);
	}

	public int update(MlinkSingle single) throws Exception {
		// TODO Auto-generated method stub
		return singleDao.update(single);
	}

	public MlinkSingle getSingleByNodeId(String nodeid) throws Exception {
		// TODO Auto-generated method stub
		return singleDao.getSingleByNodeId(nodeid);
	}

}
