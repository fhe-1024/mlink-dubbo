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

import com.mlink.dao.CountryDao;
import com.mlink.util.PageUtil;
import com.mlink.view.MlinkCountry;


@Repository
public class CountryDaoImpl implements CountryDao {

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

	public List<Map<String, Object>> get() throws Exception {
		// TODO Auto-generated method stub
		List<Map<String, Object>> actors = this.jdbctemplate
				.query("select id, name,sort from mlink_country order by sort", new RowMapper<Map<String, Object>>() {
					public Map<String, Object> mapRow(ResultSet rs, int rowNum) throws SQLException {
						Map<String, Object> map = new HashMap<String, Object>();
						map.put("id", rs.getString("id"));
						map.put("name", rs.getString("name"));
						map.put("sort", rs.getInt("sort"));
						return map;
					}
				});
		return actors;
	}

	public int save(MlinkCountry country) throws Exception {
		// TODO Auto-generated method stub
		int test = this.jdbctemplate.update("insert into mlink_country (id,name,sort,internationalid) values (?,?,?,?)",
				country.getId(), country.getName(), country.getSort(), country.getInternationalid());
		log.info(test);
		return test;
	}

	public List<Map<String, Object>> getAllMapList(Map<String, Object> map) throws Exception {
		// TODO Auto-generated method stub
		StringBuilder sb = new StringBuilder();
		sb.append("select id,name,sort from mlink_country where 1=1 ");
		Map<String, Object> argsMap = new HashMap<String, Object>();
		if (StringUtils.isNotBlank((String) map.get("internationalid"))) {
			sb.append(" and internationalid=:internationalid ");
			argsMap.put("internationalid", map.get("internationalid"));
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

	public List<MlinkCountry> getList(PageUtil<MlinkCountry> page, Map<String, Object> map) throws Exception {
		// TODO Auto-generated method stub
		StringBuilder sb = new StringBuilder();
		StringBuilder countsb = new StringBuilder();
		countsb.append("select count(id) from mlink_country where 1=1");
		Map<String, Object> searchMap = new HashMap<String, Object>();
		Map<String, Object> countMap = new HashMap<String, Object>();
		sb.append("select id,name,sort from mlink_country where 1=1 ");

		if (StringUtils.isNotBlank((String) map.get("internationalid"))) {
			sb.append("and internationalid=:internationalid ");
			searchMap.put("internationalid", map.get("internationalid"));
			countsb.append(" and  internationalid=:internationalid ");
			countMap.put("internationalid", map.get("internationalid"));
		}
		sb.append(" order by sort ");
		sb.append("limit :start,:end ");
		searchMap.put("start", page.getFirst() - 1);
		searchMap.put("end", page.getPageSize());
		
		List<MlinkCountry> actors = this.namedJdbcTemplate.query(sb.toString(), searchMap,
				new RowMapper<MlinkCountry>() {
					public MlinkCountry mapRow(ResultSet rs, int rowNum) throws SQLException {
						// TODO Auto-generated method stub
						MlinkCountry country = new MlinkCountry();
						country.setId(rs.getString("id"));
						country.setName(rs.getString("name"));
						country.setSort(rs.getInt("sort"));
						return country;
					}
				});
		Integer total = this.namedJdbcTemplate.queryForObject(countsb.toString(), countMap, Integer.class);
		page.setTotalCount(total);
		return actors;
	}

	public int delete(String id) throws Exception {
		// TODO Auto-generated method stub
		return jdbctemplate.update("delete from mlink_country where id=?", id);
	}

	public int update(MlinkCountry country) throws Exception {
		// TODO Auto-generated method stub
		return jdbctemplate.update("update mlink_country set name=? ,sort=? where id=?", country.getName(),
				country.getSort(), country.getId());
	}

	public MlinkCountry getCountryById(String id) throws Exception {
		// TODO Auto-generated method stub
		MlinkCountry country = this.jdbctemplate.queryForObject(
				"select id,name,sort,flag from mlink_country where 1=1 and id=?", new Object[] { id },
				new RowMapper<MlinkCountry>() {
					public MlinkCountry mapRow(ResultSet rs, int rowNum) throws SQLException {
						// TODO Auto-generated method stub
						MlinkCountry actor = new MlinkCountry();
						actor.setId(rs.getString("id"));
						actor.setName(rs.getString("name"));
						actor.setSort(rs.getInt("sort"));
						actor.setFlag(rs.getInt("flag"));
						return actor;
					}
				});
		return country;
	}

}
