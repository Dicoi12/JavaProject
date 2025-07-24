package com.example.ImcBeProj.models.dtos;

public class ElearningForUserDto {
    private String id;
    private String name;
    private String type;
    private AssignedUserDates assignedDates;
    private String userStatus;
    private String imageUrl;

    public ElearningForUserDto(String id, String name, String type, AssignedUserDates assignedDates, String userStatus, String imageUrl) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.assignedDates = assignedDates;
        this.userStatus = userStatus;
        this.imageUrl = imageUrl;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public AssignedUserDates getAssignedDates() {
        return assignedDates;
    }

    public void setAssignedDates(AssignedUserDates assignedDates) {
        this.assignedDates = assignedDates;
    }

    public String getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(String userStatus) {
        this.userStatus = userStatus;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
