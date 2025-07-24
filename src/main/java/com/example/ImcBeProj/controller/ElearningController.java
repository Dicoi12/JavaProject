package com.example.ImcBeProj.controller;

import com.example.ImcBeProj.models.ElearningComponent;
import com.example.ImcBeProj.models.dtos.BasicFilter;
import com.example.ImcBeProj.models.dtos.ElearningComponentDto;
import com.example.ImcBeProj.models.dtos.ElearningForUserDto;
import com.example.ImcBeProj.service.ElearningService;
import jakarta.websocket.RemoteEndpoint;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/project/restapi/lms/my-elearings")
public class ElearningController {
    private final ElearningService elearningService;

    public ElearningController(ElearningService elearningService) {
        this.elearningService = elearningService;
    }

    @GetMapping()
    public ResponseEntity<List<ElearningForUserDto>> getMyElearning(
            @RequestParam(defaultValue = "0") int pageSize,
            @RequestParam(defaultValue = "0") int pageNumber) {
        if (pageSize < 0 || pageNumber < 0) {
            return ResponseEntity.badRequest().body(null);
        }
        BasicFilter filter = new BasicFilter(pageSize, pageNumber);
        List<ElearningForUserDto> result = elearningService.getElearningForLoggedUser(filter);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ElearningComponentDto> getSpecificElearning(@PathVariable String id) {
        Optional<ElearningComponentDto> result = elearningService.getSpecificElearningForLoggedUser(id);
        if (result.isPresent()) {
            return ResponseEntity.ok(result.get());
        } else {
            return ResponseEntity.status(404).body(null);
        }
    }
}