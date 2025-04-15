package com.yupi.mcp.mcpserver.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "web.auth")
@Data
@Component
public class WebAuthProperties {
    private String csdnCookie;
    private String juejinCookie;
    private String cnblogCookie;
    private String cnblogToken;
}
