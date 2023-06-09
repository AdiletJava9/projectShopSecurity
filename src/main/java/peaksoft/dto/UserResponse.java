package peaksoft.dto;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import peaksoft.enums.Role;

@Data
@Builder
public class UserResponse {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    @Enumerated(EnumType.STRING)
    private Role role;

    public UserResponse(Long id, String firstName, String lastName, String email, Role role) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.role = role;
    }
}
