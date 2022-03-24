package luisa.almeida.secondrestaurantapi.converters;

import luisa.almeida.secondrestaurantapi.dtos.MenuDto;
import luisa.almeida.secondrestaurantapi.models.Menu;
import luisa.almeida.secondrestaurantapi.services.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MenuDtoToModelConverter {

    @Autowired
    private MenuService service;

    public Menu convertToModel(MenuDto dto) {
        Menu model = (dto.getTransactionId() != null ?
        service.findByUUID(dto.getTransactionId()).get() : new Menu());
        model.setUuid(dto.getTransactionId());
        model.setIsAvailable(dto.getIsAvailable());
        model.setDishName(dto.getDishName());
        model.setStartDate(dto.getStartDate());
        model.setEndDate(dto.getEndDate());
        model.setAmountAvailable(dto.getAmountAvailable());
        model.setActive(dto.getActive());
        return model;
    }
}
