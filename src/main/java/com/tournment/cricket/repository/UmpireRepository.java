package com.tournment.cricket.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tournment.cricket.model.Umpire;

@Repository
public interface UmpireRepository extends JpaRepository<Umpire, Long> {

}
