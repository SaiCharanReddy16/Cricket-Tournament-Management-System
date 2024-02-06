package com.tournment.cricket.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tournment.cricket.model.Tournament;
import com.tournment.cricket.repository.TournamentRepository;

@Service
public class TournamentService {
    @Autowired
    private TournamentRepository tournamentRepository;

    public List<Tournament> getAllTournments() {
        return tournamentRepository.findAll();
    }

    public Tournament getTournamentById(Long id) {
        return tournamentRepository.findById(id).orElse(null);
    }

    public Tournament createTournament(Tournament tournament) {
        return tournamentRepository.save(tournament);
    }

    public Tournament updateTournament(Long id, Tournament tournament) {
        Tournament existingTournament = tournamentRepository.findById(id).orElse(null);
        if (existingTournament != null) {
        	if(tournament.getName()!=null)
        	existingTournament.setName(tournament.getName());

        	if(tournament.getLocation()!=null)
        	existingTournament.setLocation  (tournament.getLocation());

        	if(tournament.getStartDate()!=null)
        	existingTournament.setStartDate(tournament.getStartDate());

        	if(tournament.getOrganizer()!=null)
        	 existingTournament.setOrganizer(tournament.getOrganizer());

        	if(tournament.getEndDate()!=null)
        	existingTournament.setEndDate(tournament.getEndDate());
            return tournamentRepository.save(existingTournament);
        }
        return null;
    }

    public void deleteTournament(Long id) {
        tournamentRepository.deleteById(id);
    }


	
}
