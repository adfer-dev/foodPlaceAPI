package com.adfer.security.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.adfer.security.model.FoodPlaceKind;

public interface FoodPlaceKindRepository extends JpaRepository<FoodPlaceKind, Integer> {
	Optional<FoodPlaceKind> findFoodPlaceKindById (Integer id);
}
