package org.example.mockexam.modules.dto.response;

import org.example.mockexam.enums.TestType;

import java.time.LocalDateTime;
import java.util.List;

public class ActiveListeningTestResponse {
    private Long id;
    private String name;
    private Integer timeLimit;
    private LocalDateTime activeDate;
    private TestType type;
    private Boolean active;
    private List<ListeningPartResponse> parts;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getTimeLimit() {
        return timeLimit;
    }

    public void setTimeLimit(Integer timeLimit) {
        this.timeLimit = timeLimit;
    }

    public LocalDateTime getActiveDate() {
        return activeDate;
    }

    public void setActiveDate(LocalDateTime activeDate) {
        this.activeDate = activeDate;
    }

    public TestType getType() {
        return type;
    }

    public void setType(TestType type) {
        this.type = type;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public List<ListeningPartResponse> getParts() {
        return parts;
    }

    public void setParts(List<ListeningPartResponse> parts) {
        this.parts = parts;
    }
}
