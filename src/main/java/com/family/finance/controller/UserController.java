package com.family.finance.controller;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.family.finance.model.SessionInfo;
import com.family.finance.model.UserAccountsDO;
import com.family.finance.model.UserDO;
import com.family.finance.model.base.Syresource;
import com.family.finance.model.easyui.Grid;
import com.family.finance.model.easyui.Tree;
import com.family.finance.service.UserService;
import com.family.utils.BeanUtils;
import com.family.utils.ConfigUtil;
import com.family.utils.JsonUtil;
import com.family.utils.PageUtils;

/**
 * 用户资源的控制器
 * 
 * @author suchao
 * @version $Id: UserController.java, v 0.1 2014-7-18 下午02:44:57 suchao Exp $
 */
@Controller
@RequestMapping(value = "/UserController")
public class UserController {

	@Autowired
	@Qualifier("userService")
	private UserService userService;
	private Grid grid = new Grid();
	public final transient Log logger              = LogFactory.getLog(getClass());
	
    /**
	 * 用户注册
	 * 
	 * @param user
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/user", method = RequestMethod.POST)
	public String addUser(UserDO user, Model model) {
		userService.addUser(user);
		model.addAttribute("user", user);
		return null;
	}

	/**
	 * 用户登录
	 * @param user
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/userLogin", method = RequestMethod.POST)
	public String userLogin(UserDO user, Model model, HttpSession session,
			HttpServletRequest request) {
		request.getCookies();
		UserDO userDO = userService.userLogin(user);
		if (userDO != null) {
		    SessionInfo sessionInfo = new SessionInfo();
			model.addAttribute("user", user);
			sessionInfo.setUser(userDO);
			session.setAttribute(ConfigUtil.getSessionInfoName(), sessionInfo);
			return "index";
		} else {
			model.addAttribute("str", "success:true");
			return "login";
		}
	}
	
	/**
     * 用户查询
     * @param user
     * @param model
     * @return
     */
    @RequestMapping(value = "/queryUser", method = RequestMethod.POST)
    public void queryUser(@RequestParam Map<String, Object> params, HttpServletResponse response,
            HttpServletRequest request) {
        String result = "";
        try {
            grid.setRows(userService.queryUser(params));
            grid.setTotal(userService.queryUserCount(params));
            result = JsonUtil.writeObjectJson(grid);
            PageUtils.setSuccessResponse(response, result);
        } catch (Exception e){
            logger.error("用户查询出错，E", e);
        }
    }
	
	
	/**
     * 获得菜单树
     * @param user
     * @param model
     * @return
     */
    @RequestMapping(value = "/getMainMenu", method = RequestMethod.POST)
    public void getMainMenu(HttpSession session, HttpServletResponse response,
            HttpServletRequest request) {
        SessionInfo sessionInfo = (SessionInfo)session.getAttribute(ConfigUtil.getSessionInfoName());
        List<Syresource> resources = userService.getMainMenuTree();
        List<Tree> tree = new ArrayList<Tree>();
        for (Syresource resource : resources) {
            Tree node = new Tree();
            BeanUtils.copyNotNullProperties(resource, node);
            node.setText(resource.getName());
            Map<String, String> attributes = new HashMap<String, String>();
            attributes.put("url", resource.getUrl());
            attributes.put("target", resource.getTarget());
            node.setAttributes(attributes);
            tree.add(node);
        }
        String len = JsonUtil.writeNoPageJson(tree);
        String result = len.substring(8, len.length()-1);
        response.setContentType("text/html;charset=utf-8");
        try {
            response.getWriter().write(result);
            response.getWriter().flush();
            response.getWriter().close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

	@RequestMapping(value = "/finance", method = RequestMethod.POST)
	public String finance(UserAccountsDO us, Model model) {

		String s = new String("asd14|4458|hhhhss|ss558");
		String[] ss = s.split("\\|");
		for (int i = 0; i < ss.length; i++) {
			System.out.println(ss[i]);
		}
		System.out.println("账单资金统计");
		return "dsadfdsaf";
	}

	@RequestMapping(value = "/dictionary/{parentId}", method = RequestMethod.GET)
	public void queryDictionary(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		response.setContentType("text/html;charset=UTF-8");
		String str = URLDecoder.decode(request.getParameter("parentId"),
				"UTF-8");
		response.getWriter().print(
				URLDecoder.decode(
						userService.queryDictionary(Integer.valueOf(str)),
						"UTF-8"));
	}

}
