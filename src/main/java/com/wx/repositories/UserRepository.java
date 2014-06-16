package com.wx.repositories;


import com.wx.domain.User;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;

/**
 * Created by Shawn on 2014/6/12.
 */
public interface UserRepository extends GraphRepository<User> {
    @Query("start user=node({0})" +
            "match user-[:FRIEND]-()-[:FRIEND]-other" +
            "return user")
            /*"start n=node(1265) \n"+
                    "match pf=n-[:FRIEND]-(f1)-[:FRIEND]-f2 \n"+
                    "with f2,count(pf)as pfcount\n"+
                    "where pfcount >1\n"+
                    "return f2,pfcount"*/
    Iterable<User> suggestFriends(User me);
}
