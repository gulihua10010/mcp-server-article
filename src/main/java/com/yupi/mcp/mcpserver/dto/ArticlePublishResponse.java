package com.yupi.mcp.mcpserver.dto;

import lombok.Data;

/**
 * 文件发布向英国
 *
 * @author gulihua
 * @date 2025-04-10 20:59
 */
@Data
public class ArticlePublishResponse {

    private String id;
    private String link;
    private Boolean isSuccess;
}
