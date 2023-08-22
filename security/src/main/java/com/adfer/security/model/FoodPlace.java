package com.adfer.security.model;

import java.util.Set;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "food-place")
public class FoodPlace {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@NotBlank(message = "A name must be provided.")
	private String name;
	@Embedded
	private FoodPlaceContact contact;
	@ManyToMany
	@JoinTable(
			name = "food-place_food-kind",
			joinColumns = @JoinColumn(name = "food-place_id"),
			inverseJoinColumns = @JoinColumn(name = "food-kind_id")
	)
	private Set<FoodKind> foodKinds;
	@ManyToMany
	@JoinTable(
			name = "food-place_place-service",
			joinColumns = @JoinColumn(name = "food-place_id"),
			inverseJoinColumns = @JoinColumn(name = "place-service_id")
	)
	private Set<FoodPlaceService> services;
	@ManyToMany
	@JoinTable(
			name = "food-place_place-kind",
			joinColumns = @JoinColumn(name = "food-place_id"),
			inverseJoinColumns = @JoinColumn(name = "place-kind_id")
	)
	private Set<FoodPlaceKind> kinds;
	
	
	public FoodPlace() {
	}
	
	public FoodPlace(String name, FoodPlaceContact contact,
			Set<FoodKind> foodKinds, Set<FoodPlaceService> services, Set<FoodPlaceKind> kinds) {
		super();
		this.name = name;
		this.contact = contact;
		this.foodKinds = foodKinds;
		this.services = services;
		this.kinds = kinds;
	}
	
	// GETTERS && SETTERS
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

	public FoodPlaceContact getContact() {
		return contact;
	}

	public void setContact(FoodPlaceContact contact) {
		this.contact = contact;
	}

	public Set<FoodKind> getFoodKinds() {
		return foodKinds;
	}
	public void setFoodKinds(Set<FoodKind> foodKinds) {
		this.foodKinds = foodKinds;
	}
	public Set<FoodPlaceService> getServices() {
		return services;
	}
	public void setServices(Set<FoodPlaceService> services) {
		this.services = services;
	}

	public Set<FoodPlaceKind> getKinds() {
		return kinds;
	}

	public void setKinds(Set<FoodPlaceKind> kinds) {
		this.kinds = kinds;
	}

}
