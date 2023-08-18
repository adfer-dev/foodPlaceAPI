package com.adfer.security.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.adfer.security.model.FoodPlace;

public interface FoodPlaceRepository extends JpaRepository<FoodPlace, Integer> {
	Optional<FoodPlace> findFoodPlaceById(Integer id);
}
