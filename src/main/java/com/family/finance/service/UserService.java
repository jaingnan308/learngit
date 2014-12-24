package com.family.finance.service;

import java.util.List;
import java.util.Map;

import com.family.finance.model.DateDictionaryDO;
import com.family.finance.model.UserDO;
import com.family.finance.model.base.Syresource;

public interface UserService {

	public void addUser(UserDO user);

	public List<UserDO> queryAll();
	
	public List<UserDO> queryUser(Map<String, Object> params);
	
	public Long queryUserCount(Map<String, Object> params);
	
	public UserDO userLogin(UserDO user);

	public List<DateDictionaryDO> queryDictionary(Integer parentId);
	
	public List<UserDO> queryCombox();
	
	public List<Syresource> getMainMenuTree();

}
