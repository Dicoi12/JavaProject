package com.example.ImcBeProj.service;

import com.example.ImcBeProj.models.ElearningComponent;
import com.example.ImcBeProj.models.ElearningUser;
import com.example.ImcBeProj.models.User;
import com.example.ImcBeProj.repositories.ElearningComponentRepository;
import com.example.ImcBeProj.repositories.ElearningUserRepository;
import com.example.ImcBeProj.repositories.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ElearningService {
    private final UserService userService;
    private final ElearningUserRepository elearningUserRepository;
    private final ElearningComponentRepository elearningComponentRepository;
//sau autowired
    public ElearningService(UserService userService, ElearningUserRepository elearningUserRepository, ElearningComponentRepository elearningComponentRepository) {
        this.userService = userService;
        this.elearningUserRepository = elearningUserRepository;
        this.elearningComponentRepository = elearningComponentRepository;
    }

    public List<ElearningComponent> getElearningForLoggedUser() {
        Optional<User> userOpt = userService.getCurrentUser();
        if (userOpt.isEmpty()) {
            return new ArrayList<>();
        }
        User user = userOpt.get();
        List<ElearningUser> elearningUsers = elearningUserRepository.findByUserId(user.getId());
        List<ElearningComponent> result = new ArrayList<>();
        for (ElearningUser eu : elearningUsers) {
            elearningComponentRepository.findById(eu.getElearningId()).ifPresent(result::add);
        }
        return result;
    }
} 