package com.yupi.mcp.mcpserver.dto;

/**
 * @author gulihua
 * @Description
 * @date 2024-11-20 10:38
 */
@lombok.Data
public class MomentDetailResponse {

    private boolean success;
    private int code;
    private String msg;
    private Data data;

    @lombok.Data
    public class Data {

        private String ip4;
        private String ip4Location;
        private Long id;
        private Long entityId;
        private Integer entityType;
        private String uuid;
        private Long userId;
        private String title;
        private String content;
        private Integer type;
        private String linkMoment;
        private String clockMoment;
        private String videoMoment;
        private Long createdAt;
        private Integer voteId;
        private String circle;
        private Long editTime;
        private String clientSystem;
        private String subjectData;
        private String jobIds;
        private Integer careerType;
        private Boolean hasEdit;
        private Boolean allowAnonymous;
        private Boolean checkSalary;
        private Boolean edited;
        private Long showTime;
        private String internalRecommend;

    }
}
