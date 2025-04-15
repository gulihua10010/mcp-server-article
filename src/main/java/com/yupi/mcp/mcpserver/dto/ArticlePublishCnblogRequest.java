package com.yupi.mcp.mcpserver.dto;

import cn.hutool.core.annotation.Alias;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author gulihua
 * @Description
 * @date 2025-04-10 20:40
 */
@Data
public class ArticlePublishCnblogRequest implements Serializable {

    private String id;
    private Integer postType;
    private Integer accessPermission;
    private String title;
    private String url;
    private String postBody;
    private String categoryIds;
    private String categories;
    private String categoryTree;
    private List<String> collectionIds;
    private Boolean inSiteCandidate;
    private Boolean inSiteHome;
    private String siteCategoryId;
    private String blogTeamIds;
    private Boolean isPublished;
    private Boolean displayOnHomePage;
    private Boolean isAllowComments;
    private Boolean includeInMainSyndication;
    private Boolean isPinned;
    private Boolean showBodyWhenPinned;
    private Boolean isOnlyForRegisterUser;
    private Boolean isUpdateDateAdded;
    private String entryName;
    private String description;
    private String featuredImage;
    private List<String> tags;
    private String password;
    private String publishAt;
    private Date datePublished;
    private String dateUpdated;
    private Boolean isMarkdown;
    private Boolean isDraft;
    private String autoDesc;
    private Boolean changePostType;
    private Integer blogId;
    private String author;
    private Boolean removeScript;
    private String clientInfo;
    private Boolean changeCreatedTime;
    private Boolean canChangeCreatedTime;
    private Boolean isContributeToImpressiveBugActivity;
    private Integer usingEditorId;
    private String sourceUrl;
}
