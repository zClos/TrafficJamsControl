package com.buckartz.dao.impl;

import com.buckartz.dao.EdgeDao;
import com.buckartz.model.map.City;
import com.buckartz.model.map.Edge;
import com.buckartz.util.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class EdgeDaoImpl extends BaseDaoImpl<Edge> implements EdgeDao {
    @Override
    public Edge getEdgeBySourceCityAndByTargetCity(City sourceCity, City targetCity) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = HibernateUtil.getMyTransaction(session);
        Query query = session.createQuery("FROM Edge WHERE source = :sourceCity AND target = :targetCity");
        query.setParameter("sourceCity", sourceCity);
        query.setParameter("targetCity", targetCity);
        query.setCacheable(true);
        Edge edge = (Edge) query.uniqueResult();
        return edge;
    }

    @Override
    public List<Edge> getAllEdgesFromCity(City sourceCity) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = HibernateUtil.getMyTransaction(session);
        Query query = session.createQuery("FROM Edge WHERE source = :sourceCity");
        query.setParameter("sourceCity", sourceCity);
        query.setCacheable(true);
        List<Edge> edgeList = query.list();
        return edgeList;
    }

}
