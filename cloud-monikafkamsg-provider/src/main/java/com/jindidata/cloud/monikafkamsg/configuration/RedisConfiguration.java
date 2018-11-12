package com.jindidata.cloud.monikafkamsg.configuration;

import com.jindidata.cloud.core.serialize.RedisKeySerializer;
import com.jindidata.cloud.core.serialize.RedisValueSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

import java.net.UnknownHostException;

/**
 * @author： <a href="mailto:wangxw_it@163.com">wangxiaowen</a>
 * @date: 2018/3/14 下午3:33
 */
@Configuration
public class RedisConfiguration {

    @Bean
    public RedisTemplate<Object, Object> redisTemplate(
            RedisConnectionFactory redisConnectionFactory) throws UnknownHostException {
        RedisTemplate<Object, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);
        template.setKeySerializer(new RedisKeySerializer());
        template.setValueSerializer(new RedisValueSerializer());
        return template;
    }
    
}
