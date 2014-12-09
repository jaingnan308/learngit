package com.family.finance.dao.impl.calendar;

import java.util.Date;
import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.family.finance.dao.calendar.CalendarDao;
import com.family.finance.model.Calendar;

public class CalendarDaoImpl extends SqlMapClientDaoSupport implements CalendarDao{

	@Override
	public Integer add(Calendar calendar) {
		return (Integer) super.getSqlMapClientTemplate().insert("Calendar.add", calendar);
	}

	@Override
	public List<Calendar> getAll(Integer userId) {
		return super.getSqlMapClientTemplate().queryForList("Calendar.getAll",userId);
	}

	@Override
	public Calendar getById(Long id) {
		return (Calendar) super.getSqlMapClientTemplate().queryForObject("Calendar.getById",id);
	}

	@Override
	public Long delete(Long id) {
		return (long) super.getSqlMapClientTemplate().delete("Calendar.delete",id);
	}

	@Override
	public List<Calendar> getRemindByThisTime() {
		return super.getSqlMapClientTemplate().queryForList("Calendar.getRemindByThisTime");
	}
}
