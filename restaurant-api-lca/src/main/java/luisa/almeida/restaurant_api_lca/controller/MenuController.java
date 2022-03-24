package luisa.almeida.restaurant_api_lca.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import luisa.almeida.restaurant_api_lca.data_transfer.ResponseMenu;
import luisa.almeida.restaurant_api_lca.dto.MenuDto;
import luisa.almeida.restaurant_api_lca.model.Menu;
import luisa.almeida.restaurant_api_lca.service.MenuService;
import luisa.almeida.restaurant_api_lca.data_transfer.Converter;

@RestController
@RequestMapping("/api/v1/")
public class MenuController {

	@Autowired
	private MenuService service;
	
	@Autowired
	private Converter converter;
	

	@GetMapping("/getMenu")
	public ResponseMenu getMenu() {
		ResponseMenu response = new ResponseMenu("OK", "200", "Menu get Success");
		List<Menu> menuList = service.getMenu();
		List<MenuDto> menuDtoList = new ArrayList<>();
		for(Menu menu : menuList) {
			menuDtoList.add(converter.convertToDto(menu, new MenuDto()));
		}
		response.setResValues(menuDtoList);
		return response;
	}

	@PostMapping
	@RequestMapping("/addMenu")
	//TODO fazer 1 único return, criar response fora do try, linha 49 fazer sets como quero..idem 52....return abaixo da linha 64
	public ResponseMenu addMenu(@RequestBody MenuDto dto) {
		try {
			if(dto.getDishName().isBlank() || dto.getStartSellingPeriod() == null || dto.getEndDate() == null ) {
			ResponseMenu badResponseMenu = new ResponseMenu("NOK", "400", "Bad request");
			return badResponseMenu;
			} 
			Menu menu = new Menu();
			menu = converter.convertToModel(dto, menu);
			service.addMenu(menu);
			ResponseMenu responseOk = new ResponseMenu(
					"OK", "200", "Menu add Success");
			return responseOk;

		} catch (Exception e) {
			ResponseMenu badResponse = new ResponseMenu(
					"NOK", "500", "Error: Fetch the Error");
			badResponse.setMsg("OK");
			return badResponse;
		}
		//TODO RETURN
	}

	@PostMapping
	@RequestMapping("/updateMenu")
	public ResponseMenu updateMenu(@RequestBody MenuDto dto) {
		try {
			Pair<Menu, Boolean> menu = service.findById(dto.getId());
			if(menu.getSecond()) { 
				Menu newMenu = 	menu.getFirst();
				newMenu = converter.convertToModel(dto, newMenu);
				service.addMenu(newMenu);
				ResponseMenu responseOk = new ResponseMenu(
						"OK", "200", "Menu update Success");
				return responseOk;
			} else {
				ResponseMenu badResponse = new ResponseMenu(
						"NOK", "500", "Error: Fetch the Error");
				badResponse.setMsg("OK");
				return badResponse;
			}
		} catch (Exception e) {
			ResponseMenu badResponse = new ResponseMenu(
					"NOK", "500", "Error: Fetch the Error");
			badResponse.setMsg("OK");
			return badResponse;
		}
	}

}