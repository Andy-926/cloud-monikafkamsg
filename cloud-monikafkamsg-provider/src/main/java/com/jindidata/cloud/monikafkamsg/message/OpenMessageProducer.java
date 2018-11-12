package com.jindidata.cloud.monikafkamsg.message;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Component
public class OpenMessageProducer {

    private Logger logger = LoggerFactory.getLogger(OpenMessageProducer.class);

    private ThreadPoolExecutor sendExecutors = new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors(), Runtime.getRuntime().availableProcessors()*2,
            10L, TimeUnit.SECONDS, new LinkedBlockingQueue<>(), Executors.defaultThreadFactory());

    private final KafkaTemplate kafkaTemplate;

    public OpenMessageProducer(KafkaTemplate kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendNoticeMsg(String message) {
        sendExecutors.execute( () ->
                {
                    try{
                        //测试环境open消息对列名：open_local
                        ListenableFuture send = kafkaTemplate.send("open_local_2", message);
                        send.addCallback(new ListenableFutureCallback<SendResult<Integer, String>>() {

                            @Override
                            public void onSuccess(SendResult<Integer, String> result) {
                                handleSuccess(message);
                            }

                            @Override
                            public void onFailure(Throwable ex) {
                                handleFailure(message, ex);
                            }
                        });
                        logger.info("发送一条msg");
                    }catch (Exception ex){
                        logger.error("send noticeMsg error;msg:{},ex:{}", message, ex);
                    }
                }
        );


    }

    private void handleFailure(String data, Throwable ex) {
        logger.error("handleFailure;data:{},ex:{}", data, ex);
    }

    private void handleSuccess(String data) {

    }
}
