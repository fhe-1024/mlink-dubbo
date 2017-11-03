package com.mlink.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.mlink.dao.LineDao;
import com.mlink.util.PageUtil;
import com.mlink.view.MlinkLine;

@Repository
public class LineDaoImpl implements LineDao {

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

	public int save(MlinkLine line) throws Exception {
		// TODO Auto-generated method stub
		int test = this.jdbctemplate.update("insert into mlink_line (id,name,price,sort) values (?,?,?,?)",
				line.getId(), line.getName(), line.getPrice(), line.getSort());
		return test;
	}

	public List<Map<String, Object>> getAllMapList(Map<String, Object> map) throws Exception {
		// TODO Auto-generated method stub
		StringBuilder sb = new StringBuilder();
		sb.append("select id,name,price,sort from mlink_line where 1=1 ");
		sb.append(" order by sort ");
		List<Map<String, Object>> actors = this.namedJdbcTemplate.query(sb.toString(), new HashMap<String, Object>(),
				new RowMapper<Map<String, Object>>() {
					public Map<String, Object> mapRow(ResultSet rs, int rowNum) throws SQLException {
						// TODO Auto-generated method stub
						Map<String, Object> map = new HashMap<String, Object>();
						map.put("id", rs.getString("id"));
						map.put("name", rs.getString("name"));
						map.put("price", rs.getDouble("price"));
						map.put("sort", rs.getInt("sort"));
						return map;
					}
				});
		return actors;
	}

	public List<MlinkLine> getList(PageUtil<MlinkLine> page, Map<String, Object> map) throws Exception {
		// TODO Auto-generated method stub
		StringBuilder sb = new StringBuilder();
		StringBuilder countsb = new StringBuilder();
		Map<String, Object> searchMap = new HashMap<String, Object>();
		Map<String, Object> countMap = new HashMap<String, Object>();
		sb.append("select id,name,price,sort from mlink_line where 1=1 ");
		countsb.append("select count(id) from mlink_line where 1=1");
		sb.append(" order by sort ");
		sb.append("limit :start,:end ");
		searchMap.put("start", page.getFirst() - 1);
		searchMap.put("end", page.getPageSize());

		List<MlinkLine> actors = this.namedJdbcTemplate.query(sb.toString(), searchMap, new RowMapper<MlinkLine>() {
			public MlinkLine mapRow(ResultSet rs, int rowNum) throws SQLException {
				// TODO Auto-generated method stub
				MlinkLine node = new MlinkLine();
				node.setId(rs.getString("id"));
				node.setName(rs.getString("name"));
				node.setPrice(rs.getDouble("price"));
				node.setSort(rs.getInt("sort"));
				return node;
			}
		});
		Integer total = this.namedJdbcTemplate.queryForObject(countsb.toString(), countMap, Integer.class);
		page.setTotalCount(total);
		return actors;
	}

	public int delete(String id) throws Exception {
		// TODO Auto-generated method stub
		return jdbctemplate.update("delete from mlink_line where id=?", id);
	}

	public int update(MlinkLine line) throws Exception {
		// TODO Auto-generated method stub
		return jdbctemplate.update("update mlink_line set name=?,price=? ,sort=? where id=?", line.getName(),
				line.getPrice(), line.getSort(), line.getId());
	}

}
