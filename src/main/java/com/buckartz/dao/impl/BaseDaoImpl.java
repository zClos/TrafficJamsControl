package com.buckartz.dao.impl;

import com.buckartz.dao.BaseDao;
import com.buckartz.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.Serializable;

public class BaseDaoImpl<T> implements BaseDao<T> {
    @Override
    public void add(T t) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.getTransaction();
        session.save(t);
        transaction.commit();
    }

    @Override
    public T get(Class clazz, Serializable id) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = HibernateUtil.getMyTransaction(session);
        T t = (T) session.get(clazz, id);
        return t;
    }

    @Override
    public void update(T t) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.getTransaction();
        session.update(t);
        transaction.commit();
    }

    @Override
    public void delete(T t) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.getTransaction();
        session.delete(t);
        transaction.commit();
    }
}
