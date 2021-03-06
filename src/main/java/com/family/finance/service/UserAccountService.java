package com.family.finance.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.family.finance.model.AccountChartVO;
import com.family.finance.model.UserAccountsDO;

public interface UserAccountService {

	public Long addAccount(Map<String, Object> params);

	public List<UserAccountsDO> queryFinanceByCondition(Map<String, Object> map);

	public Long queryFinanceCountByCondition(Map<String, Object> map);

	public List<UserAccountsDO> queryFinanceChart(Map<String, Object> map);
	
	public List<AccountChartVO> queryAccountChart();
	
	public void exportPaidOrderExcel(Map<String, Object> map, HttpServletRequest request,
            HttpServletResponse response);
}
