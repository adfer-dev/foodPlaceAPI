package com.adfer.security.model;

import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "food-place")
public class FoodPlace {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Embedded
	private FoodSiteContact contact;
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
	
	public FoodPlace() {
	}
	
	
	public FoodPlace(FoodSiteContact contact, Set<FoodKind> foodKinds, Set<FoodPlaceService> services) {
		this.contact = contact;
		this.foodKinds = foodKinds;
		this.services = services;
	}


	// GETTERS && SETTERS
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public FoodSiteContact getContact() {
		return contact;
	}

	public void setContact(FoodSiteContact contact) {
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
	
	
}
