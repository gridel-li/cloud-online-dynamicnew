
package com.sgcc.zj.common.domain;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @description: 元素节点
 * @author: liyingjie
 * @create: 2019-01-10
 */

@NodeEntity
public class ElementNode implements Serializable {

    private @Id @GeneratedValue Long id;

    private String elementName;

    private String elementType;

    @Relationship(type = "BELONG_TO", direction = Relationship.INCOMING)
    private List<ElementRelationship> elementRelationships;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getElementName() {
        return elementName;
    }

    public void setElementName(String elementName) {
        this.elementName = elementName;
    }

    public String getElementType() {
        return elementType;
    }

    public void setElementType(String elementType) {
        this.elementType = elementType;
    }


    public ElementNode(){

    }

    public ElementNode(String elementName, String elementType) {
        this.elementName = elementName;
        this.elementType = elementType;
    }

    public List<ElementRelationship> getElementRelationships() {
        return elementRelationships;
    }

    public void setElementRelationships(List<ElementRelationship> elementRelationships) {
        this.elementRelationships = elementRelationships;
    }
    public void addElementRelationship(ElementRelationship elementRelationship) {
        if (this.elementRelationships == null) {
            this.elementRelationships = new ArrayList<>();
        }
        this.elementRelationships.add(elementRelationship);
    }
}

