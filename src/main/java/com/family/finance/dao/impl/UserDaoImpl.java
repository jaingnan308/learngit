package com.family.finance.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.family.finance.dao.UserDao;
import com.family.finance.model.DateDictionaryDO;
import com.family.finance.model.UserDO;
import com.family.finance.model.base.Syresource;

public class UserDaoImpl extends SqlMapClientDaoSupport implements UserDao {

	@Override
	public void addUser(UserDO user) throws DataAccessException {
		super.getSqlMapClientTemplate().insert("User.addUser", user);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserDO> queryAll() throws DataAccessException {
		return super.getSqlMapClientTemplate().queryForList("User.queryAll");
	}

	@Override
	public UserDO userLogin(UserDO user) throws DataAccessException {
		return (UserDO) super.getSqlMapClientTemplate().queryForObject(
				"User.userLogin", user);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DateDictionaryDO> queryDictionary(Integer parentId)
			throws DataAccessException {
		return super.getSqlMapClientTemplate().queryForList(
				"User.queryDictionary", parentId);
	}

	@SuppressWarnings("unchecked")
    @Override
    public List<Syresource> getMainMenuTree() throws DataAccessException {
        return super.getSqlMapClientTemplate().queryForList(
            "User.getMainMenuTree");
    }

	@SuppressWarnings("unchecked")
    @Override
    public List<UserDO> queryUser(Map<String, Object> params) throws DataAccessException {
        return super.getSqlMapClientTemplate().queryForList(
                "User.queryUserField",params);
    }

    @Override
    public Long queryUserCount(Map<String, Object> params) throws DataAccessException {
        return (Long) super.getSqlMapClientTemplate().queryForObject(
            "User.queryUserCount",params);
    }
}
