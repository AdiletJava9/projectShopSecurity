package peaksoft.api;

import jakarta.annotation.security.PermitAll;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import peaksoft.dto.SimpleResponse;
import peaksoft.dto.UserRequest;
import peaksoft.dto.UserResponse;
import peaksoft.service.UserService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserApi {
    private final UserService userService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping
    public SimpleResponse saveUser(@RequestBody UserRequest userRequest) {
        return userService.saveUser(userRequest);
    }

    @PermitAll
    @GetMapping
    public List<UserResponse> getAllUsers() {
        return userService.getAllUsers();
    }

    @PermitAll
    @GetMapping("/{id}")
    public UserResponse getById(@PathVariable Long id) {
        return userService.getUserById(id);
    }
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @PutMapping("/{id}")
    public SimpleResponse updateUser(@PathVariable Long id, @RequestBody UserRequest userRequest) {
        return userService.updateUser(id, userRequest);
    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{id}")
    public SimpleResponse deleteUser(@PathVariable Long id) {
        return userService.deleteUserById(id);
    }
}

