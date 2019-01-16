package com.sgcc.zj.common.config;

import org.neo4j.ogm.session.SessionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.neo4j.transaction.Neo4jTransactionManager;

/**
 * @description: neo4j配置文件
 * @author: liyingjie
 * @create: 2019-01-11
 */
@Configuration
@PropertySource("classpath:neo4j.properties")
public class Neo4jConfig {

    @Value("${neo4j.uri}")
    private String uri;

    @Value("${neo4j.username}")
    private String username;

    @Value("${neo4j.password}")
    private String password;

    @Value("${neo4j.connection.pool.size}")
    private Integer connectionPoolSize;


    @Bean
    public SessionFactory sessionFactory() {
        return new SessionFactory(configuration(), "com.sgcc.zj.common.domain");
    }

    @Bean
    public org.neo4j.ogm.config.Configuration configuration() {
        return new org.neo4j.ogm.config.Configuration.Builder()
                .uri(uri)
                //配置连接池大小
                .connectionPoolSize(connectionPoolSize)
                //用户密码
                .credentials(username,password)
                //索引策略
                .autoIndex("update")
                .connectionLivenessCheckTimeout(1000)
                .verifyConnection(false)
                .build();
    }
    @Bean
    public Neo4jTransactionManager transactionManager() {
        return new Neo4jTransactionManager(sessionFactory());
    }

}
