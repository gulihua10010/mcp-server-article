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
public class ArticlePublishCsdnRequest implements Serializable {

    private String title;
    private String markdowncontent;
    private String content;
    private String readType;
    private int level;
    private String tags;
    private int status;
    private String categories;
    private String type;
    @Alias("original_link")
    private String originalLink;
    @Alias("authorized_status")
    private boolean authorizedStatus;
    @Alias("Description")
    private String description;
    @Alias("not_auto_saved")
    private String notAutoSaved;
    private String source;
    @Alias("cover_images")
    private List<String> coverImages;
    @Alias("cover_type")
    private int coverType;
    @Alias("is_new")
    private int isNew;
    @Alias("vote_id")
    private int voteId;
    @Alias("resource_id")
    private String resourceId;
    private String pubStatus;
    @Alias("sync_git_code")
    private int syncGitCode;
}
