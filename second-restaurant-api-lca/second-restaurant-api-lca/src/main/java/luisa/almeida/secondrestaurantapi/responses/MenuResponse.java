package luisa.almeida.secondrestaurantapi.responses;

import luisa.almeida.secondrestaurantapi.dtos.MenuDto;

import java.util.ArrayList;
import java.util.List;

public class MenuResponse extends Response{

    List<MenuDto> resValues = new ArrayList<>();

    public List<MenuDto> getResValues() {
        return resValues;
    }

    public void setResValues(List<MenuDto> resValues) {
        this.resValues = resValues;
    }
}
