package com.spring.game.repo;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.ResponseEntity;

import com.spring.game.model.Admin;

public interface AdminRepo extends JpaRepository<Admin, UUID> {

	Admin deleteBygameid(UUID gameid);

	List<Admin> findBygametitleContainingIgnoreCase(String gametitle);

	List<Admin> findAllBygamecategoryContainingIgnoreCase(String gamecategory);

	@Query(value = "SELECT DISTINCT gamecategory FROM admin_game", nativeQuery = true)
	List<String> findDistinctGameCategories();

	List<Admin> findBygameid(UUID gameid);
}
