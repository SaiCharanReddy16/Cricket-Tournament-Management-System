package com.tournment.cricket.service;

import com.tournment.cricket.model.Captain;
import com.tournment.cricket.repository.CaptainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CaptainService {
    @Autowired
    private CaptainRepository captainRepository;

    public List<Captain> getAllCaptains() {
        return captainRepository.findAll();
    }

    public Captain getCaptainById(Long id) {
        return captainRepository.findById(id).orElse(null);
    }

    public Captain createCaptain(Captain captain) {
        return captainRepository.save(captain);
    }

    public Captain updateCaptain(Long id, Captain captain) {
        Captain existingCaptain = captainRepository.findById(id).orElse(null);
        if (existingCaptain != null) {
            existingCaptain.setName(captain.getName());
            existingCaptain.setEmail(captain.getEmail());
            existingCaptain.setPhone(captain.getPhone());
            return captainRepository.save(existingCaptain);
        }
        return null;
    }

    public void deleteCaptain(Long id) {
        captainRepository.deleteById(id);
    }
}

