package peaksoft.service.serviceImpl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import peaksoft.dto.SimpleResponse;
import peaksoft.dto.UserRequest;
import peaksoft.dto.UserResponse;
import peaksoft.entity.User;
import peaksoft.repository.UserRepository;
import peaksoft.service.UserService;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public SimpleResponse saveUser(UserRequest userRequest) {
        User user = new User();
        user.setFirstName(userRequest.getFirstName());
        user.setLastName(userRequest.getLastName());
        user.setEmail(userRequest.getEmail());
        user.setPassword(userRequest.getPassword());
        user.setRole(userRequest.getRole());
        userRepository.save(user);
        return SimpleResponse.builder()
                .status(HttpStatus.OK)
                .message(String.format("User with name %s is successfully saved", userRequest.getFirstName()))
                .build();
    }

    @Override
    public List<UserResponse> getAllUsers() {
        return userRepository.getAllUsers();
    }

    @Override
    public UserResponse getUserById(Long id) {
        return userRepository.findUserById(id).orElseThrow(() ->
                new NullPointerException("User with id: " + id + " not found"));
    }

    @Override
    public SimpleResponse updateUser(Long id, UserRequest userRequest) {
        User user = userRepository.findById(id).orElseThrow(() ->
                new NullPointerException("User with id: " + id + " not found"));
        user.setFirstName(userRequest.getFirstName());
        user.setLastName(userRequest.getLastName());
        user.setEmail(userRequest.getEmail());
        user.setPassword(userRequest.getPassword());
        user.setRole(userRequest.getRole());
        userRepository.save(user);
        return SimpleResponse.builder()
                .status(HttpStatus.OK)
                .message(String.format("User with name %s is successfully updated", userRequest.getFirstName()))
                .build();
    }

    @Override
    public SimpleResponse deleteUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() ->
                new NullPointerException("User with id: " + id + " not found"));
        userRepository.delete(user);
        return SimpleResponse.builder()
                .status(HttpStatus.OK)
                .message(String.format("User with id %s is successfully deleted", id))
                .build();
    }
}
