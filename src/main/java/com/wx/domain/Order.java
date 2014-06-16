package com.wx.domain;

import org.springframework.data.neo4j.annotation.EndNode;
import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.RelationshipEntity;
import org.springframework.data.neo4j.annotation.StartNode;

import java.util.Date;

/**
 * Created by Shawn on 2014/6/13.
 */
@RelationshipEntity
public class Order {
    @GraphId
    Long id;
    @StartNode
    User uesr;
    @EndNode
    Resource resource;
    Date time;

}
