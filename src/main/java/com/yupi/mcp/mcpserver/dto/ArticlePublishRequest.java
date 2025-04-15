package com.yupi.mcp.mcpserver.dto;

import lombok.Data;

/**
 * 文件发布请求
 *
 * @author gulihua
 * @date 2025-04-10 20:59
 */
@Data
public class ArticlePublishRequest {

    private String title;
    private String description;
    private String content;
}
