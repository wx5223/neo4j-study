package com.wx.domain;

import org.springframework.data.neo4j.annotation.*;

import java.util.Date;

/**
 * Created by Shawn on 2014/6/13.
 */
@RelationshipEntity
public class Order {
    @GraphId
    private Long graphId;
    @StartNode
    @Fetch
    private User uesr;
    @EndNode
    @Fetch
    private Resource resource;
    private Date time;

    public Long getGraphId() {
        return graphId;
    }

    public void setGraphId(Long graphId) {
        this.graphId = graphId;
    }

    public User getUesr() {
        return uesr;
    }

    public void setUesr(User uesr) {
        this.uesr = uesr;
    }

    public Resource getResource() {
        return resource;
    }

    public void setResource(Resource resource) {
        this.resource = resource;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
