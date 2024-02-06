package com.tournment.cricket.repository;

import com.tournment.cricket.model.Tournament;
import com.tournment.cricket.model.TournamentOrganizer;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TournamentRepository extends JpaRepository<Tournament, Long> {
List<Tournament> findByName(String name); 
List<Tournament> findByOrganizer(TournamentOrganizer organizer);
List<Tournament> findByLocation(String location);
List<Tournament> findByStartDate(LocalDate startDate);
}
