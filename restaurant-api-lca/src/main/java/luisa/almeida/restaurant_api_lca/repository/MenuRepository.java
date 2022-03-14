package luisa.almeida.restaurant_api_lca.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import luisa.almeida.restaurant_api_lca.model.Menu;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Integer>{

	public List<Menu> findByDishName(String dishName);
	public List<Menu> findByIsAvailable(Boolean isAvailable);
}
