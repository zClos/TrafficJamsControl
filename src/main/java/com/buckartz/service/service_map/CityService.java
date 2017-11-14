package com.buckartz.service.service_map;

import com.buckartz.dao.CityDao;
import com.buckartz.dao.impl.CityDaoImpl;
import com.buckartz.model.map.City;

import java.util.List;

public class CityService {

    private final CityDao cityDao = new CityDaoImpl();

    public void add(City city) {
        cityDao.add(city);
    }

    public void add(String name, Integer x, Integer y){
        cityDao.add(name,x,y);
    }

    public List<City> getAll() {
        return cityDao.getAllCities();
    }

    public City getCity(String name) {
        return cityDao.getCityByName(name);
    }
}
