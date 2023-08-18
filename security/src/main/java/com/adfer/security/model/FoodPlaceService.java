package com.adfer.security.model;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "food-place_service")
public class FoodPlaceService {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	@ManyToMany(mappedBy = "services")
	@JsonBackReference
	private Set<FoodPlace> foodPlaces;
	
	public FoodPlaceService() {
	}
	
	public FoodPlaceService(String name, Set<FoodPlace> foodSites) {
		this.name = name;
		this.foodPlaces = foodSites;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public Set<FoodPlace> getFoodPlaces() {
		return foodPlaces;
	}

	public void setFoodPlaces(Set<FoodPlace> foodPlaces) {
		this.foodPlaces = foodPlaces;
	}

}
