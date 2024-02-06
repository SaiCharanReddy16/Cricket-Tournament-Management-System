package com.tournment.cricket.repository;

import com.tournment.cricket.model.PointsTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PointsTableRepository extends JpaRepository<PointsTable, Long> {
}
