package com.buckartz.model.map;

import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Cache;

import javax.persistence.*;

@Entity
@Table(name = "road")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "map")
public class Road {

    private Integer roadId;
    private Edge edge;
    private Integer x;
    private Integer y;
    private Integer backup;

    public Road() {
    }

    public Road(Edge edge, Integer x, Integer y, Integer backup) {
        this.edge = edge;
        this.x = x;
        this.y = y;
        this.backup = backup;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "road_id")
    public Integer getRoadId() {
        return roadId;
    }

    public void setRoadId(Integer roadId) {
        this.roadId = roadId;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "edge_id")
    public Edge getEdge() {
        return edge;
    }

    public void setEdge(Edge edge) {
        this.edge = edge;
    }

    @Column(name = "x")
    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    @Column(name = "y")
    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        this.y = y;
    }

    @Column(name = "backup", nullable = false, columnDefinition = "int default -1")
    public Integer getBackup() {
        return backup;
    }

    public void setBackup(Integer backup) {
        this.backup = backup;
    }

    @Override
    public String toString() {
        return "[" + backup + ']';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Road)) return false;

        Road road = (Road) o;

        if (getRoadId() != null ? !getRoadId().equals(road.getRoadId()) : road.getRoadId() != null) return false;
        if (getX() != null ? !getX().equals(road.getX()) : road.getX() != null) return false;
        return (getY() != null ? !getY().equals(road.getY()) : road.getY() != null);

    }

    @Override
    public int hashCode() {
        int result = getRoadId() != null ? getRoadId().hashCode() : 0;
        result = 31 * result + (getX() != null ? getX().hashCode() : 0);
        result = 31 * result + (getY() != null ? getY().hashCode() : 0);
        result = 31 * result + (getBackup() != null ? getBackup().hashCode() : 0);
        return result;
    }
}
