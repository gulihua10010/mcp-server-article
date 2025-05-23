package com.yupi.mcp.mcpserver.exception;


import com.yupi.mcp.mcpserver.common.ErrorCode;

/**
 * 自定义异常类
 *
 * @author <a href="https://github.com/liyupi">程序员鱼皮</a>
 * @from <a href="https://yupi.icu">编程导航知识星球</a>
 */
public class APIException extends RuntimeException {

    /**
     * 错误码
     */
    private final int code;

    public APIException(int code, String message) {
        super(message);
        this.code = code;
    }

    public APIException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.code = errorCode.getCode();
    }

    public APIException(ErrorCode errorCode, String message) {
        super(message);
        this.code = errorCode.getCode();
    }

    public int getCode() {
        return code;
    }
}
