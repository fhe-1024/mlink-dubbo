package com.mlink.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.mlink.dao.CityDao;
import com.mlink.util.PageUtil;
import com.mlink.view.MlinkCity;

@Repository
public class CityDaoImpl implements CityDao {

	private Log log = LogFactory.getLog(getClass());

	private JdbcTemplate jdbctemplate;

	private NamedParameterJdbcTemplate namedJdbcTemplate;

	@Autowired
	public void init(DataSource dataSource) {
		jdbctemplate = new JdbcTemplate(dataSource);

	}

	@Autowired
	public void initJdbc(DataSource dataSource) {
		namedJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}

	public List<Map<String, Object>> getValueByCountryID(String countryid) throws Exception {
		// TODO Auto-generated method stub
		StringBuilder sb = new StringBuilder(
				"select id, name,sort,countryid from mlink_city where countryid=:countryid limit :pagestart,:pageend");
		Map<String, Object> argsMap = new HashMap<String, Object>();
		argsMap.put("countryid", countryid);
		argsMap.put("pagestart", 0);
		argsMap.put("pageend", 10);
		List<Map<String, Object>> actors = this.namedJdbcTemplate.query(sb.toString(), argsMap,
				new RowMapper<Map<String, Object>>() {
					public Map<String, Object> mapRow(ResultSet rs, int rowNum) throws SQLException {
						Map<String, Object> map = new HashMap<String, Object>();
						map.put("id", rs.getString("id"));
						map.put("name", rs.getString("name"));
						map.put("sort", rs.getInt("sort"));
						map.put("countryid", rs.getString("countryid"));
						return map;
					}
				});
		return actors;

	}

	public List<Map<String, Object>> getValueById() throws Exception {
		StringBuilder sb = new StringBuilder(
				"select id, name,sort,countryid from mlink_city where countryid=:countryid limit :pagestart,:pageend");
		Map<String, Object> argsMap = new HashMap<String, Object>();
		argsMap.put("countryid", "1");
		argsMap.put("pagestart", 0);
		argsMap.put("pageend", 10);
		List<Map<String, Object>> actors = this.namedJdbcTemplate.query(sb.toString(), argsMap,
				new RowMapper<Map<String, Object>>() {
					public Map<String, Object> mapRow(ResultSet rs, int rowNum) throws SQLException {
						Map<String, Object> map = new HashMap<String, Object>();
						map.put("id", rs.getString("id"));
						map.put("name", rs.getString("name"));
						map.put("sort", rs.getInt("sort"));
						map.put("countryid", rs.getString("countryid"));
						return map;
					}
				});
		return actors;
	}

	public int save(MlinkCity city) throws Exception {
		// TODO Auto-generated method stub
		int test = this.jdbctemplate.update("insert into mlink_city (id,name,sort,countryid) values (?,?,?,?)",
				city.getId(), city.getName(), city.getSort(), city.getCountryid());
		log.info(test);
		return test;
	}

	public List<Map<String, Object>> getAllMapList(Map<String, Object> map) throws Exception {
		// TODO Auto-generated method stub
		StringBuilder sb = new StringBuilder();
		Map<String, Object> argsMap = new HashMap<String, Object>();
		sb.append("select id,name,sort from mlink_city where 1=1 ");
		if (StringUtils.isNotBlank((String) map.get("countryid"))) {
			sb.append(" and countryid=:countryid ");
			argsMap.put("countryid", map.get("countryid"));
		}
		sb.append(" order by sort ");
		List<Map<String, Object>> actors = this.namedJdbcTemplate.query(sb.toString(), argsMap,
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

	public List<MlinkCity> getList(PageUtil<MlinkCity> page, Map<String, Object> map) throws Exception {
		// TODO Auto-generated method stub
		StringBuilder sb = new StringBuilder();
		StringBuilder countsb = new StringBuilder();
		Map<String, Object> searchMap = new HashMap<String, Object>();
		Map<String, Object> countMap = new HashMap<String, Object>();

		sb.append("select id,name,sort from mlink_city where 1=1 ");
		countsb.append("select count(id) from mlink_city where 1=1 ");
		if (StringUtils.isNotBlank((String) map.get("countryid"))) {
			sb.append("and countryid=:countryid ");
			searchMap.put("countryid", map.get("countryid"));
			countsb.append(" and  countryid=:countryid ");
			countMap.put("countryid", map.get("countryid"));
		}
		sb.append(" order by sort ");
		sb.append(" and ROWNUM< :start,:end ");
		searchMap.put("start", page.getFirst() - 1);
		searchMap.put("end", page.getPageSize());
		List<MlinkCity> actors = this.namedJdbcTemplate.query(sb.toString(), searchMap, new RowMapper<MlinkCity>() {
			public MlinkCity mapRow(ResultSet rs, int rowNum) throws SQLException {
				// TODO Auto-generated method stub
				MlinkCity city = new MlinkCity();
				city.setId(rs.getString("id"));
				city.setName(rs.getString("name"));
				city.setSort(rs.getInt("sort"));
				return city;
			}
		});
		Integer total = this.namedJdbcTemplate.queryForObject(countsb.toString(), countMap, Integer.class);
		page.setTotalCount(total);
		return actors;
	}

	public int delete(String id) throws Exception {
		// TODO Auto-generated method stub
		return jdbctemplate.update("delete from mlink_city where id=?", id);
	}

	public int update(MlinkCity city) throws Exception {
		// TODO Auto-generated method stub
		return jdbctemplate.update("update mlink_city set name=? ,sort=? where id=?", city.getName(), city.getSort(),
				city.getId());
	}

}
