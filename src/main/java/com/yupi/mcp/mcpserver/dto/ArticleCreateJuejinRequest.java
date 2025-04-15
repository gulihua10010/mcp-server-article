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
public class ArticleCreateJuejinRequest implements Serializable {

    @Alias("category_id")
    private String categoryId;
    @Alias("tag_ids")
    private List<String> tagIds;
    @Alias("link_url")
    private String linkUrl;
    @Alias("cover_image")
    private String coverImage;
    private String title;
    @Alias("brief_content")
    private String briefContent;
    @Alias("edit_type")
    private Integer editType;
    @Alias("html_content")
    private String htmlContent;
    @Alias("mark_content")
    private String markContent;
    @Alias("theme_ids")
    private List<String> themeIds;
    private List<String> pics;
}
