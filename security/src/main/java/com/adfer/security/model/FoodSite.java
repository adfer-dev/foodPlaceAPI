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
@Table(name = "food-site")
public class FoodSite {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Embedded
	private FoodSiteContact contact;
	@ManyToMany
	@JoinTable(
			name = "food-site_food-kind",
			joinColumns = @JoinColumn(name = "food-site_id"),
			inverseJoinColumns = @JoinColumn(name = "food-kind_id")
	)
	private Set<FoodKind> foodKinds;
	@ManyToMany
	@JoinTable(
			name = "food-site_site-service",
			joinColumns = @JoinColumn(name = "food-site_id"),
			inverseJoinColumns = @JoinColumn(name = "site-service_id")
	)
	private Set<FoodSiteService> services;
	
	public FoodSite() {
	}
	
	
	public FoodSite(FoodSiteContact contact, Set<FoodKind> foodKinds, Set<FoodSiteService> services) {
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
	public Set<FoodSiteService> getServices() {
		return services;
	}
	public void setServices(Set<FoodSiteService> services) {
		this.services = services;
	}
	
	
}
