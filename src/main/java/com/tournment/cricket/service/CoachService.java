package com.tournment.cricket.service;

import com.tournment.cricket.model.Coach;
import com.tournment.cricket.repository.CoachRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CoachService {
    @Autowired
    private CoachRepository coachRepository;

    public List<Coach> getAllCoaches() {
        return coachRepository.findAll();
    }

    public Coach getCoachById(Long id) {
        return coachRepository.findById(id).orElse(null);
    }

    public Coach createCoach(Coach coach) {
        return coachRepository.save(coach);
    }

    public Coach updateCoach(Long id, Coach coach) {
        Coach existingCoach = coachRepository.findById(id).orElse(null);
        if (existingCoach != null) {
            existingCoach.setName(coach.getName());
            existingCoach.setEmail(coach.getEmail());
            existingCoach.setPhone(coach.getPhone());
            return coachRepository.save(existingCoach);
        }
        return null;
    }

    public void deleteCoach(Long id) {
        coachRepository.deleteById(id);
    }
}

