package com.wx.repositories;


import com.wx.domain.User;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.annotation.QueryResult;
import org.springframework.data.neo4j.annotation.ResultColumn;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by Shawn on 2014/6/12.
 */
public interface UserRepository extends GraphRepository<User> {
    User findById(Long id);

    Iterable<User> findByNameLike(String name);

    @Query("MATCH (u:User { id: {id} }), u-[f:FRIEND]->u1 " +
            "RETURN u1 " +
            "ORDER BY f.count DESC " +
            "LIMIT 5 ")
    List<User> findUserFriends(@Param("id")Long id);

    /*@Query("start n=node({0}) " +
            "match pf=n-[:FRIEND]-(f1)-[:FRIEND]-f2 " +
            "with n,f2,count(pf)as pfcount " +
            "where not(n-[:FRIEND]-f2) " +
            "return f2.id,f2.name,pfcount as count")*/
    @Query("match (u:User { id: {id} }),pf=u-[:FRIEND]-(f1)-[:FRIEND]-f2 " +
            "where not(u-[:FRIEND]-f2) " +
            "return f2.name as name,count(pf) as count")
            /*"start n=node(1265) match pf=n-[:FRIEND]-(f1)-[:FRIEND]-f2
with n,f2,count(pf)as pfcount
where not(n-[:FRIEND]-f2)
return f2.id, f2.name,pfcount"*/
    List<UserRecommend> suggestFriends(@Param("id")Long id);
    @QueryResult
    interface UserRecommend {
        @ResultColumn("name")
        String getName();
        Integer getCount();

    }
}
