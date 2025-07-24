package com.example.ImcBeProj.models.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.OffsetDateTime;

public class ElearningDates {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private OffsetDateTime startDate;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private OffsetDateTime endDate;

    public ElearningDates(OffsetDateTime startDate, OffsetDateTime endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
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
}
