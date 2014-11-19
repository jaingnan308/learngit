package com.family.finance.service.impl;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.family.finance.dao.UserDao;
import com.family.finance.model.DateDictionaryDO;
import com.family.finance.model.PageBean;
import com.family.finance.model.UserDO;
import com.family.finance.model.base.Syresource;
import com.family.finance.service.UserService;
import com.family.utils.PageUtils;

@Service("userService")
public class UserServiceImpl implements UserService {

	private UserDao userDao;
	
	public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

	@Override
	public void addUser(UserDO user) {
		userDao.addUser(user);
	}

	@Override
	public List<UserDO> queryAll() {
		return userDao.queryAll();
	}

	@Override
	public UserDO userLogin(UserDO user) {
		return userDao.userLogin(user);
	}

	@Override
	public String queryDictionary(Integer parentId) {
		DateDictionaryDO dictionary = new DateDictionaryDO();
		StringBuffer json = new StringBuffer("[");
		List<DateDictionaryDO> list = userDao.queryDictionary(parentId);
		// json.append("success:true,root:[");
		for (Iterator it = list.iterator(); it.hasNext();) {
			dictionary = (DateDictionaryDO) it.next();
			json.append("{\'key\':" + "\'" + dictionary.getKey() + "\'" + ","
					+ "\'value\':" + "\'" + dictionary.getValue() + "\'" + "},");
		}
		json.deleteCharAt(json.length() - 1);
		json.append("]");
		return json.toString();
	}
	
    @Override
    public List<Syresource> getMainMenuTree() {
        return userDao.getMainMenuTree();
    }

    @Override
    public List<UserDO> queryUser(Map<String, Object> params) {
        PageUtils.setInitPagingBean(params);
        return userDao.queryUser(params);
    }

    @Override
    public Integer queryUserCount(Map<String, Object> params) {
        return userDao.queryUserCount(params);
    }

}
