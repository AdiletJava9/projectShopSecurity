package peaksoft.service;

import peaksoft.dto.SimpleResponse;
import peaksoft.dto.UserRequest;
import peaksoft.dto.UserResponse;

import java.util.List;

public interface UserService {
    SimpleResponse saveUser(UserRequest userRequest);

    List<UserResponse> getAllUsers();

    UserResponse getUserById(Long id);

    SimpleResponse updateUser(Long id, UserRequest userRequest);

    SimpleResponse deleteUserById(Long id);
}
