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

import com.mlink.dao.ConsultDao;
import com.mlink.util.PageUtil;
import com.mlink.view.MlinkConsult;

@Repository
public class ConsultDaoImpl implements ConsultDao {

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

	public int save(MlinkConsult consult) throws Exception {
		// TODO Auto-generated method stub
		int test = this.jdbctemplate.update(
				"insert into mlink_consult (id,sex,name,phone,position,company,text,createtime) values (?,?,?,?,?,?,?,?)",
				consult.getId(), consult.getSex(), consult.getName(), consult.getPhone(), consult.getPosition(),
				consult.getCompany(), consult.getText(), consult.getCreatetime());
		return test;
	}

	public List<Map<String, Object>> getAllMapList(Map<String, Object> map) throws Exception {
		// TODO Auto-generated method stub
		StringBuilder sb = new StringBuilder();
		sb.append("select id,sex,name,phone,position,company,text,createtime from mlink_consult where 1=1 ");
		List<Map<String, Object>> actors = this.namedJdbcTemplate.query(sb.toString(), new HashMap<String, Object>(),
				new RowMapper<Map<String, Object>>() {
					public Map<String, Object> mapRow(ResultSet rs, int rowNum) throws SQLException {
						// TODO Auto-generated method stub
						Map<String, Object> map = new HashMap<String, Object>();
						map.put("id", rs.getString("id"));
						map.put("sex", rs.getInt("sex"));
						map.put("name", rs.getString("name"));
						map.put("phone", rs.getDouble("phone"));
						map.put("position", rs.getString("position"));
						map.put("company", rs.getString("company"));
						map.put("text", rs.getString("text"));
						map.put("createtime", rs.getTimestamp("createtime"));
						return map;
					}
				});
		return actors;
	}

	public List<MlinkConsult> getList(PageUtil<MlinkConsult> page, Map<String, Object> map) throws Exception {
		// TODO Auto-generated method stub
		StringBuilder sb = new StringBuilder();
		StringBuilder countsb = new StringBuilder();
		Map<String, Object> searchMap = new HashMap<String, Object>();
		Map<String, Object> countMap = new HashMap<String, Object>();
		sb.append("select id,sex,name,phone,position,company,text,createtime from mlink_consult where 1=1 ");
		countsb.append("select count(id) from mlink_consult where 1=1");
		sb.append(" order by createtime desc ");
		sb.append("limit :start,:end ");
		searchMap.put("start", page.getFirst() - 1);
		searchMap.put("end", page.getPageSize());

		List<MlinkConsult> actors = this.namedJdbcTemplate.query(sb.toString(), searchMap,
				new RowMapper<MlinkConsult>() {
					public MlinkConsult mapRow(ResultSet rs, int rowNum) throws SQLException {
						// TODO Auto-generated method stub
						MlinkConsult consult = new MlinkConsult();
						consult.setId(rs.getString("id"));
						consult.setSex(rs.getInt("sex"));
						consult.setName(rs.getString("name"));
						consult.setPhone(rs.getString("phone"));
						consult.setPosition(rs.getString("position"));
						consult.setCompany(rs.getString("company"));
						consult.setText(rs.getString("text"));
						consult.setCreatetime(rs.getTimestamp("createtime"));
						return consult;
					}
				});
		Integer total = this.namedJdbcTemplate.queryForObject(countsb.toString(), countMap, Integer.class);
		page.setTotalCount(total);
		return actors;
	}

	public int delete(String id) throws Exception {
		// TODO Auto-generated method stub
		return jdbctemplate.update("delete from mlink_consult where id=?", id);
	}

	public int update(MlinkConsult consult) throws Exception {
		// TODO Auto-generated method stub
		return jdbctemplate.update(
				"update mlink_consult set sex=?,name=?,phone=?,position=?,company=?,text=?,createtime=? where id=?",
				consult.getSex(), consult.getName(), consult.getPhone(), consult.getPosition(), consult.getCompany(),
				consult.getText(), consult.getCreatetime());
	}

}
