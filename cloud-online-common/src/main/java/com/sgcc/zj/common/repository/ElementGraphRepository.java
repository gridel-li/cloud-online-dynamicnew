package com.sgcc.zj.common.repository;

import com.sgcc.zj.common.domain.ElementNode;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;

/**
 * @description: 关系接口
 * @author: liyingjie
 * @create: 2019-01-15
 */
@Repository
public interface ElementGraphRepository extends Neo4jRepository<ElementNode,Long> {

    @Query("MATCH (f:ElementNode)-[e:BELONG_TO]-(t:ElementNode) RETURN f,e,t LIMIT {limit}")
    Collection<ElementNode> graph(@Param("limit") int limit);
}
