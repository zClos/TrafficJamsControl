package com.buckartz.model.map;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.util.Map;

@Entity
@Table(name = "city")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "map")
public class City {

    private Integer cityId;
    private String name;
    private Integer x;
    private Integer y;
    private Map<City, Edge> cityEdgeMap;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "city_id", nullable = false)
    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "x", nullable = false)
    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    @Column(name = "y", nullable = false)
    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        this.y = y;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "source", cascade = CascadeType.ALL)
    @MapKey(name = "target")
    public Map<City, Edge> getCityEdgeMap() {
        return cityEdgeMap;
    }

    public void setCityEdgeMap(Map<City, Edge> cityEdgeMap) {
        this.cityEdgeMap = cityEdgeMap;
    }

    @Override
    public String toString() {
        return '{' + name + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof City)) return false;

        City city = (City) o;

        if (getCityId() != null ? !getCityId().equals(city.getCityId()) : city.getCityId() != null) return false;
        if (getName() != null ? !getName().equals(city.getName()) : city.getName() != null) return false;
        if (getX() != null ? !getX().equals(city.getX()) : city.getX() != null) return false;
        return getY() != null ? getY().equals(city.getY()) : city.getY() == null;
    }

    @Override
    public int hashCode() {
        int result = getCityId() != null ? getCityId().hashCode() : 0;
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        result = 31 * result + (getX() != null ? getX().hashCode() : 0);
        result = 31 * result + (getY() != null ? getY().hashCode() : 0);
        return result;
    }
}
