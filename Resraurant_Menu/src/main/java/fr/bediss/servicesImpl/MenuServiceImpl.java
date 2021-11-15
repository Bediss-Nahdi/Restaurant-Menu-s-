package fr.bediss.servicesImpl;

import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.bediss.entities.Menus;
import fr.bediss.entities.Restaurant;
import fr.bediss.repositories.MenuRepository;
import fr.bediss.repositories.RestaurantRepository;
import fr.bediss.services.MenuServices;


@Service
public class MenuServiceImpl implements MenuServices{
	
	
	@Autowired
	private RestaurantRepository restoRpository;
	
	@Autowired
	private MenuRepository menuRepository;

	@Override
	public Set<Menus> findAllOfResturant(String idRestaurant) {
		return restoRpository.findById(idRestaurant).get().getMenus();
	}
	
	@Override
	public Menus findById(String id) {
		return menuRepository.findById(id).get();
	}

	@Override
	public String create(String idRestaurant, Menus menu) {
		Restaurant restoEntity = restoRpository.findById(idRestaurant).get();
		restoEntity.getMenus().add(menu);
		restoRpository.save(restoEntity);
		
		
		Menus menuEntity = restoEntity.getMenus().stream().filter(m -> m.equals(menu)).findFirst().get();
		return menuEntity.getIdentifiant();
	}

	
	@Override
	public void update(String id, Menus menu) {
		menu.setIdentifiant(id);
		menuRepository.save(menu);
	}

	
	@Override
	public void partialUpdate(String id, Map<String, Object> updates) {
		Menus menuToUpdate = menuRepository.findById(id).get();
		for (String key : updates.keySet()) {
			switch (key) {
			case "entrees": {
				menuToUpdate.setEntrees((String) updates.get(key));
				break;
			}
			case "plats": {
				menuToUpdate.setPlats((String) updates.get(key));
				break;
			}
			case "desserts": {
				menuToUpdate.setDesserts((String) updates.get(key));
				break;
			}
			}
			
		}
		menuRepository.save(menuToUpdate);
	}

	@Override
	public void deleteById(String idRestaurant, String idMenu) {
		Restaurant restoToUpdate = restoRpository.findById(idRestaurant).get();
		Set<Menus> menus = restoToUpdate.getMenus();
		Menus menu = menus.stream().filter(m -> m.getIdentifiant().equals(idMenu)).findFirst().get();
		menus.remove(menu);
		restoToUpdate.setMenus(menus);
		restoRpository.save(restoToUpdate);
		menuRepository.delete(menu);
	}
	
	
	
	
	

	
	
}
