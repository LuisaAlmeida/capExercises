package luisa.almeida.secondrestaurantapi.services;

import luisa.almeida.secondrestaurantapi.models.Menu;
import luisa.almeida.secondrestaurantapi.repositories.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.*;

@Service
public class MenuService {

    @Autowired
    private MenuRepository repository;

    //adds a menu to the table
    @Transactional
    public Boolean addMenu(Menu model) {
        Boolean check = false;
        try {
            model.setUuid(UUID.randomUUID().toString());
            repository.saveAndFlush(model);
            check = true;
        } catch (Exception e) {
            check = false;
        }
        return check;
    }

    //delete menu
    @Transactional
    public void delete(Menu model) {
        repository.delete(model);
    }

    //Updates the menu if the provided id matches
    @Transactional
    public Boolean updateMenu(Menu model) {
        boolean check = false;
        try {
            Menu toUpdateMenu = repository.getById(model.getId());
            toUpdateMenu.setActive(model.getActive());
            toUpdateMenu.setStartDate(model.getStartDate());
            toUpdateMenu.setEndDate(model.getEndDate());
            toUpdateMenu.setIsAvailable(model.getIsAvailable());
            toUpdateMenu.setDishName(model.getDishName());
            toUpdateMenu.setAmountAvailable(model.getAmountAvailable());
            repository.saveAndFlush(toUpdateMenu);
            check = true;
        } catch (Exception e) {
            check = false;
        }
        return check;
    }

    //returns all the menus
    public List<Menu> findAll() {
       return repository.findAll();
    }

    //finds the menu's by their id
    public Optional<Menu> findById(Integer id) {
        Optional<Menu> optionalMenu = Optional.empty();
        try{
            optionalMenu = repository.findById(id);
        } catch (Exception e) {
            optionalMenu = Optional.empty();
        }
        return optionalMenu;
    }

    //finds the menu's by their uuid
    public Optional<Menu> findByUUID(String uuid) {
        try{
            Optional<Menu> optionalMenu = repository.findByUuid(uuid);
            return optionalMenu;
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    //finds the menu according to the dish plate
    public Optional<Menu> findByDish(String dishName) {
        try {
            Optional<Menu> optionalMenu = repository.findByDishName(dishName);
            return optionalMenu;
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    //finds all available menus according to their dates
    public List<Menu> findAllBetweenDates(LocalDate startDate, LocalDate endDate) {
        List<Menu> availableMenusByDate = repository.findAllBetweenDates(startDate, endDate);
        if (!availableMenusByDate.isEmpty()) {
            return availableMenusByDate;
        } else {
            return new ArrayList();
        }
    }

    //finds all Available menus
    public List<Menu> findByIsAvailable() {
        List<Menu> menuList = repository.findAll();
        menuList.removeIf(menu -> !menu.getIsAvailable());
        return menuList;
    }

}
