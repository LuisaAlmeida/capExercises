package luisa.almeida.secondrestaurantapi.controllers;

import luisa.almeida.secondrestaurantapi.converters.MenuDtoToModelConverter;
import luisa.almeida.secondrestaurantapi.converters.MenuModelToDtoConverter;
import luisa.almeida.secondrestaurantapi.dtos.MenuDto;
import luisa.almeida.secondrestaurantapi.models.Menu;
import luisa.almeida.secondrestaurantapi.responses.MenuResponse;
import luisa.almeida.secondrestaurantapi.services.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/")
public class MenuController {

    @Autowired
    private MenuService service;

    @Autowired
    private MenuDtoToModelConverter dtoToModel;

    @Autowired
    private MenuModelToDtoConverter modelToDto;


    //returns all available menus according to their date
    @GetMapping("/getMenu")
    public MenuResponse getMenu() {
        MenuResponse response = new MenuResponse();
        try {
            List<Menu> menuList = service.findAllBetweenDates(LocalDate.now(), LocalDate.now().plusWeeks(1));
            List<MenuDto> dtoList = new ArrayList<>();
            for (Menu menu : menuList) {
                dtoList.add(modelToDto.convertToDto(menu));
            }
            response.setResValues(dtoList);
            response.generateResponse("OK","200","Here are the available menus by date");
        } catch (Exception e) {
            response.generateResponse("NOK,", "400", "Error: " + e.getMessage());
        } return response;
    }

    //adds a menu to the table
    @PostMapping
    @RequestMapping("/addMenu")
    public MenuResponse addMenu(@RequestBody MenuDto dto) {
        MenuResponse response = new MenuResponse();
        try {
            Menu model = dtoToModel.convertToModel(dto);
            boolean controlFlag = service.addMenu(model);
            if (controlFlag) {
                response.generateResponse("OK", "200", "Menu add Successful");
            } else {
                response.generateResponse("NOK,", "400", "NOT OKAY");
            }
        } catch (Exception e) {
            response.generateResponse("NOK,", "400", "Error: " + e.getMessage());
        }
        return response;
    }

    //makes alterations on the menus
    @PostMapping
    @RequestMapping("/updateMenu")
    public MenuResponse updateMenu(@RequestBody MenuDto dto) {
        //TODO isBlank() passar para isEmpty()
        MenuResponse response = new MenuResponse();
        try{
            if(dto.getTransactionId() == null || dto.getStartDate() == null ||
                    dto.getEndDate() == null || dto.getDishName().isEmpty()) {
               response.generateResponse("NOK,", "400", "NOT OKAY");
           }
            Menu updateMenu = dtoToModel.convertToModel(dto);
            boolean controlFlag = service.updateMenu(updateMenu);
            if(controlFlag){
                response.generateResponse("OK","200","Menu Updated");
            } else {
                response.generateResponse("NOK,", "400", "NOT OKAY");
            }

        } catch (Exception e) {
            response.generateResponse("NOK,", "400", "Error: " + e.getMessage());
        } return response;
    }

    @DeleteMapping("/{id}")
    public MenuResponse deleteMenu(@PathVariable(value = "id") @RequestBody Integer id) {
        MenuResponse response = new MenuResponse();
        Optional<Menu> optionalMenu = service.findById(id);
        try {
            if(optionalMenu.isPresent()) {
                service.delete(optionalMenu.get());
                response.generateResponse("OK","200","Menu deleted successfully");
            }
        } catch (Exception e) {
            response.generateResponse("NOK", "400", "Id not found");
        }
        return response;
    }

}

