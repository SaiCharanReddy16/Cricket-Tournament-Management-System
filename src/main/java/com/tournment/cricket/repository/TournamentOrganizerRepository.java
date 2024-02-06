package com.tournment.cricket.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tournment.cricket.model.TournamentOrganizer;

@Repository
public interface TournamentOrganizerRepository extends JpaRepository<TournamentOrganizer, Long> {
	List<TournamentOrganizer> findByName(String name);


}

