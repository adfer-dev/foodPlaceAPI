package com.adfer.security.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.adfer.security.model.FoodSite;

public interface FoodSiteRepository extends JpaRepository<FoodSite, Integer> {
	Optional<FoodSite> findFoodSiteById(Integer id);
}
