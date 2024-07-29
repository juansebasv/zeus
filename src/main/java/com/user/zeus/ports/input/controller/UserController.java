package com.user.zeus.ports.input.controller;

import com.user.zeus.core.service.UserService;
import com.user.zeus.ports.input.mapper.UserMapper;
import com.user.zeus.ports.input.response.UserResponse;
import io.micrometer.observation.annotation.Observed;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Validated
@RestController
@RequestMapping("/users")
@AllArgsConstructor
@Observed
@Tag(name = "User Management", description = "Operations pertaining to user management")
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    @GetMapping("/all")
    @Operation(summary = "Get all users", description = "Retrieves a list of all users.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved user list"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public List<UserResponse> getAllUsers() {
        var userDOLIst = userService.getAllUsers();
        return userDOLIst
                .stream()
                .map(userMapper::requestToDomain)
                .collect(Collectors.toList());
    }

    @GetMapping("/all/param/{param}/order/{order}")
    @Operation(summary = "Get all users ordered", description = "Retrieves a list of all users ordered by a specified parameter and order.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved ordered user list"),
            @ApiResponse(responseCode = "400", description = "Invalid parameter or order"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public List<UserResponse> getAllUsersOrderBy(
            @Parameter(description = "Parameter to order by", required = true) @Valid @NotBlank @PathVariable("param") @Size(min = 1, max = 10) String param,
            @Parameter(description = "Order direction (ASC or DESC)", required = true) @Valid @NotBlank @PathVariable("order") @Size(min = 3, max = 4) String order) {
        var userDOLIst = userService.getAllUsersOrderedBy(param, order);
        return userDOLIst
                .stream()
                .map(userMapper::requestToDomain)
                .collect(Collectors.toList());
    }

    @GetMapping("/{userId}/profile")
    @Operation(summary = "Get user by ID", description = "Retrieves a user's profile by their ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved user profile"),
            @ApiResponse(responseCode = "404", description = "User not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public UserResponse getUserByID(
            @Parameter(description = "ID of the user", required = true) @Valid @NotBlank @PathVariable("userId") @Size(min = 1, max = 5) String userId) {
        var userDO = userService.getUserByID(userId);
        return userMapper.requestToDomain(userDO);
    }

    @GetMapping("/{country}")
    @Operation(summary = "Get users by country", description = "Retrieves a list of users from a specified country.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved user list"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public List<UserResponse> getUsersByCountry(
            @Parameter(description = "Country of the users", required = true) @Valid @NotBlank @PathVariable("country") @Size(min = 1, max = 10) String country) {
        var userDOLIst = userService.getUsersByCountry(country);
        return userDOLIst
                .stream()
                .map(userMapper::requestToDomain)
                .collect(Collectors.toList());
    }

    @GetMapping("/{name}/{company}")
    @Operation(summary = "Get users by name and company", description = "Retrieves a list of users with the specified name and company.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved user list"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public List<UserResponse> getUsersByNameAndCompany(
            @Parameter(description = "Name of the users", required = true) @Valid @NotBlank @PathVariable("name") @Size(min = 1, max = 10) String name,
            @Parameter(description = "Company of the users", required = true) @Valid @NotBlank @PathVariable("company") @Size(min = 1, max = 10) String company) {
        var userDOLIst = userService.getUsersByNameAndCompany(name, company);
        return userDOLIst
                .stream()
                .map(userMapper::requestToDomain)
                .collect(Collectors.toList());
    }

    @GetMapping("/email/{email}")
    @Operation(summary = "Get users by email domain", description = "Retrieves a list of users with email addresses in the specified domain.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved user list"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public List<UserResponse> getUsersByEmail(
            @Parameter(description = "Email domain of the users", required = true) @Valid @NotBlank @PathVariable("email") @Size(min = 1, max = 10) String email) {
        var userDOLIst = userService.getUsersByEmail(email);
        return userDOLIst
                .stream()
                .map(userMapper::requestToDomain)
                .collect(Collectors.toList());
    }
}