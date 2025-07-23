package com.example.ImcBeProj.models;

import org.springframework.data.annotation.Id;

import java.time.OffsetDateTime;

public class ElearningComponent {
    @Id
    private String id;
    private String name;
    private String imageUrl;
    private String description;
    private Float duration;
    private String type;
    private Short categoryId;
    private OffsetDateTime startDate;
    private OffsetDateTime endDate;

    public ElearningComponent(String id, String name, String imageUrl, String description, Float duration, String type, Short categoryId, OffsetDateTime startDate, OffsetDateTime endDate) {
        this.id = id;
        this.name = name;
        this.imageUrl = imageUrl;
        this.description = description;
        this.duration = duration;
        this.type = type;
        this.categoryId = categoryId;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Float getDuration() {
        return duration;
    }

    public void setDuration(Float duration) {
        this.duration = duration;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Short getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Short categoryId) {
        this.categoryId = categoryId;
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
