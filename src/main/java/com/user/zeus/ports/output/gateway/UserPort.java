package com.user.zeus.ports.output.gateway;

import com.user.zeus.core.domain.UserDO;

import java.util.List;

public interface UserPort {

    /**
     * Retrieves a list of all users.
     *
     * @return A list of {@link UserDO} objects representing the users.
     */
    List<UserDO> getAllUsers();

    /**
     * Retrieves a list of all users.
     *
     * @return An object of {@link UserDO} objects representing the user by Id.
     */
    UserDO getUserById(String userId);
}
