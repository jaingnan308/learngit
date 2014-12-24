package com.family.finance.service.impl;

import java.io.File;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;

import com.family.finance.controller.ExcelBaseAction;
import com.family.finance.dao.UserAccountDao;
import com.family.finance.model.AccountChartVO;
import com.family.finance.model.UserAccountsDO;
import com.family.finance.service.UserAccountService;
import com.family.utils.PageUtils;
import com.family.utils.excel.Config;
import com.family.utils.excel.ExcelWriteUtil;

@Service("userAccountService")
public class UserAccountServiceImpl implements UserAccountService {

	private UserAccountDao userAccountDao;

	@Override
	public Long addAccount(Map<String, Object> params) {
//		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
//		String date = account.getCreateAtStr();
		return userAccountDao.addAccount(params);
	}

	@Override
	public List<UserAccountsDO> queryFinanceByCondition(Map<String, Object> map) {
		PageUtils.setInitPagingBean(map);
		return userAccountDao.queryFinanceByCondition(map);
	}

	public UserAccountDao getUserAccountDao() {
		return userAccountDao;
	}

	public void setUserAccountDao(UserAccountDao userAccountDao) {
		this.userAccountDao = userAccountDao;
	}

	@Override
	public Long queryFinanceCountByCondition(Map<String, Object> map) {
		return userAccountDao.queryFinanceCountByCondition(map);
	}

	@Override
	public List<UserAccountsDO> queryFinanceChart(Map<String, Object> map) {
		return userAccountDao.queryFinanceChart(map);
	}

	@Override
	public List<AccountChartVO> queryAccountChart() {
		return userAccountDao.queryAccountChart();
	}

	@Override
	public void exportPaidOrderExcel(Map<String, Object> map,
			HttpServletRequest request, HttpServletResponse response) {
		List<UserAccountsDO> list = userAccountDao.queryFinanceByCondition(map);
        String path = map.get("realPath") + "资金账单" + "20141217"
                      + ".xlsx";
        Config config = new Config();
        config.setSourceTemplate(map.get("tempPath").toString());
        config.setTargetFile(path);
        config.setContent(list);//这里是要导出的数据集合
        config.setAutoSize(false);
        try {
            new ExcelWriteUtil(config).writeExcel();
        } catch (Exception e) {
            e.printStackTrace();
        }
        ExcelBaseAction.exportFile(response, request, new File(path), true);
	}
}
