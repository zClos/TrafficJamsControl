package com.buckartz.service.service_map;

import com.buckartz.dao.EdgeDao;
import com.buckartz.dao.impl.EdgeDaoImpl;
import com.buckartz.model.map.City;
import com.buckartz.model.map.Edge;
import com.buckartz.model.map.Road;

import java.util.*;

public class EdgeService {

    private final EdgeDao edgeDao = new EdgeDaoImpl();

    public void add(Edge edge) {
        edgeDao.add(edge);
    }

    public void addEdge(City sourceCity, City targetCity, Set<Road> roads) {
        if (roads != null) {
            Edge checkEdge = edgeDao.getEdgeBySourceCityAndByTargetCity(sourceCity, targetCity);
            if (checkEdge != null) {
                roads.forEach(road -> checkEdge.getRoadList().add(road));
                roads.forEach(road -> road.setEdge(checkEdge));
                edgeDao.update(checkEdge);
            } else {
                Edge newEdge = new Edge();
                newEdge.setSource(sourceCity);
                newEdge.setTarget(targetCity);
                newEdge.setRoadList(roads);
                roads.forEach(road -> road.setEdge(newEdge));
                edgeDao.add(newEdge);
            }
        } else {
            System.out.println("Сохранить не возможно, не известен путь");
        }
    }

    public Edge getEdge(City sourceCity, City targetCity) {
        return edgeDao.getEdgeBySourceCityAndByTargetCity(sourceCity, targetCity);
    }

    public void updateEdge(Edge edge) {
        edgeDao.update(edge);
    }

    public Set<Road> getFastestPath(City sourceCity, City targetCity, Double avgMapBackup) {

        Map<Double, Set<Road>> mapOfRoutes = findRoutes(sourceCity, targetCity, avgMapBackup);

        Iterator<Double> keySet = new TreeSet<>(mapOfRoutes.keySet()).iterator();
        if (keySet.hasNext()) {
            Set<Road> fastestPath = mapOfRoutes.get(keySet.next());
            return fastestPath;
        } else {
            return null;
        }
    }

    private Map<Double, Set<Road>> findRoutes(City sourceCity, City targetCity, Double avgMapBackup) {
        List<Edge> edgesFromSource = edgeDao.getAllEdgesFromCity(sourceCity);
        List<Edge> edgesFromTarget = edgeDao.getAllEdgesFromCity(targetCity);

        Map<Double, Set<Road>> mapOfRoutes = new HashMap<>();
        for (Edge anEdgesFromSource : edgesFromSource) {
            City targetCityFromSource = anEdgesFromSource.getTarget();
            if (checkListEdgesByTarget(edgesFromTarget, targetCityFromSource) ||
                    targetCityFromSource.equals(targetCity)) {
                Set<Road> roadsOnRoute = new LinkedHashSet<>();
                roadsOnRoute.addAll(
                        edgeDao.getEdgeBySourceCityAndByTargetCity(sourceCity, targetCityFromSource).getRoadList()
                );
                if (!targetCityFromSource.equals(targetCity)) {
                    roadsOnRoute.addAll(
                            edgeDao.getEdgeBySourceCityAndByTargetCity(targetCityFromSource, targetCity).getRoadList()
                    );
                }
                Double avgRouteBackup = 0.0;
                for (Road road : roadsOnRoute) {
                    Integer backupRoad = road.getBackup();
                    if (backupRoad != -1) {
                        avgRouteBackup += backupRoad;
                    } else {
                        avgRouteBackup += avgMapBackup;
                    }
                }
                int size = roadsOnRoute.size();
                if (size != 0) {
                    avgRouteBackup /= size;
                }
                if (mapOfRoutes.containsKey(avgRouteBackup)) {
                    Set<Road> containingSet = mapOfRoutes.get(avgRouteBackup);
                    if (containingSet.size() > roadsOnRoute.size()) {
                        mapOfRoutes.put(avgRouteBackup, roadsOnRoute);
                    }
                } else {
                    mapOfRoutes.put(avgRouteBackup, roadsOnRoute);
                }
            }
        }
        return mapOfRoutes;
    }

    private boolean checkListEdgesByTarget(List<Edge> edgeList, City targetCity) {
        for (Edge edge : edgeList) {
            if (edge.getTarget().equals(targetCity)) {
                return true;
            }
        }
        return false;
    }
}
