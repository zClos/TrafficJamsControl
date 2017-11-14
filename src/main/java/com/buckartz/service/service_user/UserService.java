package com.buckartz.service.service_user;

import com.buckartz.dao.UserDao;
import com.buckartz.dao.impl.UserDaoImpl;
import com.buckartz.model.user.Admin;
import com.buckartz.model.user.Driver;
import com.buckartz.model.user.User;
;

public class UserService {

    private UserDao userDao = new UserDaoImpl();

    public boolean signUp(String name, String login, String password) {
        User checkUser = userDao.getByLogin(login);
        if (checkUser == null) {
            User newUser = new Driver();
            newUser.setName(name);
            newUser.setLogin(login);
            newUser.setPass(password);
            userDao.add(newUser);
            return true;
        } else {
            return false;
        }
    }

    public boolean addAdmin(String login, String password) {
        User checkUser = userDao.getByLogin(login);
        if (checkUser == null) {
            User newUser = new Admin();
            newUser.setLogin(login);
            newUser.setPass(password);
            userDao.add(newUser);
            return true;
        } else {
            return false;
        }
    }

    public User singIn(String login, String password) {
        User returnedUser = (User) userDao.getByLoginAndPassword(login, password);;
        if (returnedUser != null) {
            return returnedUser;
        } else {
            return null;
        }
    }

    public void updateData(User user) {
        userDao.update(user);
    }
}