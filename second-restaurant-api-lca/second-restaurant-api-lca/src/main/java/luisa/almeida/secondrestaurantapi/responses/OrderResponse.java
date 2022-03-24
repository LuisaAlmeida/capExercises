package luisa.almeida.secondrestaurantapi.responses;

import luisa.almeida.secondrestaurantapi.dtos.OrderDto;

import java.util.ArrayList;
import java.util.List;

public class OrderResponse extends Response{

    List<OrderDto> resValues = new ArrayList<>();

    public List<OrderDto> getResValues() {
        return resValues;
    }

    public void setResValues(List<OrderDto> resValues) {
        this.resValues = resValues;
    }
}
