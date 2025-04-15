package com.yupi.mcp.mcpserver.service.article;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.yupi.mcp.mcpserver.common.ErrorCode;
import com.yupi.mcp.mcpserver.dto.ContentDetailResponse;
import com.yupi.mcp.mcpserver.dto.JobExperienceListResponse;
import com.yupi.mcp.mcpserver.dto.MomentDetailResponse;
import com.yupi.mcp.mcpserver.dto.NowCoderDTO;
import com.yupi.mcp.mcpserver.exception.APIException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;


/**
 * 牛客抓取面经 服务
 *
 * @author pine
 */
@Component
@Slf4j
public class NowCoderService {


    private static final String NOW_CODER_JOB_EXPERIENCE_LIST_URL = "https://gw-c.nowcoder.com/api/sparta/job-experience/experience/job/list";
    private static final String NOW_CODER_JOB_EXPERIENCE_CONTENT_DETAIL_URL = "https://gw-c.nowcoder.com/api/sparta/detail/content-data/detail/%s";
    private static final String NOW_CODER_JOB_EXPERIENCE_MOMENT_DETAIL_URL = "https://gw-c.nowcoder.com/api/sparta/detail/moment-data/detail/%s";


    private static final Integer CONTENT_TYPE = 250;
    private static final Integer MOMENT_TYPE = 74;


    public NowCoderDTO crawlExperienceQuestion() {
        int page = RandomUtil.randomInt(1, 21);

        JSONObject param = new JSONObject();
        param.set("jobId", 11002);
        param.set("companyList", Arrays.asList(134, 151, 665, 732));
        param.set("order", 3);
        param.set("level", 3);
        param.set("isNewJob", true);
        param.set("page", page);
        String responseStr = HttpUtil.post(NOW_CODER_JOB_EXPERIENCE_LIST_URL, param.toString());
        JobExperienceListResponse response = JSONUtil.toBean(responseStr, JobExperienceListResponse.class);
        if (response.getCode() != 0) {
            log.error("query url [{}] failed, page:[{}], response :{}", NOW_CODER_JOB_EXPERIENCE_LIST_URL, 1, responseStr);
            throw new APIException(ErrorCode.API_ERROR, String.format("调用获取面经接口失败，响应码：%s", response.getCode()));
        }
        if (response.getData().getTotal() == 0) {
            throw new APIException(ErrorCode.API_ERROR, "调用获取面经接口失败，响应列表为空");
        }
        List<JobExperienceListResponse.Records> records = response.getData().getRecords();
        int index = RandomUtil.randomInt(0, records.size());
        JobExperienceListResponse.Records record = records.get(index);
        if (CONTENT_TYPE == record.getContentType()) {
            return queryContentDetail(record.getContentId());
        } else if (MOMENT_TYPE == record.getContentType()) {
            return queryMomentDetail(record.getMomentData().getUuid());
        } else {
            throw new APIException(ErrorCode.API_ERROR, "调用获取面经接口失败，内容类型非法");
        }

    }

    private NowCoderDTO queryContentDetail(String id) {

        String url = String.format(NOW_CODER_JOB_EXPERIENCE_CONTENT_DETAIL_URL, id);
        String responseStr = HttpUtil.get(url);
        ContentDetailResponse response = JSONUtil.toBean(responseStr, ContentDetailResponse.class);
        if (response.getCode() != 0) {
            log.error("query url [{}] failed, response :{}", url, responseStr);
            return null;
        }
        NowCoderDTO nowCoderDTO = new NowCoderDTO();
        nowCoderDTO.setUrl(url);
        nowCoderDTO.setId(id);
        nowCoderDTO.setTitle(response.getData().getTitle());
        nowCoderDTO.setContent(response.getData().getContent());
        nowCoderDTO.setCreateTime(DateUtil.date(response.getData().getCreateTime()));
        return nowCoderDTO;
    }

    private NowCoderDTO queryMomentDetail(String uuid) {

        String url = String.format(NOW_CODER_JOB_EXPERIENCE_MOMENT_DETAIL_URL, uuid);
        String responseStr = HttpUtil.get(url);
        MomentDetailResponse response = JSONUtil.toBean(responseStr, MomentDetailResponse.class);
        if (response.getCode() != 0) {
            log.error("query url [{}] failed, response :{}", url, responseStr);
            return null;
        }
        NowCoderDTO nowCoderDTO = new NowCoderDTO();
        nowCoderDTO.setUrl(url);
        nowCoderDTO.setId(uuid);
        nowCoderDTO.setTitle(response.getData().getTitle());
        nowCoderDTO.setContent(response.getData().getContent());
        nowCoderDTO.setCreateTime(DateUtil.date(response.getData().getCreatedAt()));
        return nowCoderDTO;
    }

}
