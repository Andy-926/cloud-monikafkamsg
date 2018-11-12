package com.jindidata.cloud.monikafkamsg.configuration;

import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.ProducerFactory;

@Configuration
@EnableKafka
public class KafkaConfiguration {

//    @Bean
//    @ConditionalOnMissingBean(ConsumerFactory.class)
//    public ConsumerFactory<?, ?> kafkaConsumerFactory(KafkaProperties properties) {
//        return new DefaultKafkaConsumerFactory<>(properties.buildConsumerProperties(),new StringDeserializer(),new StringDeserializer());
//    }


    @Bean
    @ConditionalOnMissingBean(ProducerFactory.class)
    public ProducerFactory<?, ?> kafkaProducerFactory(KafkaProperties properties) {
        return new DefaultKafkaProducerFactory<>(properties.buildProducerProperties(), new StringSerializer(), new StringSerializer());
    }
}