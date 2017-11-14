package com.buckartz.model.map;

import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Cache;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "edge")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "map")
public class Edge {
    private Integer edgeId;
    private City source;
    private City target;
    private Set<Road> roadList;

    @Id
    @Column(name = "id_edge")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getEdgeId() {
        return edgeId;
    }

    public void setEdgeId(Integer idEdge) {
        this.edgeId = idEdge;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(referencedColumnName = "city_id")
    public City getSource() {
        return source;
    }

    public void setSource(City source) {
        this.source = source;
    }

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(referencedColumnName = "city_id")
    public City getTarget() {
        return target;
    }

    public void setTarget(City target) {
        this.target = target;
    }


    @OneToMany(fetch = FetchType.LAZY, mappedBy = "edge", cascade = CascadeType.ALL)
    public Set<Road> getRoadList() {
        return roadList;
    }

    public void setRoadList(Set<Road> roadList) {
        this.roadList = roadList;
    }

    @Override
    public String toString() {
        return "Edge{" +
                "edgeId=" + edgeId +
                ", source=" + source +
                ", target=" + target +
                ", roadList=" + roadList +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Edge)) return false;

        Edge edge = (Edge) o;

        if (getEdgeId() != null ? !getEdgeId().equals(edge.getEdgeId()) : edge.getEdgeId() != null) return false;
        if (getSource() != null ? !getSource().equals(edge.getSource()) : edge.getSource() != null) return false;
        return getTarget() != null ? getTarget().equals(edge.getTarget()) : edge.getTarget() == null;
    }

    @Override
    public int hashCode() {
        int result = getEdgeId() != null ? getEdgeId().hashCode() : 0;
        result = 31 * result + (getSource() != null ? getSource().hashCode() : 0);
        result = 31 * result + (getTarget() != null ? getTarget().hashCode() : 0);
        return result;
    }
}
