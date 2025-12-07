package org.example.mockexam.modules.dto.request;

import jakarta.validation.constraints.NotNull;

public class ChangePasswordRequest {
    @NotNull
    String oldPassword;
    @NotNull
    String newPassword;
    @NotNull
    private Long id;

    public @NotNull String getOldPassword() {
        return oldPassword;
    }

    public @NotNull String getNewPassword() {
        return newPassword;
    }

    public @NotNull Long getId() {
        return id;
    }

    public void setOldPassword(@NotNull String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public void setNewPassword(@NotNull String newPassword) {
        this.newPassword = newPassword;
    }

    public void setId(@NotNull Long id) {
        this.id = id;
    }
}
