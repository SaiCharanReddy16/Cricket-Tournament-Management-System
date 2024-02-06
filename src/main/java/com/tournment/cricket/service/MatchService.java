package com.tournment.cricket.service;

import com.tournment.cricket.model.Match;
import com.tournment.cricket.model.Umpire;
import com.tournment.cricket.model.Venue;
import com.tournment.cricket.repository.MatchRepository;
import com.tournment.cricket.repository.UmpireRepository;
import com.tournment.cricket.repository.VenueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
public class MatchService {
    @Autowired
    private MatchRepository matchRepository;

    @Autowired
    private VenueRepository venueRepository;

    @Autowired
    private UmpireRepository umpireRepository;

    public List<Match> getAllMatches() {
    	List<Match> matches = matchRepository.getAllMatches();
    	return matches;
    }

    public Match getMatchById(Long id) {
        return matchRepository.getMatchDetails(id);
    }

    public Match createMatch(Match match) {
        if (match.getVenue() != null) {
            Long venueId = match.getVenue().getId();
            Venue venue = venueRepository.findById(venueId).orElse(null);
            match.setVenue(venue);
        }

        if (match.getUmpire() != null) {
            Long umpire1Id = match.getUmpire().getId();
            Umpire umpire1 = umpireRepository.findById(umpire1Id).orElse(null);
            match.setUmpire(umpire1);
        }

        return matchRepository.save(match);
    }

    public Match updateMatch(Long id, Match match) {
        Match existingMatch = matchRepository.findById(id).orElse(null);
        if (existingMatch != null) {
            existingMatch.setMatchDate(match.getMatchDate());
            existingMatch.setVenue(match.getVenue());

            if (match.getUmpire() != null) {
                Long umpire1Id = match.getUmpire().getId();
                Umpire umpire1 = umpireRepository.findById(umpire1Id).orElse(null);
                existingMatch.setUmpire(umpire1);
            } else {
                existingMatch.setUmpire(null);
            }
            return matchRepository.save(existingMatch);
        }
        return null;
    }

    public void deleteMatch(Long id) {
        matchRepository.deleteById(id);
    }

    public void createNewMatch(String matchType, String matchDate, String matchTime, Long venueId, Long umpireId,Long tournmentId ,Long team1Id,Long team2Id) {
    			matchRepository.createNewMatch(matchType, LocalDate.parse( matchDate),LocalTime.parse(matchTime),venueId, umpireId,tournmentId,team1Id,team2Id);
	
	}

	
}

