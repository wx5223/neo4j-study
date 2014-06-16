package com.wx.domain;

import org.neo4j.graphdb.Direction;
import org.springframework.data.neo4j.annotation.*;
import org.springframework.data.neo4j.support.index.IndexType;
import org.springframework.data.neo4j.template.Neo4jOperations;

import java.lang.annotation.ElementType;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Shawn on 2014/6/12.
 */
@NodeEntity
public class User {
    @GraphId
    private Long id;
    @Indexed
    private String name;
    private String account;
    private String password;
    //, direction = Direction.BOTH 设置双向时关系不能保存，bug ？
    @RelatedTo(type = "FRIEND")
    @Fetch
    private Set<User> friends;
    @RelatedToVia(type = "ORDER")
    @Fetch private Set<Order> orders;

    public Order order(Neo4jOperations template,Resource resource) {
        Order order = template.createRelationshipBetween(this, resource, Order.class, "ORDER", false);
        order.time = new Date();
        return template.save(order);
    }

    public void addFriend(User user) {
        this.friends.add(user);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User(String name, String account, String password) {
        this.name = name;
        this.account = account;
        this.password = password;
    }

    public User() {
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", account='" + account + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public Set<User> getFriends() {
        return friends;
    }

    public void setFriends(Set<User> friends) {
        this.friends = friends;
    }

    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }
}
