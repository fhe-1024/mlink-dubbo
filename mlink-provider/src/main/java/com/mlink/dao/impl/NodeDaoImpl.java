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

import com.mlink.dao.NodeDao;
import com.mlink.util.PageUtil;
import com.mlink.view.MlinkNode;


@Repository
public class NodeDaoImpl implements NodeDao {

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

	public int save(MlinkNode node) throws Exception {
		// TODO Auto-generated method stub
		int test = this.jdbctemplate.update(
				"insert into mlink_node (id,name,sort,area,protocol,electricity,authentication,cityid) values (?,?,?,?,?,?,?,?)",
				node.getId(), node.getName(), node.getSort(), node.getArea(), node.getProtocol(), node.getElectricity(),
				node.getAuthentication(), node.getCityid());
		log.info(test);
		return test;
	}

	public List<Map<String, Object>> getAllMapList(Map<String, Object> map) throws Exception {
		// TODO Auto-generated method stub
		StringBuilder sb = new StringBuilder();
		sb.append("select id,name,sort,area,protocol,electricity,authentication,cityid from mlink_node where 1=1 ");
		Map<String, Object> argsMap = new HashMap<String, Object>();
		if (StringUtils.isNotBlank((String) map.get("cityid"))) {
			sb.append(" and cityid=:cityid ");
			argsMap.put("cityid", map.get("cityid"));
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
						map.put("area", rs.getString("area"));
						map.put("protocol", rs.getString("protocol"));
						map.put("electricity", rs.getString("electricity"));
						map.put("authentication", rs.getString("authentication"));
						map.put("cityid", rs.getString("cityid"));
						return map;
					}
				});
		return actors;
	}

	public List<MlinkNode> getList(PageUtil<MlinkNode> page, Map<String, Object> map) throws Exception {
		// TODO Auto-generated method stub
		StringBuilder sb = new StringBuilder();
		StringBuilder countsb = new StringBuilder();
		Map<String, Object> searchMap = new HashMap<String, Object>();
		Map<String, Object> countMap = new HashMap<String, Object>();
		sb.append("select id,name,sort,area,protocol,electricity,authentication from mlink_node where 1=1 ");
		countsb.append("select count(id) from mlink_node where 1=1");
		
		if (StringUtils.isNotBlank((String) map.get("cityid"))) {
			sb.append("and cityid=:cityid ");
			searchMap.put("cityid", map.get("cityid"));
			countsb.append(" and  cityid=:cityid ");
			countMap.put("cityid", map.get("cityid"));
		}
		sb.append(" order by sort ");
		sb.append("limit :start,:end ");
		searchMap.put("start", page.getFirst()-1);
		searchMap.put("end", page.getPageSize());
		
		List<MlinkNode> actors = this.namedJdbcTemplate.query(sb.toString(), searchMap, new RowMapper<MlinkNode>() {
			public MlinkNode mapRow(ResultSet rs, int rowNum) throws SQLException {
				// TODO Auto-generated method stub
				MlinkNode node = new MlinkNode();
				node.setId(rs.getString("id"));
				node.setName(rs.getString("name"));
				node.setSort(rs.getInt("sort"));
				node.setArea(rs.getString("area"));
				node.setProtocol(rs.getString("protocol"));
				node.setElectricity(rs.getString("electricity"));
				node.setAuthentication(rs.getString("authentication"));
				return node;
			}
		});
		Integer total = this.namedJdbcTemplate.queryForObject(countsb.toString(), countMap,
				Integer.class);
		page.setTotalCount(total);
		return actors;
	}

	public int delete(String id) throws Exception {
		// TODO Auto-generated method stub
		return jdbctemplate.update("delete from mlink_node where id=?", id);
	}

	public int update(MlinkNode node) throws Exception {
		// TODO Auto-generated method stub
		return jdbctemplate.update("update mlink_node set name=? ,sort=? where id=?", node.getName(), node.getSort(),
				node.getId());
	}

}
