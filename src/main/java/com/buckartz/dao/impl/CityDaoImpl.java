package com.buckartz.dao.impl;

import com.buckartz.dao.CityDao;
import com.buckartz.model.map.City;
import com.buckartz.util.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class CityDaoImpl extends BaseDaoImpl<City> implements CityDao {
    @Override
    public City getCityByName(String name) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = HibernateUtil.getMyTransaction(session);
        Query query = session.createQuery("FROM City where name = :name");
        query.setString("name", name);
        query.setCacheable(true);
        City city = (City) query.uniqueResult();
        return city;
    }

    @Override
    public List<City> getAllCities() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = HibernateUtil.getMyTransaction(session);
        Query query = session.createQuery("FROM City");
        query.setCacheable(true);
        List<City> cityList = query.list();
        return cityList;
    }

    @Override
    public void add(String name, Integer x, Integer y) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = HibernateUtil.getMyTransaction(session);

        City city = new City();
        city.setName(name);
        city.setX(x);
        city.setY(y);

        session.save(city);
        transaction.commit();
    }
}
