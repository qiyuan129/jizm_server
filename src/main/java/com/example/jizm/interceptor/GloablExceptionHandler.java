package com.example.jizm.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.example.jizm.util.BaseException;
import com.example.jizm.util.BaseResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author jinbin
 * @date 2018-07-08 22:37
 */
@ControllerAdvice
public class GloablExceptionHandler {
    @ResponseBody
    @ExceptionHandler(Exception.class)
    public Object handleException(Exception e) {
        String msg = e.getMessage();
        if (msg == null || msg.equals("")) {
            msg = "服务器出错,详细信息请查看服务器控制台";
            e.printStackTrace();
            return BaseResult.failWithCodeAndMsg(500,msg);
        }
        else{
            if(e instanceof BaseException){
                BaseException exception=(BaseException)e;
                return BaseResult.failWithCodeAndMsg(exception.getErrorCode(),exception.getMessage());
            }
            else{
                return BaseResult.failWithCodeAndMsg(500,e.getMessage());
            }
        }
    }
}
