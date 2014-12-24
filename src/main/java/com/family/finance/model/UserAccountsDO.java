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
	private String userName; //账单归属人真实姓名
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
	private String financeTypeName; // 资金类型名称
	private String imageSrc; // 账单图片路径

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

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
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

	public String getFinanceTypeName() {
		return financeTypeName;
	}

	public void setFinanceTypeName(String financeTypeName) {
		this.financeTypeName = financeTypeName;
	}

	public String getImageSrc() {
		return imageSrc;
	}

	public void setImageSrc(String imageSrc) {
		this.imageSrc = imageSrc;
	}

}
