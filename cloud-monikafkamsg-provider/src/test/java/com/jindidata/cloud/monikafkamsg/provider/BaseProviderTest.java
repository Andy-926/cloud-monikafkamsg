package com.jindidata.cloud.monikafkamsg.provider;

import com.jindidata.cloud.monikafkamsg.CloudMoniKafkaMsgProviderApplication;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

/**
 * @author： <a href="mailto:wangxw_it@163.com">wangxiaowen</a>
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {CloudMoniKafkaMsgProviderApplication.class})
//@Transactional
public class BaseProviderTest {

    protected static final String  CONTEX_URL = "http://localhost:20001";

    @Autowired
    private WebApplicationContext context;

    protected MockMvc mockMvc;

    @Before
    public void before() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }
}
