package com.example.ImcBeProj.controller;

import com.example.ImcBeProj.models.ElearningComponent;
import com.example.ImcBeProj.service.ElearningService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/elearning")
public class ElearningController {
    private final ElearningService elearningService;

    public ElearningController(ElearningService elearningService) {
        this.elearningService = elearningService;
    }

    @GetMapping("/mine")
    public List<ElearningComponent> getMyElearning() {
        return elearningService.getElearningForLoggedUser();
    }
} 