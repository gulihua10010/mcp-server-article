package com.yupi.mcp.mcpserver.dto;

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
public class ArticlePublishCnblogResponse implements Serializable {

    private Long id;
    private String title;
    private List<String> errors;
    private String url;
    private String blogUrl;
    private Integer postType;
    private Date dateAdded;
    private String entryName;
    private List<String> tags;
    private String isVip;
}
