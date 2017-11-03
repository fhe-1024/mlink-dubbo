package com.mlink.view;

import java.io.Serializable;

public class MlinkInternational implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1176951363487890703L;
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

	@Override
	public String toString() {
		return "MlinkInternational [id=" + id + ", name=" + name + ", sort=" + sort + "]";
	}
	
}
