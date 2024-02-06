package com.tournment.cricket.repository;

import com.tournment.cricket.model.MatchScorecard;
import com.tournment.cricket.model.MatchScorecardDetails;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface MatchScorecardRepository extends JpaRepository<MatchScorecard, Long> {

	@Query(value ="select * from match_scorecard ms  left outer join match_scorecard_details msd on ms.id=msd.scorecard_id and ms.match_id=:id inner join players p on p.id=msd.player_Id ",nativeQuery = true )
	List<MatchScorecard> getMatchScoreCardByMatchId(Long id);

	@Transactional
	@Modifying
	@Query(value ="insert into match_scorecard (total_runs,total_wickets,total_overs,total_extras,team_id,match_id ) values (:totalRuns,:totalWickets,:totalOvers,:totalExtras,:teamId,:matchId)",nativeQuery = true )
	void insertTeamScoreCard(Integer totalRuns, Integer totalWickets, Float totalOvers, Integer totalExtras,Long teamId, Long matchId);

	
	
	
	
	
	@Transactional
	@Modifying
	@Query(value ="update match_scorecard set  total_runs=:totalRuns , total_wickets =:totalWickets , total_overs=:totalOvers , total_extras=:totalExtras where  team_id = :teamId and match_id=:matchId",nativeQuery = true )
	void updateTeamScoreCard(Integer totalRuns, Integer totalWickets, Float totalOvers, Integer totalExtras,Long teamId, Long matchId);

	@Query(value ="select * from match_scorecard where match_id =:matchId and team_id = :teamId",nativeQuery = true )
	List<MatchScorecard> getTeamScoreCard(Long teamId, Long matchId);

	
		
	
	@Transactional
	@Modifying
	@Query(value ="insert into match_scorecard_details (runs_Scored,balls_faced,dismissal_type,overs_bowled,extras_given,wickets_taken ,player_id,scoreCard_Id ) values (:runsScored,:ballsFaced,:dismissalType,:oversBowled,:extrasGiven,:wicketsTaken ,:playerId,(select id from match_scorecard where match_id =:matchId and team_id = :teamId))",nativeQuery = true )
	void insertScoreCard(Integer runsScored, Integer ballsFaced, String dismissalType, Float oversBowled,Integer extrasGiven, Integer wicketsTaken, Long teamId, Long matchId, Long playerId);

	@Transactional
	@Modifying
	@Query(value ="update  match_scorecard_details  set runs_Scored=:runsScored,balls_faced=:ballsFaced,dismissal_type=:dismissalType,overs_bowled=:oversBowled,extras_given=:extrasGiven,wickets_taken=:wicketsTaken where player_id=:playerId And scoreCard_Id=:scorecardId ",nativeQuery = true )
	void updateScoreCard(Integer runsScored, Integer ballsFaced, String dismissalType, Float oversBowled,Integer extrasGiven, Integer wicketsTaken,  Long playerId,Long scorecardId);

	
	@Query(value ="select * from match_scorecard where match_id =:matchId and team_id != :teamId",nativeQuery = true )
	
	List<MatchScorecard> getOpponentTeamScoreCard(Long teamId, Long matchId);

}

