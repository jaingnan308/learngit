package com.family.finance.model;

/**
 * sessionInfo模型，只要登录成功，就需要设置到session里面，便于系统使用
 * 
 * @author suchao
 * 
 */
public class SessionInfo implements java.io.Serializable {

    private UserDO user;


    public UserDO getUser() {
        return user;
    }


    public void setUser(UserDO user) {
        this.user = user;
    }


    @Override
    public String toString() {
        return user.getName();
    }

}

