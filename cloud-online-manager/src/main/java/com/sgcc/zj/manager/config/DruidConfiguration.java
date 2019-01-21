package com.sgcc.zj.manager.config;

/**
 * @author liyingjie
 * @Title: DruidConfig
 * @Description: druid配置
 * @date 2019/1/21
 */
/*@Configuration
public class DruidConfiguration {
    private static final Logger logger = LoggerFactory.getLogger(DruidConfiguration.class);

    private static final String DB_PREFIX = "spring.datasource";


    *//**
     * @function 添加
     * @return
     *//*
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource druid(){
        return new DruidDataSource();
    }

    //配置Druid的监控
    *//**
     * 1、配置一个管理后台的Servlet
     *//*
    @Bean
    public ServletRegistrationBean statViewServlet(){
        logger.info("阿里 druid init");
        ServletRegistrationBean bean = new ServletRegistrationBean(new StatViewServlet(), "/druid/*");
        Map<String,String> initParams = new HashMap<>();
        initParams.put("loginUsername","admin");
        initParams.put("loginPassword","123123");
        //默认就是允许所有访问
        initParams.put("allow","");
        initParams.put("deny","192.168.15.21");
        bean.setInitParameters(initParams);
        return bean;
    }

    *//**
     * 2、配置一个web监控的filter
     *//*
    @Bean
    public FilterRegistrationBean webStatFilter(){
        FilterRegistrationBean bean = new FilterRegistrationBean();
        bean.setFilter(new WebStatFilter());
        Map<String,String> initParams = new HashMap<>();
        initParams.put("exclusions","*.js,*.css,/druid/*");
        bean.setInitParameters(initParams);
        bean.setUrlPatterns(Arrays.asList("/*"));
        return  bean;
    }
}*/
