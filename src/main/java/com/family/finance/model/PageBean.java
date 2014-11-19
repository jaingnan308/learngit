package com.family.finance.model;

import java.io.Serializable;

public class PageBean implements Serializable {

	private static final long serialVersionUID = 4L;
	private Integer start; // 第多少条数据
	private Integer limit; // 每页显示多少条

	public Integer getStart() {
		return start;
	}

	public void setStart(Integer start) {
		this.start = start;
	}

	public Integer getLimit() {
		return limit;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}

}
