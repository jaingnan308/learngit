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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.family.finance.model.AccountChartVO;
import com.family.finance.model.PageBean;
import com.family.finance.model.UserAccountsDO;
import com.family.finance.model.easyui.Grid;
import com.family.finance.service.UserAccountService;
import com.family.utils.JsonUtil;

@Controller
@RequestMapping(value = "/account")
public class UserAccountController {

	@Autowired
	@Qualifier("userAccountService")
	private UserAccountService userAccountService;
	private Grid grid = new Grid();

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public void addAccount(UserAccountsDO account,@RequestParam Map<String, Object> params, HttpServletResponse response)
			throws IOException {
		System.out.println(params.toString());
		userAccountService.addAccount(account);
		response.setContentType("text/html;charset=UTF-8");
		//this is addition.
		response.getWriter().print("[{'success':'true','str':'账单提交成功'}]");
	}

	@RequestMapping(value = "/getAll")
	public @ResponseBody Grid queryFinanceByCondition(@RequestParam Map<String, Object> params) throws IOException {
		grid.setRows(userAccountService.queryFinanceByCondition(params));
		grid.setTotal(userAccountService.queryFinanceCountByCondition(params));
		return grid;
	}
	
	/**
	 * 资金统计饼状图
	 * @return
	 */
	
	@RequestMapping(value = "/chart",method = RequestMethod.POST)
	public @ResponseBody List<AccountChartVO> accountChart(){
		return userAccountService.queryAccountChart();
	}
}
