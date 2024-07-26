package com.user.zeus.config.property;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "zeus")
public class UserProperties {

    private String usersAllUrl;
}
