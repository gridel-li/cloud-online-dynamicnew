package com.sgcc.zj.manager;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;

@SpringBootApplication(scanBasePackages = {"com.sgcc.zj"})
@MapperScan("com.sgcc.zj.common.dao")
@EnableNeo4jRepositories("com.sgcc.zj.common.repository")
public class CloudOnlineManagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(CloudOnlineManagerApplication.class, args);
    }

}

