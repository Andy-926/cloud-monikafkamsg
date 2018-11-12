package com.jindidata.cloud.monikafkamsg.provider.impl;

import com.jindidata.cloud.monikafkamsg.message.OpenMessageProducer;
import com.jindidata.cloud.monikafkamsg.provider.MoniSendMsgProvider;
import com.jindidata.service.common.ResultWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author： <a href="mailto:18322710712@163.com">wangfei</a>
 * @date: 2018/11/11
 * @version: 1.0.0
 */
@RestController
public class MoniSendMsgProviderImpl implements MoniSendMsgProvider {

    @Autowired
    private OpenMessageProducer openMessageProducer;

    private static final Logger logger = LoggerFactory.getLogger(MoniSendMsgProviderImpl.class);


    // 模拟测试环境open订单消息
    // 读取日志文件，使用kafka producer手动发送消息
    @Override
    public ResultWrapper<String> moniTestOpenMsg() {
        ResultWrapper<String> wrapper = new ResultWrapper<>();

        BufferedReader br = null;
        try {
            //http://cstd.test63.tianyancha.com/dashboard/1008/serviceorder/detail/629
            //token :ef1b7c40-5845-44e4-a40e-914990f1fb29
            File file = new File("D:\\服务订单测试-5.txt");
            br = new BufferedReader(new FileReader(file));
            String str;
            int i = 0;







            int j = 0;
            while ((str = br.readLine()) != null) {

                //debug运行，逐条模拟
                String substring = str.substring(26);
                openMessageProducer.sendNoticeMsg(substring);

                //每10000条休眠一会
                i++;
                if (i % 10000 == 0) {
                    i = 0;
                    j++;
                    logger.info("10000条输出 休眠3s 第" + j + "万条");
                    try {
                        Thread.sleep(3000);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        wrapper.setMessage("success!");
        return wrapper;
    }


}
