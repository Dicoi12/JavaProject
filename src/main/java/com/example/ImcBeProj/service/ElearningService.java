package com.example.ImcBeProj.service;

import com.example.ImcBeProj.models.ElearningComponent;
import com.example.ImcBeProj.models.ElearningUser;
import com.example.ImcBeProj.models.User;
import com.example.ImcBeProj.models.dtos.BasicFilter;
import com.example.ImcBeProj.models.dtos.ElearningComponentDto;
import com.example.ImcBeProj.models.dtos.ElearningForUserDto;
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
//sau autowired
    public ElearningService(UserService userService, ElearningUserRepository elearningUserRepository) {
        this.userService = userService;
        this.elearningUserRepository = elearningUserRepository;
    }

    public List<ElearningForUserDto> getElearningForLoggedUser(BasicFilter filter) {
        Optional<User> userOpt = userService.getCurrentUser();
        if (userOpt.isEmpty()) {
            return new ArrayList<>();
        }
        User user = userOpt.get();
        return elearningUserRepository.getUserElearnings(user.getId(),filter.getPageNumber(),filter.getPageSize());
    }

    public Optional<ElearningComponentDto> getSpecificElearningForLoggedUser(String eLearningId) {
        Optional<User> userOpt = userService.getCurrentUser();
        if (userOpt.isEmpty()) {
            return null;
        }
        User user = userOpt.get();
        return elearningUserRepository.getUserSpecificElearning(user.getId(),eLearningId);
    }
} 