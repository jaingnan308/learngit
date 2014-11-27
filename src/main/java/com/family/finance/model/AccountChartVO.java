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
	private Double y = 0.0;
	//项目所占百分比
	private Double percentage;
	
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
	public Double getY() {
		return y;
	}
	public void setY(Double y) {
		this.y = y;
	}
	public Double getPercentage() {
		return percentage;
	}
	public void setPercentage(Double percentage) {
		this.percentage = percentage;
	}
	
}