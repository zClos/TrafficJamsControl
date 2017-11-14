package com.buckartz.dao;

import com.buckartz.model.map.City;

import java.util.List;

public interface CityDao extends BaseDao<City> {

    City getCityByName(String name);

    List<City> getAllCities();

    void add(String name, Integer x, Integer y);

}
