package org.example.mockexam.modules.dto.response;

import org.example.mockexam.enums.Role;
import org.example.mockexam.enums.UserStatus;

public class UserResponse {
    private Long id;
    private String username;
    private Role role;
    private UserStatus status;

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public Role getRole() {
        return role;
    }

    public UserStatus getStatus() {
        return status;
    }
}
