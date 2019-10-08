package com.lsr.shopit.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.lsr.shopit.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	@Query("SELECT u from User u where u.email=:email")
	Optional<User> findByUsernameOrEmail(@Param("email") String usernameOrEmail);

	Optional<User> findByEmail(String email);
}