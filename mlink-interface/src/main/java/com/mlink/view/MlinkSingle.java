package com.mlink.view;

import java.io.Serializable;

public class MlinkSingle implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6625948907676149377L;
	private String id;
	private String area;
	private String protocol;
	private String electricity;
	private String nodeid;
	private String authentication;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getProtocol() {
		return protocol;
	}

	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}

	public String getElectricity() {
		return electricity;
	}

	public void setElectricity(String electricity) {
		this.electricity = electricity;
	}

	public String getNodeid() {
		return nodeid;
	}

	public void setNodeid(String nodeid) {
		this.nodeid = nodeid;
	}

	public String getAuthentication() {
		return authentication;
	}

	public void setAuthentication(String authentication) {
		this.authentication = authentication;
	}

	@Override
	public String toString() {
		return "MlinkSingle [id=" + id + ", area=" + area + ", protocol=" + protocol + ", electricity=" + electricity
				+ ", nodeid=" + nodeid + ", authentication=" + authentication + "]";
	}

}
