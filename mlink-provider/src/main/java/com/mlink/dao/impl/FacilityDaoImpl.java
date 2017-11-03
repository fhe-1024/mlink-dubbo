package com.mlink.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.mlink.dao.FacilityDao;
import com.mlink.util.PageUtil;
import com.mlink.view.MlinkFacility;

@Repository
public class FacilityDaoImpl implements FacilityDao {

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

	public int save(MlinkFacility facility) throws Exception {
		// TODO Auto-generated method stub
		int test = this.jdbctemplate.update(
				"insert into mlink_facility (id,name,standard,power,price,type,sort,countryid) values (?,?,?,?,?,?,?,?)",
				facility.getId(), facility.getName(), facility.getStandard(), facility.getPower(), facility.getPrice(),
				facility.getType(), facility.getSort(), facility.getCountryid());
		return test;
	}

	public List<Map<String, Object>> getAllMapList(Map<String, Object> map) throws Exception {
		// TODO Auto-generated method stub
		StringBuilder sb = new StringBuilder();
		sb.append("select id,name,standard,power,price,type,sort,countryid from mlink_facility where 1=1 ");

		Map<String, Object> argsMap = new HashMap<String, Object>();
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
						map.put("standard", rs.getString("standard"));
						map.put("power", rs.getString("power"));
						map.put("price", rs.getString("price"));
						map.put("type", rs.getString("type"));
						map.put("sort", rs.getInt("sort"));
						map.put("countryid", rs.getString("countryid"));
						return map;
					}
				});
		return actors;
	}

	public List<MlinkFacility> getList(PageUtil<MlinkFacility> page, Map<String, Object> map) throws Exception {
		// TODO Auto-generated method stub
		StringBuilder sb = new StringBuilder();
		StringBuilder countsb = new StringBuilder();
		Map<String, Object> searchMap = new HashMap<String, Object>();
		sb.append("select id,name,standard,power,price,type,sort,countryid from mlink_facility where 1=1 ");
		countsb.append("select count(id) from mlink_facility where 1=1");
		Map<String, Object> countMap = new HashMap<String, Object>();
		if (StringUtils.isNotBlank((String) map.get("countryid"))) {
			sb.append("and countryid=:countryid ");
			searchMap.put("countryid", map.get("countryid"));
			countsb.append(" and  countryid=:countryid ");
			countMap.put("countryid", map.get("countryid"));
		}
		sb.append(" order by sort ");
		sb.append("limit :start,:end ");
		searchMap.put("start", page.getFirst() - 1);
		searchMap.put("end", page.getPageSize());
		List<MlinkFacility> actors = this.namedJdbcTemplate.query(sb.toString(), searchMap,
				new RowMapper<MlinkFacility>() {
					public MlinkFacility mapRow(ResultSet rs, int rowNum) throws SQLException {
						// TODO Auto-generated method stub
						MlinkFacility facility = new MlinkFacility();
						facility.setId(rs.getString("id"));
						facility.setName(rs.getString("name"));
						facility.setStandard(rs.getString("standard"));
						facility.setPower(rs.getString("power"));
						facility.setPrice(rs.getString("price"));
						facility.setType(rs.getInt("type"));
						facility.setSort(rs.getInt("sort"));
						facility.setCountryid(rs.getString("countryid"));
						return facility;
					}
				});
		page.setResult(actors);
		Integer total = this.namedJdbcTemplate.queryForObject(countsb.toString(), countMap, Integer.class);
		page.setTotalCount(total);
		return actors;
	}

	public int delete(String id) throws Exception {
		// TODO Auto-generated method stub
		return jdbctemplate.update("delete from mlink_facility where id=?", id);
	}

	public int update(MlinkFacility facility) throws Exception {
		// TODO Auto-generated method stub
		return jdbctemplate.update(
				"update mlink_facility set name=?,standard=?,power=?,price=?,type=?,sort=? where id=?",
				facility.getName(), facility.getStandard(), facility.getPower(), facility.getPrice(),
				facility.getType(), facility.getSort(), facility.getId());
	}

}
