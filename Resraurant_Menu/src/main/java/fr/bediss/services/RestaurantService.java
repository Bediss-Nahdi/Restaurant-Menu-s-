package fr.bediss.services;

import java.util.List;
import java.util.Map;

import fr.bediss.entities.Restaurant;

public interface RestaurantService {

//1// Toute la liste des restaurants
	public List<Restaurant> findAll();

//2// Un seul restaurant
	public Restaurant findById(String id);

//3// Créer un Restaurant
	public String create(Restaurant restaurant);

//4// Mettre à jour la totalité du restaurant un Restaurant
	public void update(String identifiant, Restaurant restaurant);

//5// Mettre à jour partielle du Restaurant 
	public void partialUpdate(String identifiant, Map<String, Object> updates);

//6// Supprimer un Restaurant
	public void deleteById(String identifiant);

	public void deleteById(String idRestaurant, String idMenu);

}
