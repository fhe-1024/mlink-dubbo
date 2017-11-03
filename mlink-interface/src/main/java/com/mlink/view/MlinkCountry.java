package com.mlink.view;

import java.io.Serializable;

public class MlinkCountry implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2289667644793409861L;
	private String id;
	private String name;
	private int sort;
	private String internationalid;

	private int flag;

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

	public String getInternationalid() {
		return internationalid;
	}

	public void setInternationalid(String internationalid) {
		this.internationalid = internationalid;
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	@Override
	public String toString() {
		return "MlinkCountry [id=" + id + ", name=" + name + ", sort=" + sort + ", internationalid=" + internationalid
				+ ", flag=" + flag + "]";
	}

}
