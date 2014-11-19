package com.family.finance.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import com.family.finance.dao.UserAccountDao;
import com.family.finance.model.UserAccountsDO;
import com.family.finance.service.UserAccountService;

public class UserAccountServiceImpl implements UserAccountService {

	private UserAccountDao userAccountDao;

	@Override
	public void addAccount(UserAccountsDO account) {
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		String date = account.getCreateAtStr();
		try {
			account.setCreateAt(sf.parse(date));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		userAccountDao.addAccount(account);
	}

	@Override
	public List<UserAccountsDO> queryFinanceByCondition(Map<String, Object> map) {
		// UserAccountsDO accountDo = new UserAccountsDO();
		// StringBuffer json = new StringBuffer("[");
		List<UserAccountsDO> list = userAccountDao.queryFinanceByCondition(map);
		// for (Iterator it = list.iterator(); it.hasNext();) {
		// accountDo = (UserAccountsDO) it.next();
		// json.append("{\'userId\':\'" + accountDo.getUserId() +
		// "\',\'direction\':\'"
		// + accountDo.getDirection() + "\',\'financeType\':\'"
		// + accountDo.getFinanceType() + "\',\'money\':\'" +
		// accountDo.getMoney()
		// + "\',\'remark\':\'" + accountDo.getRemark() + "\'},");
		// }
		// json.deleteCharAt(json.length() - 1);
		// json.append("]");
		// System.out.println(json);
		return list;
	}

	public UserAccountDao getUserAccountDao() {
		return userAccountDao;
	}

	public void setUserAccountDao(UserAccountDao userAccountDao) {
		this.userAccountDao = userAccountDao;
	}

	@Override
	public int queryFinanceCountByCondition(Map<String, Object> map) {
		return userAccountDao.queryFinanceCountByCondition(map);
	}

	@Override
	public List<UserAccountsDO> queryFinanceChart(Map<String, Object> map) {
		return userAccountDao.queryFinanceChart(map);
	}
}
