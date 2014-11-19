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

import com.family.finance.model.PageBean;
import com.family.finance.model.UserAccountsDO;
import com.family.finance.service.UserAccountService;

@Controller
@RequestMapping(value = "/UserAccountController")
public class UserAccountController {

	@Autowired
	@Qualifier("userAccountService")
	private UserAccountService userAccountService;

	@RequestMapping(value = "/account", method = RequestMethod.POST)
	public void addAccount(UserAccountsDO account, HttpServletResponse response)
			throws IOException {
		userAccountService.addAccount(account);
		response.setContentType("text/html;charset=UTF-8");
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

	/*@RequestMapping(value = "/financeChart")
	public String queryFinanceChart(UserAccountsDO account,
			HttpServletResponse response, HttpServletRequest request)
			throws IOException {
		DefaultPieDataset dpd = new DefaultPieDataset(); // 建立一个默认的饼图
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("account", account);
		List<UserAccountsDO> strlist = userAccountService
				.queryFinanceChart(map);
		for (Iterator it = strlist.iterator(); it.hasNext();) {
			UserAccountsDO userAccount = (UserAccountsDO) it.next();
			dpd.setValue(userAccount.getFinanceTypeStr(),
					userAccount.getMoney());
		}

		// dpd.setValue("管理人员", 25); //输入数据
		// dpd.setValue("市场人员", 25);
		// dpd.setValue("开发人员", 45);
		// dpd.setValue("其他人员", 10);

		JFreeChart chart = ChartFactory.createPieChart("某公司人员组织数据图", dpd, true,
				true, false);
		// 可以查具体的API文档,第一个参数是标题，第二个参数是一个数据集，第三个参数表示是否显示Legend，第四个参数表示是否显示提示，第五个参数表示图中是否存在URL

		ChartFrame chartFrame = new ChartFrame("某公司人员组织数据图", chart);
		// chart要放在Java容器组件中，ChartFrame继承自java的Jframe类。该第一个参数的数据是放在窗口左上角的，不是正中间的标题。
		chartFrame.pack(); // 以合适的大小展现图形
		chartFrame.setVisible(true);// 图形是否可见 true 为可见

		FileOutputStream fos_jpg = null;
		try {
			fos_jpg = new FileOutputStream("D:\\33333.jpg");
			ChartUtilities.writeChartAsJPEG(fos_jpg, 0, chart, 600, 500, null);
		} finally {
			try {
				fos_jpg.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		// "D:\\eclipse3.6\\eclipse\\workspace\\MyFamily\\src\\main\\wabapp\\image\\chart\\33333.jpg");

		System.out
				.println("我进来过了————————————————————————————————————————————————————————");
		return null;
	}

	private static CategoryDataset getDataSet() {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		dataset.addValue(100, "北京", "苹果");
		dataset.addValue(100, "上海", "苹果");
		dataset.addValue(100, "广州", "苹果");
		dataset.addValue(200, "北京", "梨子");
		dataset.addValue(200, "上海", "梨子");
		dataset.addValue(200, "广州", "梨子");
		dataset.addValue(300, "北京", "葡萄");
		dataset.addValue(300, "上海", "葡萄");
		dataset.addValue(300, "广州", "葡萄");
		dataset.addValue(400, "北京", "香蕉");
		dataset.addValue(400, "上海", "香蕉");
		dataset.addValue(400, "广州", "香蕉");
		dataset.addValue(500, "北京", "荔枝");
		dataset.addValue(500, "上海", "荔枝");
		dataset.addValue(500, "广州", "荔枝");
		return dataset;
	}*/
}
