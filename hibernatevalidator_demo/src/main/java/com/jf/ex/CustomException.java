package com.jf.ex;

/**
 * @author seinfish
 * @version 1.0
 * @description TODO
 * @date 2023/5/22 12:30
 */
public class CustomException extends RuntimeException {
    private String errMessage;

    public CustomException() {
        super();
    }

    public CustomException(String errMessage) {
        super(errMessage);
        this.errMessage = errMessage;
    }

    public String getErrMessage() {
        return errMessage;
    }

    public static void cast(CommonError commonError) {
        throw new CustomException(commonError.getErrMessage());
    }
    public static void cast(String errMessage) {
        throw new CustomException(errMessage);
    }

}
