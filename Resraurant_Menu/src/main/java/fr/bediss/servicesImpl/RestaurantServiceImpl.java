package fr.bediss.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.bediss.entities.Restaurant;
import fr.bediss.exceptions.RessourceNotFoundException;
import fr.bediss.repositories.RestaurantRepository;

@Service
public class RestaurantServiceImpl implements RestaurantService {

	@Autowired
	RestaurantRepository restoRepository;

//1// Récupérer toute la liste des restaurants
	@Override
	public List<Restaurant> findAll() {
		List<Restaurant> liste = new ArrayList<Restaurant>();
		restoRepository.findAll().forEach(liste::add);
		;
		return liste;

	}

//2// Récupérer un seul restaurant
	@Override
	public Restaurant findById(String id) {
		Optional<Restaurant> rest = restoRepository.findById(id);

		if (!rest.isPresent()) {
			throw new RessourceNotFoundException("Votre requête n'a pas aboutit : il n'y a pas de restaurant");
		}
		return rest.get();
	}

//3// Créer un Restaurant
	@Override
	public String create(Restaurant restaurant) {

		return restoRepository.save(restaurant).getId();
	}

//4// Mettre à jour la totalité du restaurant un Restaurant
	@Override
	public void update(String identifiant, Restaurant restaurant) {
		Optional<Restaurant> rest = restoRepository.findById(identifiant);

		if (!rest.isPresent()) {
			throw new RessourceNotFoundException("La mise à jour n'a pas été effectuée");
		} else {
			restaurant.setId(identifiant);
			restoRepository.save(restaurant);
		}
	}

//5// Mettre à jour partielle du Restaurant
	@Override
	public void partialUpdate(String identifiant, Map<String, Object> updates) {
		Restaurant restoToUpadate = restoRepository.findById(identifiant).get();
		
		if (restoToUpadate == null) {
			restoToUpadate = restoRepository.findById(identifiant).get();
			throw new RessourceNotFoundException("Erreur sur la  mise à jour partielle");
		} else {
			for (String key : updates.keySet()) {
				switch (key) {
					case "nom": {
						restoToUpadate.setNom((String) updates.get(key));
						break;
					}
					case "adresse": {
						restoToUpadate.setAdresse((String) updates.get(key));
						break;
					}
				}
			}
			restoRepository.save(restoToUpadate);
		}
	}
	
//6// Supprimer un Restaurant
	@Override
	public void deleteById(String identifiant) {
		restoRepository.deleteById(identifiant);
	}
}
