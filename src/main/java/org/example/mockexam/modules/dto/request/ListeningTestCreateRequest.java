package org.example.mockexam.modules.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;
import java.util.List;

public class ListeningTestCreateRequest {
    @NotNull
    private Integer timeLimit;
    @NotNull
    private String name;
    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime activeDate;
    @Valid
    @NotEmpty
    @Size(min = 4, max = 4)
    private List<ListeningPartCreateRequest> parts;

    public @NotNull String getName() {
        return name;
    }

    public void setName(@NotNull String name) {
        this.name = name;
    }

    public @NotNull Integer getTimeLimit() {
        return timeLimit;
    }

    public void setTimeLimit(@NotNull Integer timeLimit) {
        this.timeLimit = timeLimit;
    }

    public @NotNull LocalDateTime getActiveDate() {
        return activeDate;
    }

    public void setActiveDate(@NotNull LocalDateTime activeDate) {
        this.activeDate = activeDate;
    }

    public @NotEmpty List<ListeningPartCreateRequest> getParts() {
        return parts;
    }

    public void setParts(@NotEmpty List<ListeningPartCreateRequest> parts) {
        this.parts = parts;
    }
}
