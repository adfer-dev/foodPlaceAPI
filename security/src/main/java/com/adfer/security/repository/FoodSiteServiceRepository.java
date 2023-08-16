package com.adfer.security.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.adfer.security.model.FoodSiteService;

public interface FoodSiteServiceRepository extends JpaRepository<FoodSiteService, Integer> {
	Optional<FoodSiteService> findFoodSiteServiceById(Integer id);
}
