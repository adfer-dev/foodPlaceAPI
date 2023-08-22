package com.adfer.security.model;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "food-kind")
public class FoodKind {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@NotBlank(message = "A name must be provided.")
	private String name;
	private String description;
	@ManyToMany(mappedBy = "foodKinds")
	@JsonBackReference
	private Set<FoodPlace> foodPlaces;

	public FoodKind() {
	}

	public FoodKind(String name, String description, Set<FoodPlace> foodSites) {
		this.name = name;
		this.description = description;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<FoodPlace> getFoodPlaces() {
		return foodPlaces;
	}

	public void setFoodPlaces(Set<FoodPlace> foodPlaces) {
		this.foodPlaces = foodPlaces;
	}




}
