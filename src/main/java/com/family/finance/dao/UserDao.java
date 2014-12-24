package com.family.finance.dao;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.family.finance.model.DateDictionaryDO;
import com.family.finance.model.UserDO;
import com.family.finance.model.base.Syresource;

public interface UserDao {

	public void addUser(UserDO user) throws DataAccessException;

	public List<UserDO> queryAll() throws DataAccessException;

	public UserDO userLogin(UserDO user) throws DataAccessException;

	public List<DateDictionaryDO> queryDictionary(Integer parentId)
			throws DataAccessException;
	
	public List<UserDO> queryCombox() throws DataAccessException;
	
	public List<Syresource> getMainMenuTree() throws DataAccessException;;
	
	public List<UserDO> queryUser(Map<String, Object> params) throws DataAccessException;
	
	public Long queryUserCount(Map<String, Object> params) throws DataAccessException;
}
