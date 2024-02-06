package com.tournment.cricket.repository;

import com.tournment.cricket.model.Venue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface VenueRepository extends JpaRepository<Venue, Long> {
	@Query(value ="select * from venue v  where v.id = :id",nativeQuery = true )
	Venue getVenueById(Long id);

}

