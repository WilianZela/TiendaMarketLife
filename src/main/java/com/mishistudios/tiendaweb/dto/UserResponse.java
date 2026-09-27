package com.mishistudios.tiendaweb.dto;

import com.mishistudios.tiendaweb.model.Role;
import com.mishistudios.tiendaweb.model.User;

public class UserResponse {

    private String username;
    private String email;
    private Role role;

    public UserResponse(User user) {
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.role = user.getRole();
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public Role getRole() {
        return role;
    }
}
