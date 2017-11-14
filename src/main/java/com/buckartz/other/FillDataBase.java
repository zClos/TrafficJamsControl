package com.buckartz.other;

import com.buckartz.model.map.City;
import com.buckartz.model.map.Edge;
import com.buckartz.model.map.Road;
import com.buckartz.model.user.Admin;
import com.buckartz.model.user.Driver;
import com.buckartz.model.user.User;
import com.buckartz.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.*;

import static java.lang.Math.random;

public class FillDataBase {
    public static void fillData() {
        City cityA = new City();
        cityA.setName("A");
        cityA.setX(0);
        cityA.setY(0);
        City cityB = new City();
        cityB.setName("B");
        cityB.setX(1);
        cityB.setY(8);
        City cityC = new City();
        cityC.setName("C");
        cityC.setX(5);
        cityC.setY(8);

        Edge edgeAB = new Edge();
        edgeAB.setSource(cityA);
        edgeAB.setTarget(cityB);
        Edge edgeAC = new Edge();
        edgeAC.setSource(cityA);
        edgeAC.setTarget(cityC);
        Edge edgeBA = new Edge();
        edgeBA.setSource(cityB);
        edgeBA.setTarget(cityA);
        Edge edgeBC = new Edge();
        edgeBC.setSource(cityB);
        edgeBC.setTarget(cityC);
        Edge edgeCA = new Edge();
        edgeCA.setSource(cityC);
        edgeCA.setTarget(cityA);
        Edge edgeCB = new Edge();
        edgeCB.setSource(cityC);
        edgeCB.setTarget(cityB);

        Set<Road> roadAB = new HashSet<>();
        roadAB.add(new Road(edgeAB,0,1, (int) (random()*11-1)));
        roadAB.add(new Road(edgeAB,0,2,(int) (random()*11-1)));
        roadAB.add(new Road(edgeAB,0,3,(int) (random()*11-1)));
        roadAB.add(new Road(edgeAB,1,4,(int) (random()*11-1)));
        roadAB.add(new Road(edgeAB,1,5,(int) (random()*11-1)));
        roadAB.add(new Road(edgeAB,0,6,(int) (random()*11-1)));
        roadAB.add(new Road(edgeAB,0,7,(int) (random()*11-1)));
        Set<Road> roadBA = new HashSet<>();
        roadBA.add(new Road(edgeBA,0,1,(int) (random()*11-1)));
        roadBA.add(new Road(edgeBA,0,2,(int) (random()*11-1)));
        roadBA.add(new Road(edgeBA,0,3,(int) (random()*11-1)));
        roadBA.add(new Road(edgeBA,1,4,(int) (random()*11-1)));
        roadBA.add(new Road(edgeBA,1,5,(int) (random()*11-1)));
        roadBA.add(new Road(edgeBA,0,6,(int) (random()*11-1)));
        roadBA.add(new Road(edgeBA,0,7,(int) (random()*11-1)));

        Set<Road> roadAC = new HashSet<>();
        roadAC.add(new Road(edgeAC,1,1,(int) (random()*11-1)));
        roadAC.add(new Road(edgeAC,2,2,(int) (random()*11-1)));
        roadAC.add(new Road(edgeAC,3,3,(int) (random()*11-1)));
        roadAC.add(new Road(edgeAC,4,4,(int) (random()*11-1)));
        roadAC.add(new Road(edgeAC,5,5,(int) (random()*11-1)));
        roadAC.add(new Road(edgeAC,5,6,(int) (random()*11-1)));
        roadAC.add(new Road(edgeAC,5,7,(int) (random()*11-1)));
        Set<Road> roadCA = new HashSet<>();
        roadCA.add(new Road(edgeCA,1,1,(int) (random()*11-1)));
        roadCA.add(new Road(edgeCA,2,2,(int) (random()*11-1)));
        roadCA.add(new Road(edgeCA,3,3,(int) (random()*11-1)));
        roadCA.add(new Road(edgeCA,4,4,(int) (random()*11-1)));
        roadCA.add(new Road(edgeCA,5,5,(int) (random()*11-1)));
        roadCA.add(new Road(edgeCA,5,6,(int) (random()*11-1)));
        roadCA.add(new Road(edgeCA,5,7,(int) (random()*11-1)));

        Set<Road> roadBC = new HashSet<>();
        roadBC.add(new Road(edgeBC,2,8,(int) (random()*11-1)));
        roadBC.add(new Road(edgeBC,3,8,(int) (random()*11-1)));
        roadBC.add(new Road(edgeBC,4,8,(int) (random()*11-1)));
        Set<Road> roadCB = new HashSet<>(roadBC);
        roadCB.add(new Road(edgeCB,2,8,(int) (random()*11-1)));
        roadCB.add(new Road(edgeCB,3,8,(int) (random()*11-1)));
        roadCB.add(new Road(edgeCB,4,8,(int) (random()*11-1)));


        edgeAB.setRoadList(roadAB);
        edgeAC.setRoadList(roadAC);
        edgeBA.setRoadList(roadBA);
        edgeBC.setRoadList(roadBC);
        edgeCA.setRoadList(roadCA);
        edgeCB.setRoadList(roadCB);

        Map<City, Edge> cityEdgeMapA = new HashMap<>();
        cityEdgeMapA.put(cityB, edgeAB);
        cityEdgeMapA.put(cityC, edgeAC);
        Map<City, Edge> cityEdgeMapB = new HashMap<>();
        cityEdgeMapB.put(cityA, edgeBA);
        cityEdgeMapB.put(cityC, edgeBC);
        Map<City, Edge> cityEdgeMapC = new HashMap<>();
        cityEdgeMapC.put(cityA, edgeCA);
        cityEdgeMapC.put(cityB, edgeCB);

        cityA.setCityEdgeMap(cityEdgeMapA);
        cityB.setCityEdgeMap(cityEdgeMapB);
        cityC.setCityEdgeMap(cityEdgeMapC);

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();
        session.save(cityA);
        session.save(cityB);
        session.save(cityC);

        User admin = new Admin();
        admin.setName("Goddy");
        admin.setLogin("admin");
        admin.setPass("admin");
        User driver = new Driver();
        driver.setName("Vin Diesel");
        driver.setLogin("driver");
        driver.setPass("driver");

        session.save(admin);
        session.save(driver);
        transaction.commit();

    }
}
