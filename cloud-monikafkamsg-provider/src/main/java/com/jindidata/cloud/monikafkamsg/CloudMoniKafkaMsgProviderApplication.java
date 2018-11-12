package com.jindidata.cloud.monikafkamsg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author wangxiaowen
 */
@SpringBootApplication(exclude={DataSourceAutoConfiguration.class,HibernateJpaAutoConfiguration.class})
@EnableCircuitBreaker
@EnableDiscoveryClient
public class CloudMoniKafkaMsgProviderApplication extends SpringBootServletInitializer {
    public static void main(String args[]) {
        SpringApplication.run(CloudMoniKafkaMsgProviderApplication.class, args);
    }
}
