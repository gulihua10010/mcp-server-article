package com.yupi.mcp.mcpserver.service.article;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.XmlUtil;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import com.rometools.rome.feed.synd.SyndContent;
import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.SyndFeedInput;
import com.yupi.mcp.mcpserver.common.ErrorCode;
import com.yupi.mcp.mcpserver.dto.NewsDTO;
import com.yupi.mcp.mcpserver.exception.APIException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;

/**
 * 查询热点资讯
 *
 * @author yupi
 */
@Component
@Slf4j
public class QueryNewsService {
    private static final String RSS_URL = "https://feed.cnblogs.com/news/rss";

    public NewsDTO getNews() {
        try (HttpResponse response = HttpUtil.createGet(RSS_URL, true).timeout(10000).executeAsync()) {
            Document document = XmlUtil.readXML(response.bodyStream());
            SyndFeed feed = new SyndFeedInput().build(document);
            int index = RandomUtil.randomInt(0, feed.getEntries().size());
            SyndEntry entry = feed.getEntries().get(index);
            SyndContent content = entry.getDescription();
            NewsDTO newsDTO = new NewsDTO();
            if (content != null) {
                newsDTO.setTitle(entry.getTitle());
                newsDTO.setContent(content.getValue());
                return newsDTO;
            }
        } catch (Exception e) {
            log.error("Sync News {} exe error: ", RSS_URL, e);
            throw new APIException(ErrorCode.API_ERROR, String.format("调用获取资讯失败，异常信息：%s", e.getMessage()));
        }
        throw new APIException(ErrorCode.API_ERROR, "获取不到资讯");

    }
}
