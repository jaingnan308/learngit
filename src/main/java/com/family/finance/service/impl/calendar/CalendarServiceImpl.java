package com.family.finance.service.impl.calendar;

import java.util.List;

import org.springframework.stereotype.Service;

import com.family.finance.dao.calendar.CalendarDao;
import com.family.finance.model.Calendar;
import com.family.finance.service.calendar.CalendarService;

@Service("calendarService")
public class CalendarServiceImpl implements CalendarService{
	
	private CalendarDao calendarDao;
	
	public CalendarDao getCalendarDao() {
		return calendarDao;
	}

	public void setCalendarDao(CalendarDao calendarDao) {
		this.calendarDao = calendarDao;
	}

	@Override
	public Integer add(Calendar calendar) {
		return calendarDao.add(calendar);
	}

	@Override
	public List<Calendar> getAll(Integer userId) {
		return calendarDao.getAll(userId);
	}

	@Override
	public Calendar getById(Long id) {
		return calendarDao.getById(id);
	}

	@Override
	public Long delete(Long id) {
		return calendarDao.delete(id);
	}

}
