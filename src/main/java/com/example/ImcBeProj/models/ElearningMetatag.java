package com.example.ImcBeProj.models;

import org.springframework.data.annotation.Id;

public class ElearningMetatag {
    @Id
    private Integer id;
    private String elearningId;
    private Integer metatagId;

    public ElearningMetatag(Integer id, String elearningId, Integer metatagId) {
        this.id = id;
        this.elearningId = elearningId;
        this.metatagId = metatagId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getElearningId() {
        return elearningId;
    }

    public void setElearningId(String elearningId) {
        this.elearningId = elearningId;
    }

    public Integer getMetatagId() {
        return metatagId;
    }

    public void setMetatagId(Integer metatagId) {
        this.metatagId = metatagId;
    }
}
