package com.jindidata.cloud.monikafkamsg.configuration;

import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

/**
 *
 * @author： <a href="mailto:wangxw_it@163.com">wangxiaowen</a>
 */
@Configuration
@ConditionalOnClass(SqlSessionTemplate.class)
@MapperScan(basePackages = "com.jindidata.cloud.monikafkamsg.mapper", sqlSessionTemplateRef = "demoSST")
public class DemoDataSource extends CommonDataSource {
	
	@Bean(name = "demoDS")
    @ConfigurationProperties(prefix = "datasource.demo")
    public DataSource demoDataSource() {
        return commonDataSource();
    }

    @Bean(name = "demoSSFB")
    @ConfigurationProperties(prefix = "mybatis.demo")
    public SqlSessionFactoryBean demoSqlSessionFactoryBean() throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(demoDataSource());
        return sqlSessionFactoryBean;
    }

    @Bean(name = "demoSST")
    public SqlSessionTemplate demoSqlSessionTemplate() throws Exception {
        return new SqlSessionTemplate(demoSqlSessionFactoryBean().getObject());
    }

    @Bean(name = "demoJdbc")
    public JdbcTemplate demoJdbcTemplate(){
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.setDataSource(demoDataSource());
        return jdbcTemplate;
    }
}
