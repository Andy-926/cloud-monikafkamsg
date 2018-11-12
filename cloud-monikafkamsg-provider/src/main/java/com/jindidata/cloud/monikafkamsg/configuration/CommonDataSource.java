package com.jindidata.cloud.monikafkamsg.configuration;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import javax.sql.DataSource;

/**
 *
 * @author： <a href="mailto:wangxw_it@163.com">wangxiaowen</a>
 */
@Configuration
public class CommonDataSource {
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    @ConfigurationProperties(prefix = "datasource.common")
    @Bean
    DataSource commonDataSource() {
        return DataSourceBuilder.create().build();
    }
}
