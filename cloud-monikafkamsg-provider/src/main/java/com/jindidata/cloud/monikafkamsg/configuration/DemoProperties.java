package com.jindidata.cloud.monikafkamsg.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author： <a href="mailto:wangxw_it@163.com">wangxiaowen</a>
 */

@ConfigurationProperties(prefix = "demo")
@Getter
@Setter
public class DemoProperties {

}
