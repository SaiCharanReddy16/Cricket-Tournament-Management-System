package com.tournment.cricket.service;

import com.tournment.cricket.model.MatchScorecardDetails;
import com.tournment.cricket.model.Player;
import com.tournment.cricket.repository.MatchScorecardDetailsRepository;
import com.tournment.cricket.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MatchScorecardDetailsService {
    @Autowired
    private MatchScorecardDetailsRepository matchScorecardDetailsRepository;

    @Autowired
    private PlayerRepository playerRepository;

    public List<MatchScorecardDetails> getAllMatchScorecardDetails() {
        return matchScorecardDetailsRepository.findAll();
    }

    public MatchScorecardDetails getMatchScorecardDetailsById(Long id) {
        return matchScorecardDetailsRepository.findById(id).orElse(null);
    }

    public MatchScorecardDetails createMatchScorecardDetails(MatchScorecardDetails matchScorecardDetails) {
        if (matchScorecardDetails.getPlayer() != null) {
            Long playerId = matchScorecardDetails.getPlayer().getId();
            Player player = playerRepository.findById(playerId).orElse(null);
            matchScorecardDetails.setPlayer(player);
        }

        return matchScorecardDetailsRepository.save(matchScorecardDetails);
    }

    public MatchScorecardDetails updateMatchScorecardDetails(Long id, MatchScorecardDetails matchScorecardDetails) {
        MatchScorecardDetails existingMatchScorecardDetails = matchScorecardDetailsRepository.findById(id).orElse(null);
        if (existingMatchScorecardDetails != null) {
            existingMatchScorecardDetails.setId(matchScorecardDetails.getId());
            existingMatchScorecardDetails.setRunsScored(matchScorecardDetails.getRunsScored());
            existingMatchScorecardDetails.setBallsFaced(matchScorecardDetails.getBallsFaced());

            if (matchScorecardDetails.getPlayer() != null) {
                Long playerId = matchScorecardDetails.getPlayer().getId();
                Player player = playerRepository.findById(playerId).orElse(null);
                existingMatchScorecardDetails.setPlayer(player);
            } else {
                existingMatchScorecardDetails.setPlayer(null);
            }

            return matchScorecardDetailsRepository.save(existingMatchScorecardDetails);
        }
        return null;
    }

    public void deleteMatchScorecardDetails(Long id) {
        matchScorecardDetailsRepository.deleteById(id);
    }

    public List<MatchScorecardDetails> getMatchScorecardDetails(Long matchId,Long teamId,Long playerId) {
      return  matchScorecardDetailsRepository.getMatchScorecardDetails(matchId, teamId, playerId);
    }
    public List<MatchScorecardDetails> getMatchScorecardDetails(Long matchId) {
        return  matchScorecardDetailsRepository.getMatchScorecardDetails(matchId);
      }


}

