package fr.bediss.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import fr.bediss.entities.Restaurant;


@Repository
public interface RestaurantRepository extends CrudRepository<Restaurant, String>{

}
