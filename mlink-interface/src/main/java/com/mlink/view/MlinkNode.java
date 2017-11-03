package com.mlink.view;

import java.io.Serializable;

public class MlinkNode implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7844351541341062720L;
	private String id;
	private String name;
	private int sort;
	private String area;
	private String protocol;
	private String electricity;
	private String authentication;
	private String cityid;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSort() {
		return sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
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

	public String getAuthentication() {
		return authentication;
	}

	public void setAuthentication(String authentication) {
		this.authentication = authentication;
	}

	public String getCityid() {
		return cityid;
	}

	public void setCityid(String cityid) {
		this.cityid = cityid;
	}

	@Override
	public String toString() {
		return "MlinkNode [id=" + id + ", name=" + name + ", sort=" + sort + ", area=" + area + ", protocol=" + protocol
				+ ", electricity=" + electricity + ", authentication=" + authentication + ", cityid=" + cityid + "]";
	}

}
