package com.user.zeus.ports.input.mapper;

import com.user.zeus.core.domain.UserDO;
import com.user.zeus.ports.input.response.UserResponse;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-07-26T16:50:26-0500",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.11 (Amazon.com Inc.)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UserResponse requestToDomain(UserDO userDO) {
        if ( userDO == null ) {
            return null;
        }

        UserResponse userResponse = new UserResponse();

        userResponse.setId( userDO.getId() );
        userResponse.setName( userDO.getName() );
        userResponse.setCompany( userDO.getCompany() );
        userResponse.setUsername( userDO.getUsername() );
        userResponse.setEmail( userDO.getEmail() );
        userResponse.setAddress( userDO.getAddress() );
        userResponse.setZip( userDO.getZip() );
        userResponse.setState( userDO.getState() );
        userResponse.setCountry( userDO.getCountry() );
        userResponse.setPhone( userDO.getPhone() );
        userResponse.setPhoto( userDO.getPhoto() );

        return userResponse;
    }
}
