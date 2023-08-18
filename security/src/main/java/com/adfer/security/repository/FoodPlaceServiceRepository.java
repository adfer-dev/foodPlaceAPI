package com.adfer.security.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.adfer.security.model.FoodPlaceService;

public interface FoodPlaceServiceRepository extends JpaRepository<FoodPlaceService, Integer> {
	Optional<FoodPlaceService> findFoodPlaceServiceById(Integer id);
}
