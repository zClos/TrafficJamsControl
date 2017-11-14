package com.buckartz.service.service_map;

import com.buckartz.dao.RoadDao;
import com.buckartz.dao.impl.RoadDaoImpl;
import com.buckartz.model.map.Edge;
import com.buckartz.model.map.Road;

import java.util.Set;

public class RoadService {

    private RoadDao roadDao = new RoadDaoImpl();

    public Set<Road> getAll() {
        return roadDao.getAllRoads();
    }

    public Road getRoad(Edge edge, Integer x, Integer y) {
        return roadDao.getRoadByEdgeAndByXAndByY(edge, x, y);
    }

    public void update(Road road) {
        roadDao.update(road);
    }
}
