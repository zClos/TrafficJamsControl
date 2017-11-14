package com.buckartz.dao;

import com.buckartz.model.map.Edge;
import com.buckartz.model.map.Road;

import java.util.List;
import java.util.Set;

public interface RoadDao extends BaseDao<Road> {
    
    Set<Road> getRoadsByEdge(Edge edge);

    Road getRoadByEdgeAndByXAndByY(Edge edge, Integer x, Integer y);

    Set<Road> getAllRoads();
}
