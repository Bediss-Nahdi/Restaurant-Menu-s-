package fr.bediss.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import fr.bediss.entities.Restaurant;
import fr.bediss.exceptions.RessourceNotFoundException;
import fr.bediss.services.RestaurantService;


@RestController
@RequestMapping(value="/restaurants")
public class RestaurantController {
	
	
	@Autowired private RestaurantService restoService;
	
	
//1// Toute la liste des restaurants
	@GetMapping
	public List<Restaurant> findAll(){
		return restoService.findAll();
		
	}
	
	
//2// Un seul restaurant
	@GetMapping("/{id}")
	public Restaurant findById(@PathVariable("id") String identifiant) {
		return restoService.findById(identifiant);
	}
	
	
//3// Créer un Restaurant
	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	public String create(@RequestBody Restaurant restaurant) {
		return restoService.create(restaurant);
	}

//4// Mettre à jour la totalité du Restaurant
	@PutMapping("/{id}")
	@ResponseStatus(value = HttpStatus.OK)
	public void update(@RequestBody Restaurant restaurant, @PathVariable("id") String identifiant) {
		restoService.update(identifiant, restaurant);
	}
	
//5// Mettre à jour partielle du Restaurant 
	@PatchMapping(path = "/{id}")
	@ResponseStatus(value = HttpStatus.OK)
	public void partialUpdate(@RequestBody Map<String, Object> updates, @PathVariable("id") String identifiant) {
		restoService.partialUpdate(identifiant, updates);
	}

	
//6// Supprimer un Restaurant
	@DeleteMapping("/{id}")
	@ResponseStatus(code = HttpStatus.OK)
	public void deleteById(@PathVariable("id") String identifiant) {
		restoService.findById(identifiant);
		restoService.deleteById(identifiant);
	}
}
