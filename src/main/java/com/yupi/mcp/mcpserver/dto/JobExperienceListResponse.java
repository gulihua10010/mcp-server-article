package com.yupi.mcp.mcpserver.dto;

import java.io.Serializable;
import java.util.List;

/**
 * @author gulihua
 * @Description
 * @date 2024-11-19 20:36
 */
@lombok.Data
public class JobExperienceListResponse implements Serializable {
    private static final long serialVersionUID = -5304055362317804714L;

    private Boolean success;
    private Integer code;
    private String msg;
    private Data data;


    @lombok.Data
    public class Data {

        private int current;
        private int size;
        private int total;
        private int totalPage;
        private List<Records> records;

    }

    @lombok.Data
    public class Records {

        private String contentId;
        private int contentType;
        private MomentData momentData;
        private List<String> subjectData;
        private String internalRecommend;
        private ContentData contentData;

    }

    @lombok.Data
    public class ContentData {

        private String id;
        private String uuid;
        private String authorId;
        private String title;
        private String newTitle;
        private String richText;
        private String content;
        private String newContent;
        private String typeName;
        private Boolean beMyOnly;
        private List<String> contentImageUrls;
        private String isTop;
        private Boolean hot;
        private Boolean isGilded;
        private Boolean isReward;
        private Integer reward;
        private Boolean hasOfferCompareTag;
        private Boolean staffAnswer;
        private Boolean withAnonymousOffer;
        private Boolean isAnonymousFlag;
        private String isWithAcceptFlag;
        private Integer postCertify;
        private Long entityId;
        private Integer entityType;
        private String newReferral;
        private Long createTime;
        private Long editTime;
        private Boolean recommendAd;
        private Boolean edited;
        private Long showTime;

    }
    @lombok.Data
    public class MomentData {

        private String ip4;
        private String ip4Location;
        private Long id;
        private String uuid;
        private Long userId;
        private String title;
        private String newTitle;
        private String content;
        private String newContent;
        private Integer type;
        private Integer status;
        private Boolean hasEdit;
        private Boolean isAnonymousFlag;
        private Boolean beMyOnly;
        private String linkMoment;
        private String imgMoment;
        private String clockMoment;
        private String videoMoment;
        private Long createdAt;
        private String circle;
        private Long editTime;
        private Boolean edited;
        private Long showTime;

    }

}
