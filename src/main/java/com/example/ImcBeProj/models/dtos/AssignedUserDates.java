package com.example.ImcBeProj.models.dtos;

import java.time.OffsetDateTime;

public class AssignedUserDates {
    private OffsetDateTime startDate;
    private OffsetDateTime endDate;

    public AssignedUserDates(OffsetDateTime startDate, OffsetDateTime endDate) {
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
