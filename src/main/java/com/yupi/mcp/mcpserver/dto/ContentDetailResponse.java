package com.yupi.mcp.mcpserver.dto;

import java.util.List;

/**
 * @author gulihua
 * @Description
 * @date 2024-11-20 10:38
 */
@lombok.Data
public class ContentDetailResponse {

    private boolean success;
    private int code;
    private String msg;
    private Data data;

    @lombok.Data
    public class Data {
        private String ip4;
        private String ip4Location;
        private String id;
        private String uuid;
        private Long authorId;
        private String title;
        private String richText;
        private String content;
        private Boolean hot;
        private Boolean isGilded;
        private Boolean isReward;
        private Integer reward;
        private Boolean staffAnswer;
        private Boolean withAnonymousOffer;
        private Boolean hasOfferCompareTag;
        private Boolean isAnonymousFlag;
        private String isWithAcceptFlag;
        private Integer postCertify;
        private Long entityId;
        private Integer entityType;
        private Boolean newReferral;
        private String clientSystem;
        private Boolean commentDisable;
        private Boolean hasLook;
        private Long createTime;
        private Long editTime;
        private Boolean showInvite;
        private String jobIds;
        private String careerType;
        private Boolean isNewEdior;
        private Boolean allowAnonymous;
        private Boolean checkSalary;
        private Boolean edited;
        private Long showTime;
        private List<String> quickReviews;

    }
}
