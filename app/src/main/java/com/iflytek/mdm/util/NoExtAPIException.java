package com.iflytek.mdm.util;

/**
 * @Company: 科大讯飞
 * @Title:
 * @Description:
 * @Author 王泽民
 * @Email zmwang7@iflytek.com
 * @Date 2018/4/24 18:48
 * @Version V1.0
 */
public class NoExtAPIException extends RuntimeException {

    private static final long serialVersionUID = -7724206318121422414L;

    public NoExtAPIException(String message) {
        super(message);
    }

    public NoExtAPIException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoExtAPIException(Throwable cause) {
        super(cause == null ? null : cause.toString(), cause);
    }
}
