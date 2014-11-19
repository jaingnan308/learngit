package com.family.finance.dao;

import java.util.List;
import java.util.Map;

import com.family.finance.model.UserAccountsDO;

public interface UserAccountDao {

	public void addAccount(UserAccountsDO account);

	public List<UserAccountsDO> queryFinanceByCondition(Map<String, Object> map);

	public int queryFinanceCountByCondition(Map<String, Object> map);

	public List<UserAccountsDO> queryFinanceChart(Map<String, Object> map);

}
