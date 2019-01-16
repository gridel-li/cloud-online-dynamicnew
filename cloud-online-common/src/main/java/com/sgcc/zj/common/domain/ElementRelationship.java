
package com.sgcc.zj.common.domain;

import org.neo4j.ogm.annotation.*;


/**
 * @description: 元素关系
 * @author: liyingjie
 * @create: 2019-01-10
 */

@RelationshipEntity("BELONG_TO")
public class ElementRelationship {

    private @Id @GeneratedValue Long id;

    @StartNode
    private ElementNode fromNode;
    @EndNode
    private ElementNode toNode;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ElementNode getFromNode() {
        return fromNode;
    }

    public void setFromNode(ElementNode fromNode) {
        this.fromNode = fromNode;
    }

    public ElementNode getToNode() {
        return toNode;
    }

    public void setToNode(ElementNode toNode) {
        this.toNode = toNode;
    }

    public ElementRelationship(ElementNode fromNode, ElementNode toNode) {
        this.fromNode = fromNode;
        this.toNode = toNode;
    }

    @Override
    public String toString() {
        return "ElementRelationship{" +
                "id=" + id +
                ", fromNode=" + fromNode +
                ", toNode=" + toNode +
                '}';
    }
}

