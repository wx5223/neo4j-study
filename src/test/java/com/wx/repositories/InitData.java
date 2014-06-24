package com.wx.repositories;

import com.wx.domain.Resource;
import com.wx.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.support.Neo4jTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Shawn on 2014/6/13.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"/application-context.xml"})
@TransactionConfiguration(defaultRollback = false)
@Transactional
public class InitData {
    @Autowired
    private Neo4jTemplate template;
    @Test
    public void init() {
        User user1 = template.save(new User(101l,"name1", "account1", "123456"));
        User user2 = template.save(new User(102l,"name2", "account2", "123456"));
        User user3 = template.save(new User(103l,"name3", "account3", "123456"));
        User user4 = template.save(new User(104l,"name4", "account4", "123456"));
        User user5 = template.save(new User(105l,"name5", "account5", "123456"));
        User user6 = template.save(new User(106l,"name6", "account6", "123456"));
        User user7 = template.save(new User(107l,"name7", "account7", "123456"));
        user1.addFriend(user2);
        user1.addFriend(user3);
        user1.addFriend(user4);
        user2.addFriend(user5);
        user3.addFriend(user5);
        user4.addFriend(user6);
        user5.addFriend(user6);
        user6.addFriend(user7);
        template.save(user1);
        template.save(user2);
        template.save(user3);
        template.save(user4);
        template.save(user5);
        template.save(user6);
        template.save(user7);
        Resource r1 = new Resource(101l, "rn1", "1");
        Resource r2 = new Resource(102l, "rn2", "2");
        Resource r3 = new Resource(103l, "rn3", "1");
        Resource r4 = new Resource(104l, "rn4", "2");
        template.save(r1);
        template.save(r2);
        template.save(r3);
        template.save(r4);
        user2.order(template, r1);
        user2.order(template, r2);
        user3.order(template, r3);
        user4.order(template, r4);
        user5.order(template, r1);
        user6.order(template, r1);
    }


}
