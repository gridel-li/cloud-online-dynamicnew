package com.sgcc.zj.service.impl.neo4j;

import com.sgcc.zj.common.domain.ElementNode;
import com.sgcc.zj.common.domain.ElementRelationship;
import com.sgcc.zj.common.repository.ElementGraphRepository;
import com.sgcc.zj.service.neo4j.ElementGraphService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;


/**
 * @description: 图谱实现类
 * @author: liyingjie
 * @create: 2019-01-14
 */

@Service("elementGraphService")
public class ElementGraphServiceImpl implements ElementGraphService {

    @Resource
    private ElementGraphRepository elementGraphRepository;


    @Override
    public Map<String, Object> elementNodeGraph(int limit) {
        Collection<ElementNode> result = elementGraphRepository.graph(limit);
        return toD3sssFormat(result);
    }

    @Override
    public ElementNode save(ElementNode node) {
        return elementGraphRepository.save(node);
    }

    private Map<String, Object> toD3sssFormat(Collection<ElementNode> elementNodes) {
        List<Map<String, Object>> edges = new ArrayList<>();
        List<Map<String, Object>> nodes = new ArrayList<>();
        Iterator<ElementNode> result = elementNodes.iterator();
        while (result.hasNext()) {
            ElementNode elementNode = result.next();
            Map<String,Object> nodeObject = new HashMap<>(1);
            nodeObject.put("data",map3("id",elementNode.getElementName(),"target", elementNode.getElementName(), "type", elementNode.getElementType()));
            nodes.add(nodeObject);
            if (null!=elementNode.getElementRelationships()){
                for (ElementRelationship elementRelationship : elementNode.getElementRelationships()) {
                    Map<String,Object> edgeObject = new HashMap<>(1);
                    edgeObject.put("data",map3("source", elementRelationship.getFromNode().getElementName(), "target", elementRelationship.getToNode().getElementName(),"label","属于"));
                    edges.add(edgeObject);
                }
            }
        }
        return map("nodes", nodes, "edges", edges);
    }
    private Map<String, Object> map(String key1, Object value1, String key2, Object value2) {
        Map<String, Object> result = new HashMap<String, Object>(3);
        result.put(key1, value1);
        result.put(key2, value2);
        return result;
    }
    private Map<String, Object> map3(String key1, Object value1, String key2, Object value2,String key3, Object value3 ) {
        Map<String,Object> result = map(key1,value1,key2,value2);
        result.put(key3, value3);
        return result;
    }

    @Override
    public Iterable<ElementNode> findAll() {
        return elementGraphRepository.findAll();
    }
}

