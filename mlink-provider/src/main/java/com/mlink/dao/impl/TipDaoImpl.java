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

import com.mlink.dao.TipDao;
import com.mlink.util.PageUtil;
import com.mlink.view.MlinkTip;


@Repository
public class TipDaoImpl implements TipDao {

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

	public int save(MlinkTip tip) throws Exception {
		// TODO Auto-generated method stub
		int test = this.jdbctemplate.update("insert into mlink_tip (id,tip,countryid) values (?,?,?)", tip.getId(),
				tip.getTip(), tip.getCountryid());
		return test;
	}

	public List<Map<String, Object>> getAllMapList(Map<String, Object> map) throws Exception {
		// TODO Auto-generated method stub
		StringBuilder sb = new StringBuilder();
		sb.append("select id,tip,countryid from mlink_tip where 1=1 ");
		Map<String, Object> argsMap = new HashMap<String, Object>();
		if (StringUtils.isNotBlank((String) map.get("countryid"))) {
			sb.append(" and countryid=:countryid ");
			argsMap.put("countryid", map.get("countryid"));
		}
		List<Map<String, Object>> actors = this.namedJdbcTemplate.query(sb.toString(), argsMap,
				new RowMapper<Map<String, Object>>() {
					public Map<String, Object> mapRow(ResultSet rs, int rowNum) throws SQLException {
						// TODO Auto-generated method stub
						Map<String, Object> map = new HashMap<String, Object>();
						map.put("id", rs.getString("id"));
						map.put("tip", rs.getString("tip"));
						map.put("countryid", rs.getString("countryid"));
						return map;
					}
				});
		return actors;
	}

	public List<MlinkTip> getList(PageUtil<MlinkTip> page, Map<String, Object> map) throws Exception {
		// TODO Auto-generated method stub
		StringBuilder sb = new StringBuilder();
		StringBuilder countsb = new StringBuilder();
		Map<String, Object> searchMap = new HashMap<String, Object>();
		sb.append("select id,tip,countryid from mlink_tip where 1=1 ");
		countsb.append("select count(id) from mlink_tip where 1=1");

		Map<String, Object> countMap = new HashMap<String, Object>();
		if (StringUtils.isNotBlank((String) map.get("countryid"))) {
			sb.append("and countryid=:countryid ");
			searchMap.put("countryid", map.get("countryid"));
			countsb.append(" and  countryid=:countryid ");
			countMap.put("countryid", map.get("countryid"));
		}
		sb.append("limit :start,:end ");
		searchMap.put("start", page.getFirst() - 1);
		searchMap.put("end", page.getPageSize());

		List<MlinkTip> actors = this.namedJdbcTemplate.query(sb.toString(), searchMap, new RowMapper<MlinkTip>() {
			public MlinkTip mapRow(ResultSet rs, int rowNum) throws SQLException {
				// TODO Auto-generated method stub
				MlinkTip tip = new MlinkTip();
				tip.setId(rs.getString("id"));
				tip.setTip(rs.getString("tip"));
				tip.setCountryid(rs.getString("countryid"));
				return tip;
			}
		});
		Integer total = this.namedJdbcTemplate.queryForObject(countsb.toString(), countMap, Integer.class);
		page.setTotalCount(total);
		return actors;
	}

	public int delete(String id) throws Exception {
		// TODO Auto-generated method stub
		return jdbctemplate.update("delete from mlink_tip where id=?", id);
	}

	public int update(MlinkTip tip) throws Exception {
		// TODO Auto-generated method stub
		return jdbctemplate.update("update mlink_tip set tip=? where id=?", tip.getTip(), tip.getId());
	}

	public MlinkTip getTipByCountryId(String countryid) throws Exception {
		// TODO Auto-generated method stub
		MlinkTip single = this.jdbctemplate.queryForObject("select id,tip from mlink_tip where 1=1 and countryid=?",
				new Object[] { countryid }, new RowMapper<MlinkTip>() {
					public MlinkTip mapRow(ResultSet rs, int rowNum) throws SQLException {
						// TODO Auto-generated method stub
						MlinkTip actor = new MlinkTip();
						actor.setId(rs.getString("id"));
						actor.setTip(rs.getString("tip"));
						return actor;
					}
				});
		return single;
	}

}
