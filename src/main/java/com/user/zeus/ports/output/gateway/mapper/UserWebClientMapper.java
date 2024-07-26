package com.user.zeus.ports.output.gateway.mapper;

import com.user.zeus.core.domain.UserDO;
import com.user.zeus.ports.output.gateway.response.GetUserResponse;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, injectionStrategy = InjectionStrategy.CONSTRUCTOR
)
public interface UserWebClientMapper {

    /**
     * Maps a {@link GetUserResponse} to a {@link UserDO} object.
     *
     * @param getUserResponse The response containing user data.
     * @return A {@link UserDO} object representing the mapped user data.
     */
    UserDO buildUserDO(GetUserResponse getUserResponse);
}
