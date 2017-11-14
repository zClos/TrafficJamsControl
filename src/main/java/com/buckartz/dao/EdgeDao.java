package com.buckartz.dao;

import com.buckartz.model.map.City;
import com.buckartz.model.map.Edge;

import java.util.List;

public interface EdgeDao extends BaseDao<Edge> {

    Edge getEdgeBySourceCityAndByTargetCity(City sourceCity, City targetCity);
    List<Edge> getAllEdgesFromCity(City city);
}
