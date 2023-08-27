package com.adfer.security.service;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.springframework.stereotype.Service;
import com.adfer.security.model.AddFoodPlaceRequest;
import com.adfer.security.model.FoodKind;
import com.adfer.security.model.FoodPlace;
import com.adfer.security.model.FoodPlaceKind;
import com.adfer.security.model.FoodPlaceService;
import com.adfer.security.model.FoodPlaceContact;
import com.adfer.security.repository.FoodKindRepository;
import com.adfer.security.repository.FoodPlaceKindRepository;
import com.adfer.security.repository.FoodPlaceRepository;
import com.adfer.security.repository.FoodPlaceServiceRepository;

@Service
public class FoodPlaceServ {
	private final FoodPlaceRepository foodPlaceRepository;
	private final FoodKindRepository foodKindRepository;
	private final FoodPlaceServiceRepository foodPlaceServiceRepository;
	private final FoodPlaceKindRepository foodPlaceKindRepository;
	
	public FoodPlaceServ(FoodPlaceRepository foodSiteRepository, FoodKindRepository foodKindRepository,
			FoodPlaceServiceRepository foodSiteServiceRepository, FoodPlaceKindRepository foodPlaceKindRepository) {
		this.foodPlaceRepository = foodSiteRepository;
		this.foodKindRepository = foodKindRepository;
		this.foodPlaceServiceRepository = foodSiteServiceRepository;
		this.foodPlaceKindRepository = foodPlaceKindRepository;
	}
	
	public List<FoodPlace> getAllFoodPlaces () {
		return foodPlaceRepository.findAll();
	}
	
	public Optional<FoodPlace> getFoodPlace (Integer id){
		return foodPlaceRepository.findById(id);
	}
	
	public FoodPlace addFoodPlace (AddFoodPlaceRequest request) {
		 Set<FoodKind> foodKinds = new HashSet<FoodKind>();
		 Set<FoodPlaceService> foodSiteServices = new HashSet<FoodPlaceService>();
		 Set<FoodPlaceKind> foodPlaceKinds = new HashSet<FoodPlaceKind>();
		 DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
		 
		 request.getFoodKinds().forEach(foodKindId -> {
			 foodKinds.add(foodKindRepository.findFoodKindById(foodKindId).get());
		 });
		 
		request.getServices().forEach(foodServicesId -> {
			foodSiteServices.add(foodPlaceServiceRepository.findFoodPlaceServiceById(foodServicesId).get());
		});
		
		request.getKinds().forEach(placeKindId -> {
			foodPlaceKinds.add(foodPlaceKindRepository.findFoodPlaceKindById(placeKindId).get());
		});		
		FoodPlace foodPlace = new FoodPlace(
				request.getName(),
				new FoodPlaceContact(request.getAddress(),
						request.getTelephoneNumber(),
						request.getWebsiteUrl(),
						LocalTime.parse(request.getOpeningTime(), timeFormatter),
						LocalTime.parse(request.getClosingTime(), timeFormatter),
						request.getOpeningWeekDays())
				, foodKinds, foodSiteServices, foodPlaceKinds);
		
		foodPlaceRepository.save(foodPlace);
		
		return foodPlace;
	}
	
	public FoodPlace updateFoodPlace (AddFoodPlaceRequest request, Integer id) {
		FoodPlace updateFoodPlace = foodPlaceRepository.findById(id).get();
		
		Optional.ofNullable(request.getName())
			.ifPresent(updateFoodPlace::setName);
		
		Optional.ofNullable(request.getAddress())
			.ifPresent(address -> updateFoodPlace.getContact().setAddress(address));
		
		Optional.ofNullable(request.getTelephoneNumber())
			.ifPresent(telephoneNumber -> updateFoodPlace.getContact().setTelephoneNumber(telephoneNumber));
		
		Optional.ofNullable(request.getWebsiteUrl())
		.ifPresent(websiteUrl -> updateFoodPlace.getContact().setWebsiteUrl(websiteUrl));
		
		Optional.ofNullable(request.getOpeningTime())
			.map(LocalTime::parse)
			.ifPresent(openingTime -> updateFoodPlace.getContact().setOpeningTime(openingTime));
		
		Optional.ofNullable(request.getClosingTime())
			.map(LocalTime::parse)
			.ifPresent(closingTime -> updateFoodPlace.getContact().setClosingTime(closingTime));
		
		Optional.ofNullable(request.getOpeningWeekDays())
			.ifPresent(openingWeekdays -> updateFoodPlace.getContact().setOpeningWeekDays(openingWeekdays));
		
		Optional.ofNullable(request.getFoodKinds())
			.ifPresent(foodKinds -> {
				Set<FoodKind> newFoodKinds = new HashSet<FoodKind>();
				
				foodKinds.forEach(foodKind -> {
					newFoodKinds.add(foodKindRepository.findById(foodKind).get());
				});
			});
		
		Optional.ofNullable(request.getServices())
		.ifPresent(services -> {
			Set<FoodPlaceService> newServices = new HashSet<FoodPlaceService>();
			
			services.forEach(foodKind -> {
				newServices.add(foodPlaceServiceRepository.findById(foodKind).get());
			});
		});
		
		Optional.ofNullable(request.getKinds())
		.ifPresent(kinds -> {
			Set<FoodPlaceKind> newKinds = new HashSet<FoodPlaceKind>();
			
			kinds.forEach(foodKind -> {
				newKinds.add(foodPlaceKindRepository.findById(foodKind).get());
			});
		});
		
		foodPlaceRepository.save(updateFoodPlace);
		
		return updateFoodPlace;
		
	}
	
	public void deleteFoodPlace (Integer id) {
		foodPlaceRepository.deleteById(id);
	}
	
	
	public boolean foodPlaceExists (Integer id) {
		return foodPlaceRepository.existsById(id);
	}
}
