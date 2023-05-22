package com.jf.ex;

import java.io.Serializable;

/**
 * @author seinfish
 * @version 1.0
 * @description 错误响应参数包装
 * @date 2023/5/22 12:33
 */
public class RestErrorResponse implements Serializable {

    private String errMessage;

    public RestErrorResponse(String errMessage){
        this.errMessage= errMessage;
    }

    public String getErrMessage() {
        return errMessage;
    }
    public void setErrMessage(String errMessage) {
        this.errMessage = errMessage;
    }
}

