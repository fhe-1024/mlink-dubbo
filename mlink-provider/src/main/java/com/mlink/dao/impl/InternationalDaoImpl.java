package com.mlink.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.mlink.dao.InternationalDao;
import com.mlink.util.PageUtil;
import com.mlink.view.MlinkInternational;

@Repository
public class InternationalDaoImpl implements InternationalDao {

	private Log log = LogFactory.getLog(getClass());

	private JdbcTemplate jdbctemplate;

	private NamedParameterJdbcTemplate namedJdbcTemplate;

	@Autowired
	public void initJdbc(DataSource dataSource) {
		namedJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}

	@Autowired
	public void init(DataSource dataSource) {
		jdbctemplate = new JdbcTemplate(dataSource);
	}

	public int save(MlinkInternational international) throws Exception {
		// TODO Auto-generated method stub
		int test = this.jdbctemplate.update("insert into mlink_international (id,name,sort) values (?,?,?)",
				international.getId(), international.getName(), international.getSort());
		log.info(test);
		return test;
	}

	public List<Map<String, Object>> getAllMapList(Map<String, Object> map) throws Exception {
		// TODO Auto-generated method stub
		StringBuilder sb = new StringBuilder();
		sb.append("select id,name,sort from mlink_international where 1=1 ");
		sb.append(" order by sort ");
		List<Map<String, Object>> actors = this.namedJdbcTemplate.query(sb.toString(), new HashMap<String, Object>(),
				new RowMapper<Map<String, Object>>() {
					public Map<String, Object> mapRow(ResultSet rs, int rowNum) throws SQLException {
						// TODO Auto-generated method stub
						Map<String, Object> map = new HashMap<String, Object>();
						map.put("id", rs.getString("id"));
						map.put("name", rs.getString("name"));
						map.put("sort", rs.getInt("sort"));
						return map;
					}
				});
		return actors;
	}

	public List<MlinkInternational> getList(PageUtil<MlinkInternational> page, Map<String, Object> map)
			throws Exception {
		// TODO Auto-generated method stub
		StringBuilder sb = new StringBuilder();
		StringBuilder countsb = new StringBuilder();
		Map<String, Object> searchMap = new HashMap<String, Object>();
		sb.append("select id,name,sort from mlink_international where 1=1 ");
		sb.append(" order by sort ");
		sb.append("limit :start,:end ");
		searchMap.put("start", page.getFirst() - 1);
		searchMap.put("end", page.getPageSize());
		countsb.append("select count(id) from mlink_international where 1=1");
		List<MlinkInternational> actors = this.namedJdbcTemplate.query(sb.toString(), searchMap,
				new RowMapper<MlinkInternational>() {
					public MlinkInternational mapRow(ResultSet rs, int rowNum) throws SQLException {
						// TODO Auto-generated method stub
						MlinkInternational country = new MlinkInternational();
						country.setId(rs.getString("id"));
						country.setName(rs.getString("name"));
						country.setSort(rs.getInt("sort"));
						return country;
					}
				});
		Integer total = this.namedJdbcTemplate.queryForObject(countsb.toString(), new HashMap<String, Object>(),
				Integer.class);
		page.setTotalCount(total);
		return actors;
	}

	public int delete(String id) throws Exception {
		// TODO Auto-generated method stub
		return jdbctemplate.update("delete from mlink_international where id=?", id);
	}

	public int update(MlinkInternational international) throws Exception {
		// TODO Auto-generated method stub
		return jdbctemplate.update("update mlink_international set name=? ,sort=? where id=?", international.getName(),
				international.getSort(), international.getId());
	}

}
