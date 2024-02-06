package com.tournment.cricket.repository;

import com.tournment.cricket.model.Captain;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface CaptainRepository extends JpaRepository<Captain, Long> {
@Query(value="select * from captain where team_id=:teamId",nativeQuery = true)
	List<Captain> findByTeamId(Long teamId);
@Transactional
@Modifying
@Query(value ="update captain  set name =:name where id = :id",nativeQuery = true )
void updateCaptain(Long id, String name);
@Transactional
@Modifying(clearAutomatically = true)
@Query(value ="insert into captain (name,team_Id)values (:captainName,:teamId)",nativeQuery = true )
void insertCaptain(Long teamId, String captainName);
}

