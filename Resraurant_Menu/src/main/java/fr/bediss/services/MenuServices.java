package fr.bediss.services;

import java.util.Map;
import java.util.Set;

import fr.bediss.entities.Menus;



public interface MenuServices {
	public Set<Menus> findAllOfResturant(String idRestaurant);

	public Menus findById(String id);

	public String create(String idRestaurant, Menus menu);

	public void deleteById(String idRestaurant, String idMenu);

	void update(String id, Menus menu);

	void partialUpdate(String id, Map<String, Object> updates);

	
	
	
}
