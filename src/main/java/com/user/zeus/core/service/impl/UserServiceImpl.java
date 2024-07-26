package com.user.zeus.core.service.impl;

import com.user.zeus.commons.SortOrder;
import com.user.zeus.commons.SortingParameter;
import com.user.zeus.core.domain.UserDO;
import com.user.zeus.core.service.UserService;
import com.user.zeus.ports.output.gateway.UserPort;
import io.micrometer.observation.annotation.Observed;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Slf4j
@Service
@Observed
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserPort userPort;

    private static final String LOG_USERS_FOUND = "Found {} users with email domain: {}";
    private static final String WARN_EMPTY_EMAIL = "Attempted to filter users with null or empty email domain";

    @Override
    public List<UserDO> getAllUsers() {
        return userPort.getAllUsers();
    }

    @Override
    public List<UserDO> getAllUsersOrderBy(String param, String order) {
        List<UserDO> userDOList = userPort.getAllUsers();

        SortingParameter sortParam = SortingParameter.valueOf(param.toUpperCase());
        SortOrder sortOrder = SortOrder.valueOf(order.toUpperCase());

        Comparator<UserDO> comparator = getComparator(sortParam);

        if (sortOrder == SortOrder.DESC) {
            comparator = comparator.reversed();
        }

        return userDOList.stream()
                .sorted(comparator)
                .collect(Collectors.toList());
    }

    private Comparator<UserDO> getComparator(SortingParameter param) {
        return switch (param) {
            case ID -> Comparator.comparingInt(UserDO::getId);
            case NAME -> Comparator.comparing(UserDO::getName, String.CASE_INSENSITIVE_ORDER);
            case COMPANY -> Comparator.comparing(UserDO::getCompany, String.CASE_INSENSITIVE_ORDER);
            case USERNAME -> Comparator.comparing(UserDO::getUsername, String.CASE_INSENSITIVE_ORDER);
            case EMAIL -> Comparator.comparing(UserDO::getEmail, String.CASE_INSENSITIVE_ORDER);
            case ADDRESS -> Comparator.comparing(UserDO::getAddress, String.CASE_INSENSITIVE_ORDER);
            case ZIP -> Comparator.comparing(UserDO::getZip, String.CASE_INSENSITIVE_ORDER);
            case STATE -> Comparator.comparing(UserDO::getState, String.CASE_INSENSITIVE_ORDER);
            case COUNTRY -> Comparator.comparing(UserDO::getCountry, String.CASE_INSENSITIVE_ORDER);
            case PHONE -> Comparator.comparing(UserDO::getPhone, String.CASE_INSENSITIVE_ORDER);
            case PHOTO -> Comparator.comparing(UserDO::getPhoto, String.CASE_INSENSITIVE_ORDER);
        };
    }

    @Override
    public List<UserDO> getUserByID(String userId) {
        return userPort.getAllUsersById(userId);
    }

    @Override
    public List<UserDO> getUsersByCountry(String country) {
        List<UserDO> userDOList = userPort.getAllUsers();

        if (country == null || country.trim().isEmpty()) {
            return Collections.emptyList();
        }

        return userDOList.stream()
                .filter(user -> country.equalsIgnoreCase(user.getCountry()))
                .collect(Collectors.toList());
    }

    @Override
    public List<UserDO> getUsersByNameAndCompany(String name, String company) {
        List<UserDO> userDOList = userPort.getAllUsers();
        return userDOList.stream()
                .filter(user -> matchesNameAndCompany(user, name, company))
                .collect(Collectors.toList());
    }

    @Override
    public List<UserDO> getUsersByEmail(String email) {
        List<UserDO> userDOList = userPort.getAllUsers();

        if (email == null || email.trim().isEmpty()) {
            log.warn(WARN_EMPTY_EMAIL);
            return Collections.emptyList();
        }

        String cleanedDomain = Pattern.quote(email.trim().toLowerCase());
        Pattern pattern = Pattern.compile("^[\\w-\\.]+@(" + cleanedDomain + "\\.)+[\\w-]{2,4}$", Pattern.CASE_INSENSITIVE);

        List<UserDO> filteredUsers = userDOList.stream()
                .filter(user -> user.getEmail() != null && pattern.matcher(user.getEmail()).find())
                .collect(Collectors.toList());

        log.info(LOG_USERS_FOUND, filteredUsers.size(), email);
        return filteredUsers;
    }

    private boolean matchesNameAndCompany(UserDO user, String name, String company) {
        boolean nameMatches = name == null || name.trim().isEmpty() || name.equalsIgnoreCase(user.getName());
        boolean companyMatches = company == null || company.trim().isEmpty() || company.equalsIgnoreCase(user.getCompany());
        return nameMatches && companyMatches;
    }
}