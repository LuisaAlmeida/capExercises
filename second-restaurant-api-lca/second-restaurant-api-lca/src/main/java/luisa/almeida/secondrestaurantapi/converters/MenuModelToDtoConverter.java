package luisa.almeida.secondrestaurantapi.converters;

import luisa.almeida.secondrestaurantapi.dtos.MenuDto;
import luisa.almeida.secondrestaurantapi.models.Menu;
import luisa.almeida.secondrestaurantapi.services.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MenuModelToDtoConverter {

    @Autowired
    MenuService service;

    public MenuDto convertToDto(Menu model){
        MenuDto dto = new MenuDto();
        dto.setTransactionId(model.getUuid());
        dto.setDishName(model.getDishName());
        dto.setEndDate(model.getEndDate());
        dto.setStartDate(model.getStartDate());
        dto.setIsAvailable(model.getIsAvailable());
        dto.setAmountAvailable(model.getAmountAvailable());
        return dto;
    }
}
