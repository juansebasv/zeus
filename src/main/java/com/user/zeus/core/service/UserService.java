package com.user.zeus.core.service;

import com.user.zeus.core.domain.UserDO;

import java.util.List;

/**
 * Service interface for managing user-related operations.
 */
public interface UserService {

    /**
     * Retrieves all users.
     *
     * @return a List<UserDO> object containing the details of all users.
     */
    List<UserDO> getAllUsers();

    /**
     * Retrieves all users ordered by a specified parameter and order.
     *
     * @param param The parameter to order by (e.g., "name", "email", etc.).
     * @param order The order direction ("ASC" for ascending, "DESC" for descending).
     * @return a List<UserDO> object containing the details of all users.
     * @throws IllegalArgumentException if the param or order is invalid.
     */
    List<UserDO> getAllUsersOrderedBy(String param, String order);

    /**
     * Retrieves a user by their ID.
     *
     * @param userId The ID of the user to retrieve.
     * @return a List<UserDO> object containing the user with the specified ID.
     * The list will be empty if no user is found with the given ID.
     */
    UserDO getUserByID(String userId);

    /**
     * Retrieves all users from a specified country.
     *
     * @param country The country to filter users by.
     * @return a List<UserDO> object containing the users from the specified country.
     */
    List<UserDO> getUsersByCountry(String country);

    /**
     * Retrieves users by name and company.
     *
     * @param name    The name to filter users by.
     * @param company The company to filter users by.
     * @return a List<UserDO> object containing the users matching the specified name and company.
     */
    List<UserDO> getUsersByNameAndCompany(String name, String company);

    /**
     * Retrieves users by email domain.
     *
     * @param email The email domain to filter users by.
     * @return a List<UserDO> object containing the users with email addresses matching the specified domain.
     */
    List<UserDO> getUsersByEmail(String email);
}