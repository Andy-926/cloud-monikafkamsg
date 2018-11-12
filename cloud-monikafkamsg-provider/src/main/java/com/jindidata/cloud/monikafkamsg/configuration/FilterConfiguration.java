package com.jindidata.cloud.monikafkamsg.configuration;

import com.alibaba.fastjson.JSON;
import com.jindidata.cloud.core.utils.StdRequestHandler;
import feign.RequestInterceptor;
import org.apache.commons.lang.StringUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

/**
 * @author： <a href="mailto:wangxw_it@163.com">wangxiaowen</a>
 * @date: 2018/6/13 下午6:03
 */
@Configuration
public class FilterConfiguration {

    @Bean
    public RequestInterceptor headerInterceptor() {
        return requestTemplate -> {
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            Enumeration<String> headerNames = request.getHeaderNames();
            if (headerNames != null) {
                while (headerNames.hasMoreElements()) {
                    String name = headerNames.nextElement();
                    if (!name.equalsIgnoreCase("content-type") && !name.equalsIgnoreCase("accept")) {
                        String values = request.getHeader(name);
                        requestTemplate.header(name, values);
                    }
                }
            }
            if (StringUtils.isNotBlank(request.getParameter("_version"))) {
                requestTemplate.header("version", request.getParameter("_version"));
            }

            String userInfo = JSON.toJSONString(StdRequestHandler.getJwtToken());
            requestTemplate.header("cloud_tyc_user", userInfo);
        };
    }
}
