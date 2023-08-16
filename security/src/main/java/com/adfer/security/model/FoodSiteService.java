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
@Table(name = "food-site_service")
public class FoodSiteService {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	@ManyToMany(mappedBy = "services")
	@JsonBackReference
	private Set<FoodSite> foodSites;
	
	public FoodSiteService() {
	}
	
	public FoodSiteService(String name, Set<FoodSite> foodSites) {
		this.name = name;
		this.foodSites = foodSites;
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
	public Set<FoodSite> getFoodSites() {
		return foodSites;
	}
	public void setFoodSites(Set<FoodSite> foodSites) {
		this.foodSites = foodSites;
	}
	
	
}
