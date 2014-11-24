package com.family.finance.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.family.finance.model.AccountChartVO;
import com.family.finance.model.PageBean;
import com.family.finance.model.UserAccountsDO;
import com.family.finance.service.UserAccountService;

@Controller
@RequestMapping(value = "/account")
public class UserAccountController {

	@Autowired
	@Qualifier("userAccountService")
	private UserAccountService userAccountService;

	@RequestMapping(value = "/account", method = RequestMethod.POST)
	public void addAccount(UserAccountsDO account, HttpServletResponse response)
			throws IOException {
		userAccountService.addAccount(account);
		response.setContentType("text/html;charset=UTF-8");
		//this is addition.
		response.getWriter().print("[{'success':'true','str':'账单提交成功'}]");
	}

	@ResponseBody
	@RequestMapping(value = "/finance")
	public String queryFinanceByCondition(UserAccountsDO account, Model model,
			PageBean pageBean) throws IOException {
		Map<String, Object> map = new HashMap<String, Object>();
		UserAccountsDO accountsDO = new UserAccountsDO();
		StringBuffer str = new StringBuffer();
		map.put("account", account);
		map.put("pageBean", pageBean);
		List<UserAccountsDO> strlist = userAccountService
				.queryFinanceByCondition(map);
		Integer count = userAccountService.queryFinanceCountByCondition(map);
		str.append("{'totalCount':" + "'" + count + "'," + "'list':[");
		for (Iterator it = strlist.iterator(); it.hasNext();) {
			accountsDO = (UserAccountsDO) it.next();
			str.append("{" + "'direction'" + ":" + "'"
					+ accountsDO.getDirection() + "',");
			str.append("'financeType'" + ":" + "'"
					+ accountsDO.getFinanceType() + "',");
			str.append("'money'" + ":" + "'" + accountsDO.getMoney() + "',");
			str.append("'createAtStr'" + ":" + "'"
					+ accountsDO.getCreateAtStr() + "',");
			str.append("'remark'" + ":" + "'" + accountsDO.getRemark() + "',");
			str.append("'userId'" + ":" + "'" + accountsDO.getUserId() + "'},");
		}
		str.deleteCharAt(str.length() - 1);
		str.append("]}");
		System.out.println(str.toString());
		return str.toString();
	}
	
	/**
	 * 
	 * @return
	 */
	
	@RequestMapping(value = "/chart",method = RequestMethod.POST)
	public @ResponseBody List<AccountChartVO> accountChart(){
		return userAccountService.queryAccountChart();
	}
}
