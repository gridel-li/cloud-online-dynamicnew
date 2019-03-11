package com.sgcc.zj.manager.controller.neo4j;

import com.sgcc.zj.core.aop.annotation.PGControllerMonitor;
import com.sgcc.zj.core.base.R;
import com.sgcc.zj.service.neo4j.ElementGraphService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;


/**
 * @description: 图谱
 * @author: liyingjie
 * @create: 2019-01-11
 */
@Api(value = "GraphController-API",description = "图谱controller")
@RestController
@RequestMapping("/graph")
public class GraphController {
    @Resource
    private ElementGraphService elementGraphService;

    @CrossOrigin
    @PGControllerMonitor
    @GetMapping("/graph")
    @ApiOperation(value="根据需要的节点数获取neo4j数据", notes="根据url的limit数量来获取图谱json,limit 非必填 默认100")
    @ApiImplicitParam(name = "limit", value = "节点数量", required = false, dataType = "Integer")
    public R graph(@RequestParam(value = "limit",required = false) Integer limit) {
        Map<String, Object> elements = elementGraphService.elementNodeGraph(limit == null ? 100 : limit);
        return R.ok().put("elements",elements);
    }



    /*@RequestMapping("/insertTest")
    public R insertTest(){

        ElementNode node1 = new ElementNode();
        node1.setElementName("ecs1");
        node1.setElementType("ecs");

        ElementNode node2 = new ElementNode();
        node2.setElementName("project1");
        node2.setElementType("project");

        ElementNode savenode1 = elementGraphService.save(node1);
        ElementNode savenode2 = elementGraphService.save(node2);
        System.out.println("node1"+savenode1.toString());
        System.out.println("node2"+savenode2.toString());
        ElementRelationship comRelation = new ElementRelationship(node2,node1);

        *//*ElementRelationship elementSave = elementRelationshipRepository.save(comRelation);
        System.out.println("关系"+elementSave.toString());
        ElementNode elementNode = elementGraphService.elementNodeSave(node1);
        System.out.println("elementNode"+elementNode.toString());
        Iterable<ElementNode> all = elementNodeRepository.findAll();*//*

        return R.ok().put("all","");
    }*/

}
