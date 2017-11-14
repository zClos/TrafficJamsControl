package com.buckartz.dao.impl;

import com.buckartz.dao.UserDao;
import com.buckartz.model.user.User;
import com.buckartz.util.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;


public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao {

    @Override
    public User getByLoginAndPassword(String login, String password) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = HibernateUtil.getMyTransaction(session);
        Query query = session.createQuery("FROM User WHERE login = :login AND pass = :pass");
        query.setString("login", login);
        query.setString("pass", password);
        query.setCacheable(true);
        User user = (User) query.uniqueResult();
        return user;
    }

    @Override
    public User getByLogin(String login) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = HibernateUtil.getMyTransaction(session);
        Query query = session.createQuery("FROM User WHERE login = :login");
        query.setString("login", login);
        query.setCacheable(true);
        User user = (User) query.uniqueResult();
        return user;
    }
}
