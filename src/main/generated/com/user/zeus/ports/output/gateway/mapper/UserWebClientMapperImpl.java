package com.user.zeus.ports.output.gateway.mapper;

import com.user.zeus.core.domain.UserDO;
import com.user.zeus.ports.output.gateway.response.GetUserResponse;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-07-25T11:47:40-0500",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.11 (Amazon.com Inc.)"
)
@Component
public class UserWebClientMapperImpl implements UserWebClientMapper {

    @Override
    public UserDO buildUserDO(GetUserResponse getUserResponse) {
        if ( getUserResponse == null ) {
            return null;
        }

        UserDO userDO = new UserDO();

        userDO.setId( getUserResponse.getId() );
        userDO.setName( getUserResponse.getName() );
        userDO.setCompany( getUserResponse.getCompany() );
        userDO.setUsername( getUserResponse.getUsername() );
        userDO.setEmail( getUserResponse.getEmail() );
        userDO.setAddress( getUserResponse.getAddress() );
        userDO.setZip( getUserResponse.getZip() );
        userDO.setState( getUserResponse.getState() );
        userDO.setCountry( getUserResponse.getCountry() );
        userDO.setPhone( getUserResponse.getPhone() );
        userDO.setPhoto( getUserResponse.getPhoto() );

        return userDO;
    }
}
