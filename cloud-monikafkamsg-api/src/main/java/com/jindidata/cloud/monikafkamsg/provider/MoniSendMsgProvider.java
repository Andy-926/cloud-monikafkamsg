package com.jindidata.cloud.monikafkamsg.provider;

import com.jindidata.service.common.ResultWrapper;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

/**
 * @author： <a href="mailto:18322710712@163.com">wangfei</a>
 * @date: 2018/11/11
 * @version: 1.0.0
 */
public interface MoniSendMsgProvider {



    @GetMapping(path = {"moniTestOpenMsg"}, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResultWrapper<String> moniTestOpenMsg();





}
