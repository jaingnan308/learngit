package com.family.finance.service.calendar;

import java.util.List;

import com.family.finance.model.Calendar;

/**
 * 日程
 * @author suchao
 *
 */
public interface CalendarService {

	/**
	 * 添加日程
	 * @param calendar
	 */
	public Integer add(Calendar calendar);
	
	/**
	 * 查询我所有的日程
	 * @return
	 */
	public List<Calendar> getAll(Integer userId);
	
	
	/**
	 * 根据ID查询我的日程
	 * @return
	 */
	public Calendar getById(Long id);
	
	/**
	 * 根据id删除一个日程
	 * @param id
	 * @return
	 */
	public Long delete(Long id);
	
}
