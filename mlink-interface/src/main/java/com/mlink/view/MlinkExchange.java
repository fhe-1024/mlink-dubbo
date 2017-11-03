package com.mlink.view;

import java.io.Serializable;
import java.sql.Timestamp;

public class MlinkExchange implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -76330550775260815L;
	private String id;
	private String fromcurrency;
	private String tocurrency;
	private double exchange;
	private Timestamp updatetime;
	private Timestamp createtime;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFromcurrency() {
		return fromcurrency;
	}

	public void setFromcurrency(String fromcurrency) {
		this.fromcurrency = fromcurrency;
	}

	public String getTocurrency() {
		return tocurrency;
	}

	public void setTocurrency(String tocurrency) {
		this.tocurrency = tocurrency;
	}

	public double getExchange() {
		return exchange;
	}

	public void setExchange(double exchange) {
		this.exchange = exchange;
	}

	public Timestamp getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Timestamp createtime) {
		this.createtime = createtime;
	}

	public Timestamp getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(Timestamp updatetime) {
		this.updatetime = updatetime;
	}

	@Override
	public String toString() {
		return "MlinkExchange [id=" + id + ", fromcurrency=" + fromcurrency + ", tocurrency=" + tocurrency
				+ ", exchange=" + exchange + ", createtime=" + createtime + "]";
	}

}
