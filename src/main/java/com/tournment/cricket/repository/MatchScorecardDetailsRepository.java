package com.tournment.cricket.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.tournment.cricket.model.MatchScorecardDetails;

@Repository
public interface MatchScorecardDetailsRepository extends JpaRepository<MatchScorecardDetails, Long> {
@Query(value = "select * from players p left outer join match_scorecard_details msd on p.id =msd.player_id inner join match_scorecard ms on msd.scorecard_id = ms.id where ms.team_id = :teamId and ms.match_Id=:matchId and msd.player_id=:playerId" ,nativeQuery = true)
List<MatchScorecardDetails> getMatchScorecardDetails(Long matchId , Long teamId ,Long playerId);
@Query(value = "select * from players p left outer join match_scorecard_details msd on p.id =msd.player_id left outer join match_scorecard ms on msd.scorecard_id = ms.id where ms.match_Id=:matchId " ,nativeQuery = true)

List<MatchScorecardDetails> getMatchScorecardDetails(Long matchId);

}
