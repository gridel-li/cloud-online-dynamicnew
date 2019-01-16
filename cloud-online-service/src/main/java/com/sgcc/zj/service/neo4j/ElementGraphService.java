
package com.sgcc.zj.service.neo4j;

import com.sgcc.zj.common.domain.ElementNode;

import java.util.Map;


/**
 * @author liyingjie
 */

public interface ElementGraphService {

    public Map<String, Object> elementNodeGraph(int limit);

    ElementNode save(ElementNode node);

    Iterable<ElementNode> findAll();
}

