package com.user.zeus.ports.input.mapper;

import com.user.zeus.core.domain.UserDO;
import com.user.zeus.ports.input.response.UserResponse;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;


@Mapper(
        componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, injectionStrategy = InjectionStrategy.CONSTRUCTOR
)
public interface UserMapper {

    UserResponse requestToDomain(UserDO userDO);
}
