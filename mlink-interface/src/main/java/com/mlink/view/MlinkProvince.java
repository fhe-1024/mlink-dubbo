package com.mlink.view;

import java.io.Serializable;

public class MlinkProvince implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5731860168646930727L;
	private String id;
	private String name;
	private int sort;

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

}
