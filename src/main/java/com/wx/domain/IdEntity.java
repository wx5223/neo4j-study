package com.wx.domain;

import org.springframework.data.neo4j.annotation.GraphId;

/**
 * Created by Shawn on 2014/6/24.
 */
public abstract class IdEntity {
    @GraphId
    private Long graphId;

    public Long getGraphId() {
        return graphId;
    }

    public void setGraphId(Long graphId) {
        this.graphId = graphId;
    }


}
