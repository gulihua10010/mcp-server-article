package com.yupi.mcp.mcpserver.dto;

import cn.hutool.core.annotation.Alias;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author gulihua
 * @Description
 * @date 2025-04-10 20:40
 */
@Data
public class ArticlePublishJuejinResponse implements Serializable {

    @Alias("err_no")
    private Integer errNo;
    @Alias("err_msg")
    private String errMsg;
    private Data data;


    @lombok.Data
    public class Data {

        @Alias("article_id")
        private String articleId;
        @Alias("user_id")
        private String userId;
        @Alias("category_id")
        private String categoryId;
        @Alias("tag_ids")
        private String tagIds;
        @Alias("visible_level")
        private Integer visibleLevel;
        @Alias("link_url")
        private String linkUrl;
        @Alias("cover_image")
        private String coverImage;
        @Alias("is_gfw")
        private Integer isGfw;
        private String title;
        @Alias("brief_content")
        private String briefContent;
        @Alias("is_english")
        private Integer isEnglish;
        @Alias("is_original")
        private Integer isOriginal;
        @Alias("user_index")
        private Integer userIndex;
        @Alias("original_type")
        private Integer originalType;
        @Alias("original_author")
        private String originalAuthor;
        private String content;
        private String ctime;
        private String mtime;
        private String rtime;
        private Integer status;
        @Alias("verify_status")
        private Integer verifyStatus;
        @Alias("audit_status")
        private Integer auditStatus;
        @Alias("mark_content")
        private String markContent;
        @Alias("org_id")
        private String orgId;
        @Alias("homepage_top_time")
        private Integer homepageTopTime;
        @Alias("homepage_top_status")
        private Integer homepageTopStatus;

    }
}
