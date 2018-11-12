package com.jindidata.cloud.monikafkamsg.configuration;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author： <a href="mailto:wangxw_it@163.com">wangxiaowen</a>
 */
@Configuration
@EnableConfigurationProperties(DemoProperties.class)
public class DemoConfiguration {

    private final DemoProperties demoProperties;

    public DemoConfiguration(DemoProperties demoProperties) {
        this.demoProperties = demoProperties;
    }
}
