package luisa.almeida.restaurant_api_lca.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;
import luisa.almeida.restaurant_api_lca.model.Menu;
import luisa.almeida.restaurant_api_lca.repository.MenuRepository;

@Service
public class MenuService {

	@Autowired
	private MenuRepository repository;


	public List<Menu> getMenu() {
		List<Menu> menuList = repository.findAll();
		for(Menu menu : menuList) {
			if(!menu.getIsAvailable()) {
				menuList.remove(menu);
			} 
		}
		return menuList;
	}

	@Transactional
	public Menu addMenu(Menu model) {
		return repository.save(model);
	}


	@Transactional
	public void delete(Menu model) {
		repository.delete(model);
	}


	public Pair<Menu, Boolean> findById(Integer id) {
		try {
			Optional<Menu> menuOptional = repository.findById(id);
			if(menuOptional.isPresent() ) {
				return Pair.of(menuOptional.get(), true);
			} else {
				return Pair.of(new Menu(), false);
			}
		} catch (Exception e) {
			return Pair.of(new Menu(), false);
		}
	}

}


