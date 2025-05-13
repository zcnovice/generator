package com.zc.maker.meta;

/**
 * 元信息异常
 */
public class MetaException extends RuntimeException {

    /* 信息 */
    public MetaException(String message) {
        super(message);
    }
    /* 异常原因 */
    public MetaException(String message, Throwable cause) {
        super(message, cause);
    }
}
