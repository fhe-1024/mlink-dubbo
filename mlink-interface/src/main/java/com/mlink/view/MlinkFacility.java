package com.mlink.view;

import java.io.Serializable;

public class MlinkFacility implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8203692300311513401L;
	private String id;
	private String name;
	private String standard;
	private String power;
	private String price;
	private int type;
	private int sort;
	private String countryid;

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

	public String getStandard() {
		return standard;
	}

	public void setStandard(String standard) {
		this.standard = standard;
	}

	public String getPower() {
		return power;
	}

	public void setPower(String power) {
		this.power = power;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getSort() {
		return sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}

	public String getCountryid() {
		return countryid;
	}

	public void setCountryid(String countryid) {
		this.countryid = countryid;
	}

	@Override
	public String toString() {
		return "MlinkFacility [id=" + id + ", name=" + name + ", standard=" + standard + ", power=" + power + ", price="
				+ price + ", type=" + type + ", sort=" + sort + ", countryid=" + countryid + "]";
	}

}
