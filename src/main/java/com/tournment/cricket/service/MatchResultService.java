package com.tournment.cricket.service;

import com.tournment.cricket.model.MatchResult;
import com.tournment.cricket.model.Team;
import com.tournment.cricket.repository.MatchResultRepository;
import com.tournment.cricket.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MatchResultService {
    @Autowired
    private MatchResultRepository matchResultRepository;

    @Autowired
    private TeamRepository teamRepository;

    public List<MatchResult> getAllMatchResults() {
        return matchResultRepository.findAll();
    }

    public MatchResult getMatchResultById(Long id) {
        return matchResultRepository.findById(id).orElse(null);
    }

    public MatchResult createMatchResult(MatchResult matchResult) {
        if (matchResult.getWinningTeam() != null) {
            Long winnerId = matchResult.getWinningTeam().getId();
            Team winner = teamRepository.findById(winnerId).orElse(null);
            matchResult.setWinningTeam(winner);
        }

        if (matchResult.getLosingTeam() != null) {
            Long loserId = matchResult.getLosingTeam().getId();
            Team loser = teamRepository.findById(loserId).orElse(null);
            matchResult.setLosingTeam(loser);
        }

        return matchResultRepository.save(matchResult);
    }

    public MatchResult updateMatchResult(Long id, MatchResult matchResult) {
        MatchResult existingMatchResult = matchResultRepository.findById(id).orElse(null);
        if (existingMatchResult != null) {
            existingMatchResult.setWinningTeam(matchResult.getWinningTeam());
            existingMatchResult.setLosingTeam(matchResult.getLosingTeam());
            existingMatchResult.setResultType(matchResult.getResultType());
            return matchResultRepository.save(existingMatchResult);
        }
        return null;
    }

    public void deleteMatchResult(Long id) {
        matchResultRepository.deleteById(id);
    }
}
