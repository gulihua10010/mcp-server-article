package com.yupi.mcp.mcpserver.dto;

import lombok.Data;

import java.util.Date;
import java.util.Objects;

/**
 * @author gulihua
 * @Description
 * @date 2024-11-20 10:38
 */
@Data
public class NowCoderDTO {

   private String id;
   private String title;
   private String content;
   private String url;
   private Date createTime;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof NowCoderDTO)) return false;
        NowCoderDTO that = (NowCoderDTO) o;
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }
}
