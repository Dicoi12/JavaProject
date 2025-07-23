package com.example.ImcBeProj.models;

import org.springframework.data.annotation.Id;

import java.time.OffsetDateTime;

public class ElearningUser {
    @Id
    private Long id;
    private String elearningId;
    private Long userId;
    private OffsetDateTime startDate;
    private OffsetDateTime endDate;
    private String status;

    public ElearningUser(Long id, String elearningId, Long userId, OffsetDateTime startDate, OffsetDateTime endDate, String status) {
        this.id = id;
        this.elearningId = elearningId;
        this.userId = userId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getElearningId() {
        return elearningId;
    }

    public void setElearningId(String elearningId) {
        this.elearningId = elearningId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public OffsetDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(OffsetDateTime startDate) {
        this.startDate = startDate;
    }

    public OffsetDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(OffsetDateTime endDate) {
        this.endDate = endDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

