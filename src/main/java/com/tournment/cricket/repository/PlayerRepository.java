package com.tournment.cricket.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tournment.cricket.model.Player;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {
		
	@Query(value ="select * from Players p inner join Team t where p.team_Id = t.id and t.name=:teamName",nativeQuery = true )
	List<Player> findPlayersByTeamName(String teamName);

	@Query(value ="select * from Players p inner join Team t where p.team_Id = t.id and t.id = :teamId",nativeQuery = true )
	List<Player> findByTeamId(Long teamId);
	@Transactional
	@Modifying(clearAutomatically = true)
	@Query(value ="update players  set team_Id =:newTeamId where id = :id",nativeQuery = true )
	void exchangePlayer(Long id, Long newTeamId);
	@Transactional
	@Modifying(clearAutomatically = true)
	@Query(value ="update Team  set captain_id =:captainId where id = :id",nativeQuery = true )
	void updateTeamCaptain(Long id, Long captainId);


	

}
