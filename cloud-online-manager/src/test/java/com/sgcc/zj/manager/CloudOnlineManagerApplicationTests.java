package com.sgcc.zj.manager;

import org.assertj.core.util.DateUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CloudOnlineManagerApplicationTests {
    private static final Logger LOGGER = LoggerFactory.getLogger(CloudOnlineManagerApplicationTests.class);

    @Test
    public void contextLoads() {

        String date = DateUtil.formatAsDatetime(new Date());
        LOGGER.info("date:{},info",date);
        LOGGER.error("date:{},error",date);
        LOGGER.debug("date:{},debug",date);
        LOGGER.warn("date:{},warn",date);
        LOGGER.trace("date:{},trace",date);



    }

}

