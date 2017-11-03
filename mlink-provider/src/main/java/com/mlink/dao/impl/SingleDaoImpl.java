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

import com.mlink.dao.SingleDao;
import com.mlink.util.PageUtil;
import com.mlink.view.MlinkSingle;

@Repository
public class SingleDaoImpl implements SingleDao {
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

	public int save(MlinkSingle single) throws Exception {
		// TODO Auto-generated method stub
		int test = this.jdbctemplate.update(
				"insert into mlink_single (id,area,protocol,electricity,authentication,nodeid) values (?,?,?,?,?,?)",
				single.getId(), single.getArea(), single.getProtocol(), single.getElectricity(),
				single.getAuthentication(), single.getNodeid());
		log.info(test);
		return test;
	}

	public List<Map<String, Object>> getAllMapList(Map<String, Object> map) throws Exception {
		// TODO Auto-generated method stub
		StringBuilder sb = new StringBuilder();
		sb.append("select id,area,protocol,electricity,authentication,nodeid from mlink_single where 1=1 ");
		Map<String, Object> argsMap = new HashMap<String, Object>();
		if (StringUtils.isNotBlank((String) map.get("nodeid"))) {
			sb.append(" and nodeid=:nodeid ");
			argsMap.put("nodeid", map.get("nodeid"));
		}
		List<Map<String, Object>> actors = this.namedJdbcTemplate.query(sb.toString(), argsMap,
				new RowMapper<Map<String, Object>>() {
					public Map<String, Object> mapRow(ResultSet rs, int rowNum) throws SQLException {
						// TODO Auto-generated method stub
						Map<String, Object> map = new HashMap<String, Object>();
						map.put("id", rs.getString("id"));
						map.put("area", rs.getString("area"));
						map.put("protocol", rs.getString("protocol"));
						map.put("electricity", rs.getString("electricity"));
						map.put("authentication", rs.getString("authentication"));
						map.put("nodeid", rs.getString("nodeid"));
						return map;
					}
				});
		return actors;
	}

	public List<MlinkSingle> getList(PageUtil<MlinkSingle> page, Map<String, Object> map) throws Exception {
		// TODO Auto-generated method stub
		StringBuilder sb = new StringBuilder();
		StringBuilder countsb = new StringBuilder();
		Map<String, Object> searchMap = new HashMap<String, Object>();
		sb.append("select id,area,protocol,electricity,authentication,nodeid from mlink_single where 1=1 ");
		countsb.append("select count(id) from mlink_single where 1=1");

		Map<String, Object> countMap = new HashMap<String, Object>();
		if (StringUtils.isNotBlank((String) map.get("nodeid"))) {
			sb.append("and nodeid=:nodeid ");
			searchMap.put("nodeid", map.get("nodeid"));
			countsb.append(" and  nodeid=:nodeid ");
			countMap.put("nodeid", map.get("nodeid"));
		}
		sb.append("limit :start,:end ");
		searchMap.put("start", page.getFirst() - 1);
		searchMap.put("end", page.getPageSize());

		List<MlinkSingle> actors = this.namedJdbcTemplate.query(sb.toString(), searchMap, new RowMapper<MlinkSingle>() {
			public MlinkSingle mapRow(ResultSet rs, int rowNum) throws SQLException {
				// TODO Auto-generated method stub
				MlinkSingle single = new MlinkSingle();
				single.setId(rs.getString("id"));
				single.setArea(rs.getString("area"));
				single.setProtocol(rs.getString("protocol"));
				single.setElectricity(rs.getString("electricity"));
				single.setAuthentication(rs.getString("authentication"));
				single.setNodeid(rs.getString("nodeid"));
				return single;
			}
		});
		Integer total = this.namedJdbcTemplate.queryForObject(countsb.toString(), countMap, Integer.class);
		page.setTotalCount(total);
		return actors;
	}

	public int delete(String id) throws Exception {
		// TODO Auto-generated method stub
		return jdbctemplate.update("delete from mlink_single where id=?", id);
	}

	public int update(MlinkSingle single) throws Exception {
		// TODO Auto-generated method stub
		return jdbctemplate.update(
				"update mlink_single set area=?,protocol=?,electricity=?,authentication=? where id=?", single.getArea(),
				single.getProtocol(), single.getElectricity(), single.getAuthentication(), single.getId());
	}

	public MlinkSingle getSingleByNodeId(String nodeid) throws Exception {
		// TODO Auto-generated method stub
		MlinkSingle single = this.jdbctemplate.queryForObject(
				"select id,area,protocol,electricity,authentication from mlink_single where 1=1 and nodeid=?",
				new Object[] { nodeid }, new RowMapper<MlinkSingle>() {
					public MlinkSingle mapRow(ResultSet rs, int rowNum) throws SQLException {
						// TODO Auto-generated method stub
						MlinkSingle actor = new MlinkSingle();
						actor.setId(rs.getString("id"));
						actor.setArea(rs.getString("area"));
						actor.setProtocol(rs.getString("protocol"));
						actor.setElectricity(rs.getString("electricity"));
						actor.setAuthentication(rs.getString("authentication"));
						return actor;
					}
				});
		return single;
	}

}
