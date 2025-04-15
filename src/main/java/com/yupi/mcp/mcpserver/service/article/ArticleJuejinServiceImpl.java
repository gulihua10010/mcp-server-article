package com.yupi.mcp.mcpserver.service.article;

import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.json.JSONUtil;
import com.yupi.mcp.mcpserver.common.ErrorCode;
import com.yupi.mcp.mcpserver.dto.ArticleCreateJuejinRequest;
import com.yupi.mcp.mcpserver.dto.ArticleCreateJuejinResponse;
import com.yupi.mcp.mcpserver.dto.ArticlePublishCsdnRequest;
import com.yupi.mcp.mcpserver.dto.ArticlePublishCsdnResponse;
import com.yupi.mcp.mcpserver.dto.ArticlePublishJuejinRequest;
import com.yupi.mcp.mcpserver.dto.ArticlePublishJuejinResponse;
import com.yupi.mcp.mcpserver.dto.ArticlePublishRequest;
import com.yupi.mcp.mcpserver.dto.ArticlePublishResponse;
import com.yupi.mcp.mcpserver.exception.APIException;
import com.yupi.mcp.mcpserver.properties.WebAuthProperties;
import com.yupi.mcp.mcpserver.util.TextTransformUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 掘金发布文章服务
 *
 * @author gulihua
 * @date 2025-04-11 16:24
 */
@Service("articleJuejinService")
@Slf4j
public class ArticleJuejinServiceImpl implements ArticleService {
    private final String CREATE_URL = "https://api.juejin.cn/content_api/v1/article_draft/create";
    private final String PUBLISH_URL = "https://api.juejin.cn/content_api/v1/article/publish?aid=2608&uuid=7355741656475977213";
    private final String LINK = "https://juejin.cn/post/%s";

    @Autowired
    private WebAuthProperties webAuthProperties;

    @Override
    public ArticlePublishResponse publishArticle(ArticlePublishRequest articlePublishRequest) {
        ArticleCreateJuejinResponse articleCreateJuejinResponse = createArticle(articlePublishRequest);

        log.info("call 掘金[publishArticle], id = {}", articleCreateJuejinResponse.getData().getId());
        ArticlePublishJuejinRequest articlePublishJuejinRequest = new ArticlePublishJuejinRequest();
        articlePublishJuejinRequest.setDraftId(articleCreateJuejinResponse.getData().getId());
        articlePublishJuejinRequest.setSyncToOrg(false);
        articlePublishJuejinRequest.setColumnIds(new ArrayList<>());
        articlePublishJuejinRequest.setThemeIds(new ArrayList<>());
        ArticlePublishResponse articlePublishResponse = new ArticlePublishResponse();
        articlePublishResponse.setIsSuccess(false);
        String result = null;
        try {
            Map<String, String> headerMap = new HashMap<>();
            headerMap.put("Cookie", webAuthProperties.getJuejinCookie());
            headerMap.put("accept", "*/*");
            headerMap.put("content-type", "application/json;");
            HttpResponse response = HttpRequest.post(PUBLISH_URL)
                    .addHeaders(headerMap)
                    .body(JSONUtil.toJsonStr(articlePublishJuejinRequest))
                    .timeout(3000)
                    .execute();
            result = response.body();
            log.info("call 掘金[publishArticle], result = {}", result);
            if (response.getStatus() != 200) {
                throw new APIException(ErrorCode.API_ERROR, String.format("调用掘金发布文章接口失败，响应码：%s", response.getStatus()));
            }

            ArticlePublishJuejinResponse resp = JSONUtil.toBean(result, ArticlePublishJuejinResponse.class);
            int code = resp.getErrNo();
            if (code == 0) {
                articlePublishResponse.setId(resp.getData().getArticleId());
                articlePublishResponse.setLink(String.format(LINK, resp.getData().getArticleId()));
                articlePublishResponse.setIsSuccess(true);
            } else {
                log.error("call 掘金[publishArticle] failed, code = {}, message = {}", code, resp.getErrMsg());
                throw new APIException(ErrorCode.API_ERROR, String.format("调用掘金发布文章接口失败，响应信息：%s", resp.getErrMsg()));

            }
        } catch (Exception e) {
            log.error("call 掘金[publishArticle] failed, e:\n", e);
            throw new APIException(ErrorCode.API_ERROR, String.format("调用掘金发布文章接口失败，异常信息：%s", e.getMessage()));

        }
        return articlePublishResponse;
    }

    private ArticleCreateJuejinResponse createArticle(ArticlePublishRequest articlePublishRequest) {
        ArticleCreateJuejinRequest articleCreateJuejinRequest = new ArticleCreateJuejinRequest();
        articleCreateJuejinRequest.setTitle(articlePublishRequest.getTitle());
        String desc = articlePublishRequest.getDescription();
        if (StrUtil.isBlank(desc) && desc.length() < 50) {
            desc = TextTransformUtils.markdownToTextCustom(articlePublishRequest.getContent());
        }
        if (desc.length() > 100) {
            desc = desc.substring(0, 100);
        }
        articleCreateJuejinRequest.setBriefContent(desc);
        articleCreateJuejinRequest.setMarkContent(articlePublishRequest.getContent());
        articleCreateJuejinRequest.setEditType(10);
        articleCreateJuejinRequest.setHtmlContent("deprecated");
        articleCreateJuejinRequest.setTagIds(List.of("6809640408797167623"));//后端标签
        articleCreateJuejinRequest.setCategoryId("6809637769959178254");//后端分类

        String result = null;
        try {
            Map<String, String> headerMap = new HashMap<>();
            headerMap.put("Cookie", webAuthProperties.getJuejinCookie());
            headerMap.put("accept", "*/*");
            headerMap.put("content-type", "application/json;");
            HttpResponse response = HttpRequest.post(CREATE_URL)
                    .addHeaders(headerMap)
                    .body(JSONUtil.toJsonStr(articleCreateJuejinRequest)).timeout(3000)
                    .execute();
            result = response.body();
            log.info("call 掘金[createArticle], result = {}", result);
            if (response.getStatus() != 200) {
                throw new APIException(ErrorCode.API_ERROR, String.format("调用掘金创建文章接口失败，响应码：%s", response.getStatus()));
            }

            ArticleCreateJuejinResponse resp = JSONUtil.toBean(result, ArticleCreateJuejinResponse.class);
            int code = resp.getErrNo();
            if (code == 0) {
                return resp;
            } else {
                log.error("call juejin[createArticle] failed, code = {}, message = {}", code, resp.getErrMsg());
                throw new APIException(ErrorCode.API_ERROR, String.format("调用掘金创建文章接口失败，响应信息：%s", resp.getErrMsg()));

            }
        } catch (Exception e) {
            log.error("call juejin[createArticle] failed, e:\n", e);
            throw new APIException(ErrorCode.API_ERROR, String.format("调用掘金创建文章接口失败，异常信息：%s", e.getMessage()));

        }
    }
}
