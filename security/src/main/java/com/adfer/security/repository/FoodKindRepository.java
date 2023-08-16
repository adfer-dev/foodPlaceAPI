package com.adfer.security.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.adfer.security.model.FoodKind;

public interface FoodKindRepository extends JpaRepository<FoodKind, Integer>{
	Optional<FoodKind> findFoodKindById (Integer id);
}
