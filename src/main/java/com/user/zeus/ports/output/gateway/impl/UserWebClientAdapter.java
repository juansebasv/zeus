package com.user.zeus.ports.output.gateway.impl;

import com.user.zeus.config.property.UserProperties;
import com.user.zeus.core.domain.UserDO;
import com.user.zeus.ports.output.gateway.UserPort;
import com.user.zeus.ports.output.gateway.mapper.UserWebClientMapper;
import com.user.zeus.ports.output.gateway.response.GetUserResponse;
import io.micrometer.observation.annotation.Observed;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@Observed
public class UserWebClientAdapter implements UserPort {

    private final UserProperties userProperties;
    private final WebClient webClient;
    private final UserWebClientMapper userWebClientMapper;

    public UserWebClientAdapter(UserProperties userProperties, WebClient.Builder webClientBuilder, UserWebClientMapper userWebClientMapper) {
        this.userProperties = userProperties;
        this.webClient = webClientBuilder.build();
        this.userWebClientMapper = userWebClientMapper;
    }

    @Override
    public List<UserDO> getAllUsers() {
        log.info("Getting All Users from API");
        var listUsersResponse = doRequest();
        return listUsersResponse
                .stream()
                .map(userWebClientMapper::buildUserDO)
                .collect(Collectors.toList());
    }

    @Override
    public List<UserDO> getAllUsersById(String userId) {
        log.info("Getting All Users By ID from API");
        var listUsersResponse = doRequestById(userId);
        return listUsersResponse
                .stream()
                .map(userWebClientMapper::buildUserDO)
                .collect(Collectors.toList());
    }

    private List<GetUserResponse> doRequest() {
        log.info("Calling API to get all users");
        String uri = UriComponentsBuilder.fromHttpUrl(userProperties.getUsersAllUrl()).toUriString();

        return webClient.get()
                .uri(uri)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<GetUserResponse>>() {
                })
                .onErrorMap(ex -> {
                    log.error("Error getting all users form API");
                    log.error("Exception message {}", ex.getMessage());
                    return new Exception("Error Getting User", ex);
                })
                .block();
    }

    private List<GetUserResponse> doRequestById(String userId) {
        log.info("Calling API to get all users by ID");
        String uri = UriComponentsBuilder.fromHttpUrl(userProperties.getUsersAllUrl()).toUriString();

        return webClient.get()
                .uri(uri)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<GetUserResponse>>() {
                })
                .onErrorMap(ex -> {
                    log.error("Error getting all users form API");
                    log.error("Exception message {}", ex.getMessage());
                    return new Exception("Error Getting User", ex);
                })
                .block();
    }
}