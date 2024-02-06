package com.tournment.cricket.repository;

import com.tournment.cricket.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {

	@Query(value="select * from team t left outer join coach c on t.coach_id = c.id where t.id =:teamId",nativeQuery = true)
	Team getTeamCoachesByTeamId(Long teamId);
@Transactional
@Modifying
@Query(value="update team set team.coach_Id =:coachId where team.id=:teamId",nativeQuery = true)
void updateTeamCoach(Long teamId, Long coachId);

@Transactional
@Modifying
@Query(value="update coach set coach.team_id =:teamId where coach.id=:coachId",nativeQuery = true)

void updateCoach(Long teamId, Long coachId);
}

