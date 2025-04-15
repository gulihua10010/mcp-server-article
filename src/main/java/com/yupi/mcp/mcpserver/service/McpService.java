package com.yupi.mcp.mcpserver.service;

import com.yupi.mcp.mcpserver.dto.ArticlePublishRequest;
import com.yupi.mcp.mcpserver.dto.NewsDTO;
import com.yupi.mcp.mcpserver.properties.WebAuthProperties;
import com.yupi.mcp.mcpserver.service.article.ArticleService;
import com.yupi.mcp.mcpserver.service.article.NowCoderService;
import com.yupi.mcp.mcpserver.service.article.QueryNewsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * @author gulihua
 * @Description
 * @date 2025-03-26 11:35
 */
@Slf4j
@Service
public class McpService {

    @Autowired
    private NowCoderService nowCoderService;
    @Autowired
    private QueryNewsService queryNewsService;

    @Qualifier("articleCsdnService")
    @Autowired
    private ArticleService articleCsdnService;
    @Autowired
    @Qualifier("articleJuejinService")
    private ArticleService articleJuejinService;
    @Autowired
    @Qualifier("articleCnblogService")
    private ArticleService articleCnblogService;

    /**
     * 从牛客搜索用户发表的面经
     *
     * @return 面经内容
     */
    @Tool(description = "从牛客搜索用户发表的面经，返回的是markdown格式的面经内容")
    public String searchExperienceQuestion() {
        return nowCoderService.crawlExperienceQuestion().getContent();
    }

    /**
     * 从博客园查询最新的科技资讯话题内容
     *
     * @return 面经内容
     */
    @Tool(description = "从网上查询最新的科技资讯话题内容，返回标题和内容")
    public NewsDTO queryhNews() {
        return queryNewsService.getNews();
    }

    /**
     * 发布文章到CSDN
     *
     * @return 内容
     */
    @Tool(description = "发布文章到CSDN，参数为标题、内容、描述，返回值为文章链接")
    public String publishArticle2Csdn(ArticlePublishRequest articlePublishRequest) {
        return articleCsdnService.publishArticle(articlePublishRequest).getLink();
    }
    /**
     * 发布文章到掘金
     *
     * @return 内容
     */
    @Tool(description = "发布文章到掘金，参数为标题、内容、描述，返回值为文章链接")
    public String publishArticle2Juejin(ArticlePublishRequest articlePublishRequest) {
        return articleJuejinService.publishArticle(articlePublishRequest).getLink();
    }
    /**
     * 发布文章到博客园
     *
     * @return 内容
     */
    @Tool(description = "发布文章到博客园，参数为标题、内容、描述，返回值为文章链接")
    public String publishArticle2Cnblog(ArticlePublishRequest articlePublishRequest) {
        return articleCnblogService.publishArticle(articlePublishRequest).getLink();
    }
}
