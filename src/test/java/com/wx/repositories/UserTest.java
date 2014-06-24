package com.wx.repositories;

import com.wx.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.conversion.Result;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.neo4j.support.Neo4jTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

/**
 * Created by Shawn on 2014/6/12.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"/application-context.xml"})
@Transactional
public class UserTest {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private Neo4jTemplate template;

    @Test @Transactional
    public void template() {
        User user1 = template.save(new User("zhangsan", "zs1966", "123456"));
        User user2 = template.findOne(user1.getId(), User.class);
        assertEquals("retrieved name matches", "zhangsan", user2.getName());
    }

    @Test @Transactional
    public void templateRepository() {
        GraphRepository<User> userRepository = template.repositoryFor(User.class);
        User user1 = userRepository.save(new User("zhangsan", "zs1966", "123456"));
        User user2 = userRepository.findBySchemaPropertyValue("name", "zhangsan");// name is indexed
        assertEquals("retrieved name matches", "123456", user2.getPassword());
    }

    @Test
    public void save() {
        User u = new User();
        u.setName("zhangsan");
        u.setPassword("123456");
        userRepository.save(u);
        Result<User> result = userRepository.findAll();
        System.out.println(result);
        System.out.println(result.single());
    }

    @Test
    public void query() {
        Result<User> result = userRepository.findAll();
        System.out.println(result);
        Iterator<User> iterator = result.iterator();
        while (iterator.hasNext()) {
            User next =  iterator.next();
            System.out.println(next);
        }
    }

    @Test
    public void findbyid() {
        User user = userRepository.findById(101l);
        System.out.println(user);
    }
    @Test
    public void friends() {
        List<User> users = userRepository.findUserFriends(101l);
        System.out.println(users);
    }
    @Test
    @Transactional
    public void suggest() {
        List<UserRepository.UserRecommend> users = userRepository.suggestFriends(101l);
//        System.out.println(users);
        for (UserRepository.UserRecommend user : users) {
            System.out.println(user.getName());
            System.out.println(user.getCount());
        }
    }
    @Test
    @Transactional
    public void suggest1() {
        Map<String, Object> map = new HashMap<>();
        map.put("0", 1265l);
        Result<Map<String, Object>> result = template.query("start n=node({0}) match pf=n-[:FRIEND]-(f1)-[:FRIEND]-f2 " +
                "where not(n-[:FRIEND]-f2) " +
                "return f2.name as name,count(pf) as count", map);
        Iterator<Map<String, Object>> iterator = result.iterator();
        while (iterator.hasNext()) {
            Map<String, Object> next = iterator.next();
            System.out.println(next);
        }
    }

    public static void main(String[] args) {
        System.out.println("123");
    }
}
