package com.tournment.cricket.repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tournment.cricket.model.Match;

@Repository
public interface MatchRepository extends JpaRepository<Match, Long> {

	@Transactional
	@Modifying
	@Query(value="insert into MatchCR (match_Type,match_date,match_time,venue_id,umpire_Id,tournament_id,team_1_id,team_2_id) values(:matchType,:matchDate,:matchTime,:venueId,:umpireId,:tournamentId,:team1Id,:team2Id)",nativeQuery = true)
	void createNewMatch(String matchType,LocalDate matchDate , LocalTime matchTime , Long venueId, Long umpireId, Long tournamentId, Long team1Id, Long team2Id);
	
	@Query(value="select * from MatchCR m inner join team t1 on m.team_1_id = t1.id inner join team t2 on m.team_2_id = t2.id",nativeQuery = true)
	List<Match> getAllMatches();
	

	@Query(value="select * from MatchCR m inner join team t1 on m.team_1_id = t1.id inner join team t2 on m.team_2_id = t2.id inner join umpire u on u.id = m.umpire_id inner join tournament t on t.id=m.tournament_id inner join venue v on v.id=m.venue_id left outer join players p  on  p.team_id= t1.id  left outer Join match_Scorecard ms on ms.match_id =m.id left outer Join match_Scorecard_details msd on msd.scorecard_Id =ms.id and msd.player_Id=p.id    where m.id = :id",nativeQuery = true)
	 Match getMatchDetails(Long id);

	@Query(value="select * from MatchCR m  where id=:matchId",nativeQuery = true)
	Match getMatch(Long matchId);
}

