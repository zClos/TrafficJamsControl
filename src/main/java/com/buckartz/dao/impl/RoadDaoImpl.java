package com.buckartz.dao.impl;

import com.buckartz.dao.RoadDao;
import com.buckartz.model.map.Edge;
import com.buckartz.model.map.Road;
import com.buckartz.util.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RoadDaoImpl extends BaseDaoImpl<Road> implements RoadDao {
    @Override
    public Set<Road> getRoadsByEdge(Edge edge) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = HibernateUtil.getMyTransaction(session);
        Query query = session.createQuery("FROM Road WHERE edge = :edge");
        query.setParameter("edge", edge);
        query.setCacheable(true);
        Set<Road> roadSet = new HashSet<>(query.list());
        session.close();
        return roadSet;
    }

    @Override
    public Road getRoadByEdgeAndByXAndByY(Edge edge, Integer x, Integer y) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = HibernateUtil.getMyTransaction(session);
        Query query = session.createQuery("FROM Road WHERE edge = :edge AND x = :x AND y = :y");
        query.setParameter("edge", edge);
        query.setParameter("x", x);
        query.setParameter("y", y);
        query.setCacheable(true);
        Road road = (Road) query.uniqueResult();
        return road;
    }

    @Override
    public Set<Road> getAllRoads() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = HibernateUtil.getMyTransaction(session);
        Query query = session.createQuery("FROM Road ");
        query.setCacheable(true);
        Set<Road> roadSet = new HashSet<>(query.list());
        return roadSet;
    }

}
