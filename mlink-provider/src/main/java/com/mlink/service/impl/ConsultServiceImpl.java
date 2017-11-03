package com.mlink.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mlink.dao.ConsultDao;
import com.mlink.service.IConsultService;
import com.mlink.util.PageUtil;
import com.mlink.view.MlinkConsult;


@Service
@Transactional
public class ConsultServiceImpl implements IConsultService {
	@Autowired
	private ConsultDao consultDao;

	public int save(MlinkConsult consult) throws Exception {
		// TODO Auto-generated method stub
		return consultDao.save(consult);
	}

	public List<Map<String, Object>> getAllMapList(Map<String, Object> map) throws Exception {
		// TODO Auto-generated method stub
		return consultDao.getAllMapList(map);
	}

	public List<MlinkConsult> getList(PageUtil<MlinkConsult> page, Map<String, Object> map) throws Exception {
		// TODO Auto-generated method stub
		return consultDao.getList(page, map);
	}

	public int delete(String id) throws Exception {
		// TODO Auto-generated method stub
		return consultDao.delete(id);
	}

	public int update(MlinkConsult consult) throws Exception {
		// TODO Auto-generated method stub
		return consultDao.update(consult);
	}

}
