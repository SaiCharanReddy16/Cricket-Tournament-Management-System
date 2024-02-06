package com.tournment.cricket.service;

import com.tournment.cricket.model.Match;
import com.tournment.cricket.model.MatchScorecard;
import com.tournment.cricket.model.MatchScorecardDetails;
import com.tournment.cricket.model.Team;
import com.tournment.cricket.repository.MatchRepository;
import com.tournment.cricket.repository.MatchScorecardDetailsRepository;
import com.tournment.cricket.repository.MatchScorecardRepository;
import com.tournment.cricket.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MatchScorecardService {
    @Autowired
    private MatchScorecardRepository matchScorecardRepository;
    @Autowired
    private MatchScorecardDetailsRepository matchScorecardDetailsRepository;

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private MatchRepository matchRepository;

    public List<MatchScorecard> getAllMatchScorecards() {
        return matchScorecardRepository.findAll();
    }

    public MatchScorecard getMatchScorecardById(Long id) {
        return matchScorecardRepository.findById(id).orElse(null);
    }

    public MatchScorecard createMatchScorecard(MatchScorecard matchScorecard) {
        if (matchScorecard.getTeam() != null && matchScorecard.getTeam().getId() != null) {
            Long teamId = matchScorecard.getTeam().getId();
            Team team = teamRepository.findById(teamId).orElse(null);
            matchScorecard.setTeam(team);
        }


        Team team = matchScorecard.getTeam();
        if (team != null) {
            Long teamId = team.getId();
            team = teamRepository.findById(teamId).orElse(null);
            matchScorecard.setTeam(team);
        }


        if (matchScorecard.getMatch() != null) {
            Long matchId = matchScorecard.getMatch().getId();
            Match match = matchRepository.findById(matchId).orElse(null);
            matchScorecard.setMatch(match);
        }

        return matchScorecardRepository.save(matchScorecard);
    }

    public MatchScorecard updateMatchScorecard(Long id, MatchScorecard matchScorecard) {
        MatchScorecard existingMatchScorecard = matchScorecardRepository.findById(id).orElse(null);
        if (existingMatchScorecard != null) {
            existingMatchScorecard.setTotalRuns(matchScorecard.getTotalRuns());
            existingMatchScorecard.setTotalWickets(matchScorecard.getTotalWickets());
            existingMatchScorecard.setTotalOvers(matchScorecard.getTotalOvers());
            existingMatchScorecard.setTeam(matchScorecard.getTeam());
            existingMatchScorecard.setMatch(matchScorecard.getMatch());
            return matchScorecardRepository.save(existingMatchScorecard);
        }
        return null;
    }

    public void deleteMatchScorecard(Long id) {
        matchScorecardRepository.deleteById(id);
    }

	public List<MatchScorecard> getMatchScorecardByMatchId(Long id) {
	
	    return matchScorecardRepository.getMatchScoreCardByMatchId( id);
        
	}


	public void insertScoreCard(MatchScorecardDetails scoreCard, Long playerId, Long teamId, Long matchId) {
		MatchScorecard matchScorecard = null;
		MatchScorecard opponentTeamMatchScorecard = null;
		
		List<MatchScorecard> matchScorecardList =	matchScorecardRepository.getTeamScoreCard(teamId, matchId);
		
		
		List<MatchScorecard> opponentTeamMatchScorecardList =	matchScorecardRepository.getOpponentTeamScoreCard(teamId, matchId);
		
		
		List<MatchScorecardDetails> matchScorecardDetailLit =		matchScorecardDetailsRepository.getMatchScorecardDetails(matchId, teamId, playerId);

		if(opponentTeamMatchScorecardList!=null && opponentTeamMatchScorecardList.size()>0) {
			
			opponentTeamMatchScorecard = opponentTeamMatchScorecardList.get(0);
		}else {
			
			Match match =	matchRepository .getMatch(matchId);
			Long opponentTeamID = (match.getTeam1().getId()==teamId)?match.getTeam2().getId():match.getTeam1().getId();
			
			matchScorecardRepository.insertTeamScoreCard(0, 0, 0.0f, 0, opponentTeamID, matchId);
			 opponentTeamMatchScorecardList =	matchScorecardRepository.getOpponentTeamScoreCard(teamId, matchId);
			 if(opponentTeamMatchScorecardList!=null && opponentTeamMatchScorecardList.size()>0) {
					
					opponentTeamMatchScorecard = opponentTeamMatchScorecardList.get(0);
				}		
		}
	
		
		
		if(matchScorecardList!=null && matchScorecardList.size()>0) {
			matchScorecard = matchScorecardList.get(0);
			
			if(matchScorecardDetailLit!=null && !matchScorecardDetailLit.isEmpty()) {
				
				MatchScorecardDetails matchScorecardDetails =  matchScorecardDetailLit.get(0);
				int runsDiff = scoreCard.getRunsScored()-matchScorecardDetails.getRunsScored();

			
				int extraDiff = scoreCard.getExtrasGiven()-matchScorecardDetails.getExtrasGiven();

				float oversDiff = scoreCard.getOversBowled()-matchScorecardDetails.getOversBowled();

				int wicketsDiff = ((scoreCard.getWicketsTaken()!=null)?scoreCard.getWicketsTaken():0 )  - (( (matchScorecardDetails.getWicketsTaken()!=null)? matchScorecardDetails.getWicketsTaken():0));

				matchScorecardRepository.updateTeamScoreCard(matchScorecard.getTotalRuns()+runsDiff, matchScorecard.getTotalWickets(), matchScorecard.getTotalOvers(), matchScorecard.getTotalExtras(), teamId, matchId);
				matchScorecardRepository.updateTeamScoreCard(opponentTeamMatchScorecard.getTotalRuns(), opponentTeamMatchScorecard.getTotalWickets()+wicketsDiff, opponentTeamMatchScorecard.getTotalOvers()+oversDiff, opponentTeamMatchScorecard.getTotalExtras()+extraDiff, opponentTeamMatchScorecard.getTeam().getId(), matchId);
				
				matchScorecardRepository.updateScoreCard(scoreCard.getRunsScored(),scoreCard.getBallsFaced(),scoreCard.getDismissalType(),scoreCard.getOversBowled(),scoreCard.getExtrasGiven(),scoreCard.getWicketsTaken(),playerId,matchScorecardDetails.getId());
		        
			}else {
				matchScorecardRepository.updateTeamScoreCard((matchScorecard.getTotalRuns()!=null)?matchScorecard.getTotalRuns()+scoreCard.getRunsScored():scoreCard.getRunsScored(), 
						(matchScorecard.getTotalWickets()!=null)?matchScorecard.getTotalWickets():0,
						(matchScorecard.getTotalOvers()!=null)?matchScorecard.getTotalOvers():0.0f,
						(matchScorecard.getTotalExtras()!=null)?matchScorecard.getTotalExtras():0, teamId, matchId);
				matchScorecardRepository.updateTeamScoreCard((opponentTeamMatchScorecard.getTotalRuns()!=null)?opponentTeamMatchScorecard.getTotalRuns():0, 
						(opponentTeamMatchScorecard.getTotalWickets()!=null)?opponentTeamMatchScorecard.getTotalWickets()+scoreCard.getWicketsTaken():scoreCard.getWicketsTaken(),
						(opponentTeamMatchScorecard.getTotalOvers()!=null)?opponentTeamMatchScorecard.getTotalOvers()+scoreCard.getOversBowled():scoreCard.getOversBowled(),
						(opponentTeamMatchScorecard.getTotalExtras()!=null)?opponentTeamMatchScorecard.getTotalExtras()+scoreCard.getExtrasGiven():scoreCard.getExtrasGiven(), 
								opponentTeamMatchScorecard.getTeam().getId(), matchId);
				
				
				matchScorecardRepository.insertScoreCard(scoreCard.getRunsScored(),scoreCard.getBallsFaced(),scoreCard.getDismissalType(),scoreCard.getOversBowled(),scoreCard.getExtrasGiven(),scoreCard.getWicketsTaken(),teamId,matchId,playerId);
			}
		}else {
			if(matchScorecard == null){
				 matchScorecard= new MatchScorecard();
				matchScorecard.setTotalExtras(0);
				matchScorecard.setTotalRuns(0);
				matchScorecard.setTotalOvers(0.0f);
				matchScorecard.setTotalWickets(0);
			}
			matchScorecardRepository.insertTeamScoreCard(scoreCard.getRunsScored(), matchScorecard.getTotalWickets(), matchScorecard.getTotalOvers(), matchScorecard.getTotalRuns(), teamId, matchId);
			matchScorecardRepository.updateTeamScoreCard(opponentTeamMatchScorecard.getTotalRuns(), scoreCard.getWicketsTaken(), scoreCard.getOversBowled(), scoreCard.getExtrasGiven(), opponentTeamMatchScorecard.getTeam().getId(), matchId);
			matchScorecardRepository.insertScoreCard(scoreCard.getRunsScored(),scoreCard.getBallsFaced(),scoreCard.getDismissalType(),scoreCard.getOversBowled(),scoreCard.getExtrasGiven(),scoreCard.getWicketsTaken(),teamId,matchId,playerId);
	        
		}
		
	}
}
