package luisa.almeida.restaurant_api_lca.repository;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import luisa.almeida.restaurant_api_lca.model.Order;
import luisa.almeida.restaurant_api_lca.model.PurchaseStatus;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer>{
	
	public List<Order> findByCustomerName(String customerName);
	public List<Order> findByDate(Date date);
	public List<Order> findByDeliveryAddress(String deliveryAddress);
	public List<Order> findByDishName(String dishName);
	public Optional<Order> findById(Integer id);
	public List<Order> findByPurchaseStatus(PurchaseStatus purchaseStatus);
	public List<Order> findByQuantity(Integer quantity);
	public List<Order> findByTransactionID(String transactionID);

}
