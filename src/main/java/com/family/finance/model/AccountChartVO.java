package com.family.finance.model;

import java.io.Serializable;

/**
 * 资金统计表的VO
 * @author suchao
 *
 */
public class AccountChartVO implements Serializable {

	private static final long serialVersionUID = 9999L;
	// 项目名称
	private String name;
	//是否选中
	private Boolean selected = false;
	//是否切片显示
	private Boolean sliced = false;
	//项目总金额
	private Long y;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Boolean getSelected() {
		return selected;
	}
	public void setSelected(Boolean selected) {
		this.selected = selected;
	}
	public Boolean getSliced() {
		return sliced;
	}
	public void setSliced(Boolean sliced) {
		this.sliced = sliced;
	}
	public Long getY() {
		return y;
	}
	public void setY(Long y) {
		this.y = y;
	}
}