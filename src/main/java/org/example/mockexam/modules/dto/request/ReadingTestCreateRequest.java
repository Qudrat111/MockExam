package org.example.mockexam.modules.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;
import java.util.List;

public class ReadingTestCreateRequest {
    @NotNull
    private Integer timeLimit;
    @NotNull
    private String name;
    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime activeDate;
    @Valid
    @NotEmpty
    @Size(min = 3, max = 3)
    private List<ReadingPassageCreateRequest> passages;

    public Integer getTimeLimit() {
        return timeLimit;
    }

    public void setTimeLimit(Integer timeLimit) {
        this.timeLimit = timeLimit;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getActiveDate() {
        return activeDate;
    }

    public void setActiveDate(LocalDateTime activeDate) {
        this.activeDate = activeDate;
    }

    public @NotEmpty List<ReadingPassageCreateRequest> getPassages() {
        return passages;
    }

    public void setPassages(@NotEmpty List<ReadingPassageCreateRequest> passages) {
        this.passages = passages;
    }
}
