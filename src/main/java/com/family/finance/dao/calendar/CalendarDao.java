package com.family.finance.dao.calendar;

import java.util.List;

import com.family.finance.model.Calendar;

/**
 * 日程DAO
 * @author suchao
 *
 */
public interface CalendarDao {
	
	public Integer add(Calendar calendar);
	
	public List<Calendar> getAll(Integer userId);
	
	public Calendar getById(Long id);
	
	public Long delete(Long id);
}
