package com.buckartz.dao;

import com.buckartz.model.user.User;

public interface UserDao extends BaseDao<User> {

    User getByLoginAndPassword(String login, String password);

    User getByLogin(String login);

}
