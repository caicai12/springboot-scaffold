package com.zhh;

import com.zhh.web.CommonController;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


/**
 * @Description: 单元测试
 * @Author: zhouhui
 * @Version: V1.0
 * @Date: 2019/3/19 9:28
 */
@SpringBootTest
// 引入Spring对JUnit4的支持
@RunWith(SpringJUnit4ClassRunner.class)
// 开启Web应用的配置，用于模拟ServletContext
@WebAppConfiguration
public class SpringBootBaseApplicationTests {
    Logger logger = LoggerFactory.getLogger(SpringBootBaseApplicationTests.class);

    // MockMvc对象，用于模拟调用接口的请求
    private MockMvc mockMvc;

    @Before
    public void before(){
        logger.info("before test...");
        mockMvc = MockMvcBuilders.standaloneSetup(new CommonController()).build();
    }

    @Test
    public void testClearAllCaches() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/common/clearAllCaches").accept(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @After
    public void after(){
        logger.info("after test...");
    }
}
