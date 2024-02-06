package com.tournment.cricket.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tournment.cricket.model.TournamentOrganizer;
import com.tournment.cricket.repository.TournamentOrganizerRepository;

@Service
public class OrganizerService {
    @Autowired
    private TournamentOrganizerRepository tournamentRepository;

    public List<TournamentOrganizer> getAllOrganizers() {
        return tournamentRepository.findAll();
    }

    public TournamentOrganizer getTournamentOrganizerById(Long id) {
        return tournamentRepository.findById(id).orElse(null);
    }

    public TournamentOrganizer createTournamentOrganizer(TournamentOrganizer organizer) {
        return tournamentRepository.save(organizer);
    }

    public TournamentOrganizer updateTournamentOrganizer(Long id, TournamentOrganizer tournament) {
    	TournamentOrganizer existingTournament = tournamentRepository.findById(id).orElse(null);
        if (existingTournament != null) {
        	existingTournament.setName(tournament.getName());
        	existingTournament.setEmail(tournament.getEmail()); 
        	existingTournament.setPhone(tournament.getPhone());
            return tournamentRepository.save(existingTournament);
        }
        return null;
    }

    public void deleteTournamentOrganizer(Long id) {
        tournamentRepository.deleteById(id);
    }

	public List<TournamentOrganizer> getOrganizerByName(String organizerName) {
		// TODO Auto-generated method stub
		return   tournamentRepository.findByName(organizerName);
	}


	
}
