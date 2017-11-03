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

import com.mlink.dao.ExchangeDao;
import com.mlink.util.PageUtil;
import com.mlink.view.MlinkExchange;

@Repository
public class ExchangeDaoImpl implements ExchangeDao {

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

	public int save(MlinkExchange exchange) throws Exception {
		// TODO Auto-generated method stub
		int test = this.jdbctemplate.update(
				"insert into mlink_exchange (id,fromcurrency,tocurrency,exchange,createtime,updatetime) values (?,?,?,?,?,?)",
				exchange.getId(), exchange.getFromcurrency(), exchange.getTocurrency(), exchange.getExchange(),
				exchange.getCreatetime(),exchange.getUpdatetime());
		return test;
	}

	public MlinkExchange getLastExchange() throws Exception {
		// TODO Auto-generated method stub
		MlinkExchange exchange = this.jdbctemplate.queryForObject(
				"select id,fromcurrency,tocurrency,exchange,createtime from mlink_exchange where 1=1 order by createtime desc limit ?,?",
				new Object[] { 0, 1 }, new RowMapper<MlinkExchange>() {
					public MlinkExchange mapRow(ResultSet rs, int rowNum) throws SQLException {
						// TODO Auto-generated method stub
						MlinkExchange actor = new MlinkExchange();
						actor.setId(rs.getString("id"));
						actor.setFromcurrency(rs.getString("fromcurrency"));
						actor.setTocurrency(rs.getString("tocurrency"));
						actor.setExchange(rs.getDouble("exchange"));
						actor.setCreatetime(rs.getTimestamp("createtime"));
						return actor;
					}
				});
		return exchange;
	}

	public List<MlinkExchange> getList(PageUtil<MlinkExchange> page, Map<String, Object> map) throws Exception {
		// TODO Auto-generated method stub
		StringBuilder sb = new StringBuilder();
		StringBuilder countsb = new StringBuilder();
		Map<String, Object> searchMap = new HashMap<String, Object>();
		Map<String, Object> countMap = new HashMap<String, Object>();
		sb.append("select id,fromcurrency,tocurrency,exchange,createtime,updatetime from mlink_exchange where 1=1 ");
		countsb.append("select count(id) from mlink_exchange where 1=1");
		sb.append(" order by createtime desc ");
		sb.append("limit :start,:end ");
		searchMap.put("start", page.getFirst() - 1);
		searchMap.put("end", page.getPageSize());

		List<MlinkExchange> actors = this.namedJdbcTemplate.query(sb.toString(), searchMap,
				new RowMapper<MlinkExchange>() {
					public MlinkExchange mapRow(ResultSet rs, int rowNum) throws SQLException {
						// TODO Auto-generated method stub
						MlinkExchange exchange = new MlinkExchange();
						exchange.setId(rs.getString("id"));
						exchange.setFromcurrency(rs.getString("fromcurrency"));
						exchange.setTocurrency(rs.getString("fromcurrency"));
						exchange.setExchange(rs.getDouble("exchange"));
						exchange.setCreatetime(rs.getTimestamp("createtime"));
						exchange.setUpdatetime(rs.getTimestamp("updatetime"));
						return exchange;
					}
				});
		Integer total = this.namedJdbcTemplate.queryForObject(countsb.toString(), countMap, Integer.class);
		page.setTotalCount(total);
		return actors;
	}

}
