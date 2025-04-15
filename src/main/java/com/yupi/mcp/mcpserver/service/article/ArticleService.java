package com.yupi.mcp.mcpserver.service.article;

import com.yupi.mcp.mcpserver.dto.ArticlePublishRequest;
import com.yupi.mcp.mcpserver.dto.ArticlePublishResponse;

/**
 * @author gulihua
 * @Description
 * @date 2025-04-10 20:49
 */
public interface ArticleService {

    /***
     * 发布文章
     * @param articlePublishRequest
     * @return
     */
    ArticlePublishResponse publishArticle(ArticlePublishRequest articlePublishRequest);

}
