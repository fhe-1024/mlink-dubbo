package com.mlink.util;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang.StringUtils;

public class PageUtil<T> implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6041141029563323318L;
	public static final String ASC = "asc";
	public static final String DESC = "desc";

	protected int pageNo = 1;
	protected int pageSize = 1;

	protected String orderBy = null;
	protected String order = null;

	protected boolean autoCount = true;

	protected List<T> result = Collections.emptyList();
	protected long totalCount = -1L;

	public PageUtil() {
	}

	public PageUtil(int pageSize) {
		this.setPageSize(pageSize);
	}

	public PageUtil(int pageSize, boolean autoCount) {
		this.setPageSize(pageSize);
		this.setAutoCount(autoCount);
	}

	public int getPageNo() {
		return this.pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
		if (pageNo < 1) {
			this.pageNo = 1;
		}

	}

	public int getPageSize() {
		return this.pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
		if (pageSize < 0) {
			this.pageSize = 1;
		}
	}

	public int getFirst() {
		return (this.pageNo - 1) * this.pageSize + 1;
	}

	public String getOrderBy() {
		return this.orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	public boolean isOrderBySetted() {
		return StringUtils.isNotBlank(this.orderBy) && StringUtils.isNotBlank(this.order);
	}

	public String getOrder() {
		return this.order;
	}

	public void setOrder(String order) {
		String[] orders = StringUtils.split(StringUtils.lowerCase(order), ',');
		String[] var6 = orders;
		int var5 = orders.length;
		for (int var4 = 0; var4 < var5; ++var4) {
			String orderStr = var6[var4];
			if (!StringUtils.equals("desc", orderStr) && !StringUtils.equals("asc", orderStr)) {
				throw new IllegalArgumentException("排序方向	;" + orderStr + "不是合法值;");
			}
		}
		this.order = StringUtils.lowerCase(order);
	}

	public boolean isAutoCount() {
		return autoCount;
	}

	public void setAutoCount(boolean autoCount) {
		this.autoCount = autoCount;
	}

	public List<T> getResult() {
		return result;
	}

	public void setResult(List<T> result) {
		this.result = result;
	}

	public long getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(long totalCount) {
		this.totalCount = totalCount;
	}

	public long getTotalPages() {
		if (this.totalCount < 0L) {
			return -1L;
		} else {
			long count = this.totalCount / (long) this.pageSize;
			if (this.totalCount % (long) this.pageSize > 0L) {
				++count;
			}
			return count;
		}
	}

	public boolean isHasNext() {
		return (long) (this.pageNo + 1) <= this.getTotalPages();
	}

	public boolean isHasPre() {
		return this.pageNo - 1 >= 1;
	}

	public int getPrePage() {
		return this.isHasPre() ? this.pageNo - 1 : this.pageNo;
	}

	@Override
	public String toString() {
		return "PageUtil [pageNo=" + pageNo + ", pageSize=" + pageSize + ", orderBy=" + orderBy + ", order=" + order
				+ ", autoCount=" + autoCount + ", result=" + result + ", totalCount=" + totalCount + "]";
	}

}
