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
public class ArticlePublishJuejinRequest implements Serializable {

    @Alias("draft_id")
    private String draftId;
    @Alias("sync_to_org")
    private Boolean syncToOrg;
    @Alias("column_ids")
    private List<String> columnIds;
    @Alias("theme_ids")
    private List<String> themeIds;
    @Alias("encrypted_word_count")
    private Long encryptedWordCount;
    @Alias("origin_word_count")
    private Integer originWordCount;
}
