package com.yupi.mcp.mcpserver.service.article;

import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.json.JSONUtil;
import com.yupi.mcp.mcpserver.common.ErrorCode;
import com.yupi.mcp.mcpserver.dto.ArticlePublishCsdnRequest;
import com.yupi.mcp.mcpserver.dto.ArticlePublishCsdnResponse;
import com.yupi.mcp.mcpserver.dto.ArticlePublishRequest;
import com.yupi.mcp.mcpserver.dto.ArticlePublishResponse;
import com.yupi.mcp.mcpserver.exception.APIException;
import com.yupi.mcp.mcpserver.properties.WebAuthProperties;
import com.yupi.mcp.mcpserver.util.TextTransformUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * csdn发布文章服务
 *
 * @author gulihua
 * @date 2025-04-11 16:24
 */
@Service("articleCsdnService")
@Slf4j
public class ArticleCsdnServiceImpl implements ArticleService {
    private final String BASE_URL = "https://bizapi.csdn.net/blog-console-api/v3/mdeditor/saveArticle";

    @Autowired
    private WebAuthProperties webAuthProperties;

    @Override
    public ArticlePublishResponse publishArticle(ArticlePublishRequest articlePublishRequest) {
        ArticlePublishCsdnRequest articlePublishCsdnRequest = new ArticlePublishCsdnRequest();
        articlePublishCsdnRequest.setTitle(articlePublishRequest.getTitle());
        String desc = articlePublishRequest.getDescription();
        if (StrUtil.isBlank(desc) && desc.length() < 50) {
            desc = TextTransformUtils.markdownToTextCustom(articlePublishRequest.getContent());
        }
        if (desc.length() > 100) {
            desc = desc.substring(0, 100);
        }
        articlePublishCsdnRequest.setDescription(desc);
        articlePublishCsdnRequest.setMarkdowncontent(articlePublishRequest.getContent());
        articlePublishCsdnRequest.setContent(TextTransformUtils.markdownToHtml(articlePublishRequest.getContent()));
        articlePublishCsdnRequest.setTags("后端");
        articlePublishCsdnRequest.setReadType("public");
        articlePublishCsdnRequest.setType("original");
        articlePublishCsdnRequest.setSource("pc_mdeditor");
        articlePublishCsdnRequest.setNotAutoSaved("1");
        articlePublishCsdnRequest.setCoverType(1);
        articlePublishCsdnRequest.setIsNew(1);
        articlePublishCsdnRequest.setStatus(0);
        articlePublishCsdnRequest.setLevel(0);
        articlePublishCsdnRequest.setCategories("");
        articlePublishCsdnRequest.setOriginalLink("");
        articlePublishCsdnRequest.setResourceId("");
        articlePublishCsdnRequest.setVoteId(0);
        articlePublishCsdnRequest.setSyncGitCode(0);
        articlePublishCsdnRequest.setCoverImages(new ArrayList<>());
        articlePublishCsdnRequest.setAuthorizedStatus(false);
        ArticlePublishResponse articlePublishResponse = new ArticlePublishResponse();
        articlePublishResponse.setIsSuccess(false);
        String result = null;
        try {
            Map<String, String> headerMap = new HashMap<>();
            log.info("call csdn[publishArticle], cookie = {}", webAuthProperties.getCsdnCookie());
            headerMap.put("Cookie", webAuthProperties.getCsdnCookie());
            headerMap.put("x-ca-key", "203803574");
            headerMap.put("x-ca-nonce", "3bc851e3-6d65-4560-81a4-2e7917e34d63");
            headerMap.put("x-ca-signature", "DrtwrmXOsrcA3fm4RKmcSBpX0fmugoDJcUFpHXHCDy0=");
            headerMap.put("x-ca-signature-headers", "x-ca-key,x-ca-nonce");
            headerMap.put("content-type", "application/json");
            headerMap.put("accept", "*/*");
            HttpResponse response = HttpRequest.post(BASE_URL)
                    .addHeaders(headerMap)
                    .body(JSONUtil.toJsonStr(articlePublishCsdnRequest))
                    .timeout(3000)
                    .execute();
            result = response.body();
            log.info("call csdn[publishArticle], result = {}", result);
            ArticlePublishCsdnResponse resp = JSONUtil.toBean(result, ArticlePublishCsdnResponse.class);
            if (response.getStatus() != 200) {
                String msg = resp.getMsg();
                if (StrUtil.isBlank(msg)) {
                    msg = resp.getMessage();
                }
                throw new APIException(ErrorCode.API_ERROR, String.format("调用CSDN发布文章接口失败，响应码：%s，错误信息:%s", response.getStatus(), msg));
            }

            int code = resp.getCode();
            if (code == 200) {
                ArticlePublishCsdnResponse.Data data = JSONUtil.toBean(JSONUtil.toJsonStr(resp.getData()), ArticlePublishCsdnResponse.Data.class);
                articlePublishResponse.setId(data.getId().toString());
                articlePublishResponse.setLink(data.getUrl());
                articlePublishResponse.setIsSuccess(true);
            } else {
                log.error("call csdn[publishArticle] failed, code = {}, message = {}", code, resp.getMessage());
                throw new APIException(ErrorCode.API_ERROR, String.format("调用CSDN发布文章接口失败，响应信息：%s", resp.getMessage()));

            }
        } catch (Exception e) {
            log.error("call csdn[publishArticle] failed, e:\n", e);
            throw new APIException(ErrorCode.API_ERROR, String.format("调用CSDN发布文章接口失败，异常信息：%s", e.getMessage()));

        }
        return articlePublishResponse;
    }
}
