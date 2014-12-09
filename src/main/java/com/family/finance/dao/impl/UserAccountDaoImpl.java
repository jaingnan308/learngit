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
	public Long addAccount(Map<String, Object> params) {
		return (Long) super.getSqlMapClientTemplate().insert("UserAccount.addAccount",
				params);
	}

	@Override
	public List<UserAccountsDO> queryFinanceByCondition(Map<String, Object> map) {
		return super.getSqlMapClientTemplate().queryForList(
				"UserAccount.queryFinanceByCondition", map);
	}

	@Override
	public Long queryFinanceCountByCondition(Map<String, Object> map) {
		return (Long) super.getSqlMapClientTemplate().queryForObject(
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
