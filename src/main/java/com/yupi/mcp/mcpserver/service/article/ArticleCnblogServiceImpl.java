package com.yupi.mcp.mcpserver.service.article;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.json.JSONUtil;
import com.yupi.mcp.mcpserver.common.ErrorCode;
import com.yupi.mcp.mcpserver.dto.ArticlePublishCnblogRequest;
import com.yupi.mcp.mcpserver.dto.ArticlePublishCnblogResponse;
import com.yupi.mcp.mcpserver.dto.ArticlePublishCsdnRequest;
import com.yupi.mcp.mcpserver.dto.ArticlePublishCsdnResponse;
import com.yupi.mcp.mcpserver.dto.ArticlePublishRequest;
import com.yupi.mcp.mcpserver.dto.ArticlePublishResponse;
import com.yupi.mcp.mcpserver.exception.APIException;
import com.yupi.mcp.mcpserver.properties.WebAuthProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 博客园发布文章服务
 *
 * @author gulihua
 * @date 2025-04-11 16:24
 */
@Service("articleCnblogService")
@Slf4j
public class ArticleCnblogServiceImpl implements ArticleService {
    private final String BASE_URL = "https://i.cnblogs.com/api/posts";
    private final String LINK = "https://www.cnblogs.com/gulihua/articles/%s";

    @Autowired
    private WebAuthProperties webAuthProperties;

    @Override
    public ArticlePublishResponse publishArticle(ArticlePublishRequest articlePublishRequest) {
        ArticlePublishCnblogRequest articlePublishCnblogRequest = new ArticlePublishCnblogRequest();
        articlePublishCnblogRequest.setTitle(articlePublishRequest.getTitle());
        articlePublishCnblogRequest.setPostBody(articlePublishRequest.getContent());
        articlePublishCnblogRequest.setPostType(2);
        articlePublishCnblogRequest.setAccessPermission(0);
        articlePublishCnblogRequest.setInSiteCandidate(false);
        articlePublishCnblogRequest.setInSiteHome(false);
        articlePublishCnblogRequest.setIsPublished(true);
        articlePublishCnblogRequest.setDisplayOnHomePage(true);
        articlePublishCnblogRequest.setIsAllowComments(true);
        articlePublishCnblogRequest.setIncludeInMainSyndication(true);
        articlePublishCnblogRequest.setIsPinned(true);
        articlePublishCnblogRequest.setShowBodyWhenPinned(false);
        articlePublishCnblogRequest.setIsOnlyForRegisterUser(false);
        articlePublishCnblogRequest.setIsUpdateDateAdded(true);
        articlePublishCnblogRequest.setIsMarkdown(true);
        articlePublishCnblogRequest.setIsDraft(true);
        articlePublishCnblogRequest.setChangePostType(false);
        articlePublishCnblogRequest.setRemoveScript(false);
        articlePublishCnblogRequest.setChangeCreatedTime(false);
        articlePublishCnblogRequest.setChangeCreatedTime(false);
        articlePublishCnblogRequest.setCanChangeCreatedTime(false);
        articlePublishCnblogRequest.setIsContributeToImpressiveBugActivity(false);
        articlePublishCnblogRequest.setUsingEditorId(5);
        articlePublishCnblogRequest.setTags(List.of("后端"));
        ArticlePublishResponse articlePublishResponse = new ArticlePublishResponse();
        articlePublishResponse.setIsSuccess(false);
        String result = null;
        try {
            log.info("call 博客园[publishArticle], cookie = {}", webAuthProperties.getCnblogCookie());
            log.info("call csdn[publishArticle], token = {}", webAuthProperties.getCnblogToken());
            Map<String, String> headerMap = new HashMap<>();
            headerMap.put("Cookie", webAuthProperties.getCnblogCookie());
            headerMap.put("x-xsrf-token", webAuthProperties.getCnblogToken());
            headerMap.put("accept", "application/json, text/plain, */*");
            headerMap.put("content-type", "application/json;");
            HttpResponse response = HttpRequest.post(BASE_URL)
                    .addHeaders(headerMap)
                    .body(JSONUtil.toJsonStr(articlePublishCnblogRequest))
                    .timeout(3000)
                    .execute();
            result = response.body();
            log.info("call 博客园[publishArticle], result = {}", result);
            if (response.getStatus() != 200) {
                throw new APIException(ErrorCode.API_ERROR, String.format("调用博客园发布文章接口失败，响应码：%s", response.getStatus()));
            }

            ArticlePublishCnblogResponse resp = JSONUtil.toBean(result, ArticlePublishCnblogResponse.class);
            List<String> errs = resp.getErrors();
            if (CollUtil.isEmpty(errs)) {
                articlePublishResponse.setId(resp.getId().toString());
                articlePublishResponse.setIsSuccess(true);
                articlePublishResponse.setLink(String.format(LINK, resp.getId()));
            } else {
                log.error("call 博客园[publishArticle] failed, message = {}", errs);
                throw new APIException(ErrorCode.API_ERROR, String.format("调用博客园发布文章接口失败，响应信息：%s", errs));

            }
        } catch (Exception e) {
            log.error("call 博客园[publishArticle] failed, e:\n", e);
            throw new APIException(ErrorCode.API_ERROR, String.format("调用博客园发布文章接口失败，异常信息：%s", e.getMessage()));

        }
        return articlePublishResponse;
    }
}
