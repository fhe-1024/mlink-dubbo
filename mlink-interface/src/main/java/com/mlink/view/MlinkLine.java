package com.mlink.view;

import java.io.Serializable;

public class MlinkLine implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6196602462072192285L;
	private String id;
	private String name;
	private double price;
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

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getSort() {
		return sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}

	@Override
	public String toString() {
		return "MlinkLine [id=" + id + ", name=" + name + ", price=" + price + ", sort=" + sort + "]";
	}

}
