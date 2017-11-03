package com.mlink.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mlink.dao.LineDao;
import com.mlink.service.ILineService;
import com.mlink.util.PageUtil;
import com.mlink.view.MlinkLine;

@Service
@Transactional
public class LineServiceImpl implements ILineService {
	@Autowired
	private LineDao lineDao;

	public int save(MlinkLine line) throws Exception {
		// TODO Auto-generated method stub
		return lineDao.save(line);
	}

	public List<Map<String, Object>> getAllMapList(Map<String, Object> map) throws Exception {
		// TODO Auto-generated method stub
		return lineDao.getAllMapList(map);
	}

	public List<MlinkLine> getList(PageUtil<MlinkLine> page, Map<String, Object> map) throws Exception {
		// TODO Auto-generated method stub
		return lineDao.getList(page, map);
	}

	public int delete(String id) throws Exception {
		// TODO Auto-generated method stub
		return lineDao.delete(id);
	}

	public int update(MlinkLine line) throws Exception {
		// TODO Auto-generated method stub
		return lineDao.update(line);
	}

}
