package com.tournment.cricket.repository;

import com.tournment.cricket.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
	@Query(value="select * from User u where u.email=:email and u.password=:password",nativeQuery = true)
    Optional<User> findByEmailAndPassword(String email,String password);

	Optional<User> findByEmail(String email);
}

