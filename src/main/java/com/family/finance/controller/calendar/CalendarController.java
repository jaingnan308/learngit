package com.family.finance.controller.calendar;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.family.finance.model.Calendar;
import com.family.finance.model.UserDO;
import com.family.finance.service.calendar.CalendarService;
import com.family.utils.PageUtils;

/**
 * 日程的Controller
 * @author suchao
 *
 */
@Controller
@RequestMapping(value = "calendar")
public class CalendarController {
	
	@Autowired
	@Qualifier("calendarService")
	private CalendarService calendarService;
	
	/**
	 * 添加一个日程
	 * @param calendar
	 * @param response
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public void add(@ModelAttribute("calendar") Calendar calendar, HttpServletResponse response) {
		System.out.println(calendar.toString());
        try {
        	calendar.setUserId(1L);
        	calendar.setEnd(calendar.getStart());
        	int result = calendarService.add(calendar);
			PageUtils.setSuccessResponse(response, String.valueOf(result));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 查看所有日程
	 * @param session
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/getAll", method = RequestMethod.POST)
	public @ResponseBody List<Calendar> getAll(HttpSession session, HttpServletResponse response) {
		UserDO user = null;
		List<Calendar> result = null;
        try {
        	Object obj = session.getAttribute("sessionInfo");
        	if(obj != null){
        		user = (UserDO)obj;
        	}
        	result = calendarService.getAll(user.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
        return result;
	}
	
	/**
	 * 根据ID查看日程
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/view", method = RequestMethod.POST)
	public @ResponseBody Calendar view(Long id) {
		Calendar result = null;
		try{
			result = calendarService.getById(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
        return result;
	}
	
	
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody Long delete(Long id) {
		Long result = null;
		try{
			result = calendarService.delete(id);
		} catch (Exception e) {
			return result;
		}
        return result;
	}

}
