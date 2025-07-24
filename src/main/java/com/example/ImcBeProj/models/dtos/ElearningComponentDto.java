package com.example.ImcBeProj.models.dtos;

import com.example.ImcBeProj.models.Category;
import com.example.ImcBeProj.models.Metatag;

import java.util.List;

public class ElearningComponentDto {
    private String id;
    private String name;
    private String description;
    private String type;
    private ElearningDates availableDates;
    private String imageUrl;
    private String userStatus;
    private String duration;
    private String category;
    private String[] metaTags;

    public ElearningComponentDto(String id, String name, String description, String type, ElearningDates availableDates, String imageUrl, String userStatus, String duration, String category, String[] metaTags) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.type = type;
        this.availableDates = availableDates;
        this.imageUrl = imageUrl;
        this.userStatus = userStatus;
        this.duration = duration;
        this.category = category;
        this.metaTags = metaTags;
    }

    public String[] getMetaTags() {
        return metaTags;
    }

    public void setMetaTags(String[] metaTags) {
        this.metaTags = metaTags;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ElearningDates getAvailableDates() {
        return availableDates;
    }

    public void setAvailableDates(ElearningDates availableDates) {
        this.availableDates = availableDates;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(String userStatus) {
        this.userStatus = userStatus;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
