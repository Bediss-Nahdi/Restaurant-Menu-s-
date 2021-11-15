package fr.bediss.controllers;

import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import fr.bediss.entities.Menus;
import fr.bediss.services.MenuServices;
import fr.bediss.services.RestaurantService;

@RestController
public class MenuController {

	@Autowired public MenuServices menuService;
		
	@Autowired
	public RestaurantService restoService;
	
//1// Remonter une Set de Menus (tous les menus d'UN restaurant)
	@GetMapping("/restaurants/{idResto}/menus")
	public Set<Menus> findAllOfRestaurant(@PathVariable("idResto") String idRestaurant) {
		
		return menuService.findAllOfResturant(idRestaurant);
		
	}
	
	
	

	@GetMapping  ("/menus/{id}")
	public Menus findById(@PathVariable("id") String id) {
		return menuService.findById(id);
	}
	
	@PostMapping("/restaurants/{idResto}/menus")
	@ResponseStatus(code = HttpStatus.CREATED)
	public String create( String idRestaurant, @RequestBody Menus menu) {
		restoService.findById(idRestaurant);
		return menuService.create(idRestaurant, menu);
		
		
	}
	
	
	
	@PatchMapping("/menus/{id}")
	@ResponseStatus(code = HttpStatus.OK)
	public void partialUpdate(@PathVariable("id") String id, @RequestBody Map<String, Object> updates) {
		menuService.findById(id);
		menuService.partialUpdate(id, updates);
	}

	@DeleteMapping("/restaurants/{idResto}/menus/{idMenu}")
	public void delete(@PathVariable("idResto") String idRestaurant, @PathVariable("idMenu") String idMenu) {
		restoService.findById(idRestaurant);
		menuService.findById(idMenu);
		restoService.deleteById(idRestaurant, idMenu);
	}
	
}
