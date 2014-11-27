package com.family.finance.model.easyui;

import java.util.ArrayList;
import java.util.List;

/**
 * EasyUI DataGrid模型
 * 
 * @author 孙宇
 * 
 */
public class Grid implements java.io.Serializable {
	
	private static final long serialVersionUID = 9999L;
	//查询返回记录的总条数
	private Long total = 0L;
	//查询出来的record
	private List rows = new ArrayList();



    public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

	public List getRows() {
		return rows;
	}

	public void setRows(List rows) {
		this.rows = rows;
	}

}
