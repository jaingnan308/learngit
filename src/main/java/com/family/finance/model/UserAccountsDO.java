package com.family.finance.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户资金流水表的DO
 * 
 * @author suchao
 * @version $Id: UserAccountsDO.java, v 0.1 2014-7-21 下午06:24:44 suchao Exp $
 */
public class UserAccountsDO implements Serializable {

	private static final long serialVersionUID = 2L;

	private Integer id;
	private Integer userId; // 用户ID
	private Integer direction;
	private Integer financeType;
	private Double money;
	private String remark;
	private String createAtStr;
	private Date createAt;
	private Integer createMan;
	private String updateAtStr;
	private Date updateAt;
	private Integer updateMan;
	private String financeTypeStr; // 资金类型名称

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getDirection() {
		return direction;
	}

	public void setDirection(Integer direction) {
		this.direction = direction;
	}

	public Integer getFinanceType() {
		return financeType;
	}

	public void setFinanceType(Integer financeType) {
		this.financeType = financeType;
	}

	public Double getMoney() {
		return money;
	}

	public void setMoney(Double money) {
		this.money = money;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	public Integer getCreateMan() {
		return createMan;
	}

	public void setCreateMan(Integer createMan) {
		this.createMan = createMan;
	}

	public Date getUpdateAt() {
		return updateAt;
	}

	public void setUpdateAt(Date updateAt) {
		this.updateAt = updateAt;
	}

	public Integer getUpdateMan() {
		return updateMan;
	}

	public void setUpdateMan(Integer updateMan) {
		this.updateMan = updateMan;
	}

	public String getCreateAtStr() {
		return createAtStr;
	}

	public void setCreateAtStr(String createAtStr) {
		this.createAtStr = createAtStr;
	}

	public String getUpdateAtStr() {
		return updateAtStr;
	}

	public void setUpdateAtStr(String updateAtStr) {
		this.updateAtStr = updateAtStr;
	}

	public String getFinanceTypeStr() {
		return financeTypeStr;
	}

	public void setFinanceTypeStr(String financeTypeStr) {
		this.financeTypeStr = financeTypeStr;
	}

}
