package com.yupi.mcp.mcpserver.dto;

import cn.hutool.core.annotation.Alias;
import lombok.Data;

import java.io.Serializable;

/**
 * @author gulihua
 * @Description
 * @date 2025-04-10 20:40
 */
@Data
public class ArticlePublishCsdnResponse implements Serializable {


    private Integer code;
    private String traceId;
    private Object data;
    private String message;
    private String msg;


    @lombok.Data
    public class Data {

        private String url;
        private Long id;
        private String title;
        private String qrcode;
        private String description;

    }
}
