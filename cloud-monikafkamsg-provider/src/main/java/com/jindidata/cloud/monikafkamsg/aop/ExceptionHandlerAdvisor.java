package com.jindidata.cloud.monikafkamsg.aop;

import com.alibaba.fastjson.JSON;
import com.jindidata.cloud.core.exception.JindiException;
import com.jindidata.service.common.ResultWrapper;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 异常处理类
 * @author： <a href="mailto:wangxw_it@163.com">wangxiaowen</a>
 */
@ControllerAdvice
public class ExceptionHandlerAdvisor {

	@ExceptionHandler({JindiException.class, IllegalArgumentException.class})
	public void handleJindiException(HttpServletResponse response,
									 Exception ex) throws IOException{
		ResultWrapper<String> resultWrapper = new ResultWrapper<>();
		resultWrapper.setState(ResultWrapper.STATE_WARN);
		resultWrapper.setMessage(ex.getMessage());
		sendJson(response, JSON.toJSONString(resultWrapper));
	}
	
	@ExceptionHandler({Exception.class})
	public void handleException(HttpServletResponse response,
			Exception ex) throws IOException{
		ResultWrapper<String> resultWrapper = new ResultWrapper<>();
		resultWrapper.setState(ResultWrapper.STATE_WARN);
		resultWrapper.setMessage("请稍后重试");
		ex.printStackTrace();
		sendJson(response, JSON.toJSONString(resultWrapper));
	}

    private void sendJson(HttpServletResponse httpResponse,String data){
        try(PrintWriter writer = httpResponse.getWriter()) {
            httpResponse.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
            httpResponse.setCharacterEncoding("UTF-8");
            writer.write(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

