package com.family.finance.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.family.finance.dao.UserAccountDao;
import com.family.finance.model.AccountChartVO;
import com.family.finance.model.UserAccountsDO;

public class UserAccountDaoImpl extends SqlMapClientDaoSupport implements
		UserAccountDao {

	@Override
	public void addAccount(UserAccountsDO account) {
		super.getSqlMapClientTemplate().insert("UserAccount.addAccount",
				account);
	}

	@Override
	public List<UserAccountsDO> queryFinanceByCondition(Map<String, Object> map) {
		return super.getSqlMapClientTemplate().queryForList(
				"UserAccount.queryFinanceByCondition", map);
	}

	@Override
	public int queryFinanceCountByCondition(Map<String, Object> map) {
		return (Integer) super.getSqlMapClientTemplate().queryForObject(
				"UserAccount.queryFinanceCountByCondition", map);
	}

	@Override
	public List<UserAccountsDO> queryFinanceChart(Map<String, Object> map) {
		return super.getSqlMapClientTemplate().queryForList(
				"UserAccount.queryFinanceChart", map);
	}

	@Override
	public List<AccountChartVO> queryAccountChart() {
		return super.getSqlMapClientTemplate().queryForList(
				"UserAccount.queryAccountChart");
	}

}
