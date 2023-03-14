package com.brucebat.demo.dto.exception;

/**
 * http异常信息
 *
 * @author brucebat
 * @version 1.0
 * @since Created at 2023/3/14 10:15
 */
public class HttpException extends RuntimeException {

    private final String errorCode;
    private final String errorMessage;

    /**
     * http请求异常
     *
     * @param errorCode    异常code
     * @param errorMessage 异常消息
     */
    public HttpException(String errorCode, String errorMessage) {
        super(errorMessage);
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
