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
public class ArticleCreateJuejinResponse implements Serializable {

    @Alias("err_no")
    private Integer errNo;
    @Alias("err_msg")
    private String errMsg;
    private Data data;


    @lombok.Data
    public class Data {


        private String id;
        @Alias("article_id")
        private String articleId;
        @Alias("user_id")
        private String userId;
        @Alias("category_id")
        private String categoryId;
        @Alias("tag_ids")
        private List<String> tagIds;
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
        @Alias("edit_type")
        private Integer editType;
        @Alias("html_content")
        private String htmlContent;
        @Alias("mark_content")
        private String markContent;
        private String ctime;
        private String mtime;
        private Integer status;
        @Alias("original_type")
        private Integer originalType;
        @Alias("theme_ids")
        private List<String> themeIds;

    }
}
