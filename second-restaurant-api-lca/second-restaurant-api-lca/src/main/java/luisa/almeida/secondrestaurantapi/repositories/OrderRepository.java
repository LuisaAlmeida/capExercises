package luisa.almeida.secondrestaurantapi.repositories;

import luisa.almeida.secondrestaurantapi.models.Order;
import luisa.almeida.secondrestaurantapi.models.PurchaseStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Integer> {

    public Optional<Order> findByCustomerName(String customerName);
    public Optional<Order> findByOrderDate(LocalDate orderDate);
    public Optional<Order> findByDeliveryAddress(String deliveryAddress);
    public Optional<Order> findByDishName(String dishName);
    public Optional<Order> findByQuantity(Integer quantity);
    public Optional<Order> findByUuid(String uuid);
    public Optional<Order> findById(Integer id);


}
