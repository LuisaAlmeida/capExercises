package luisa.almeida.restaurant_api_lca.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import luisa.almeida.restaurant_api_lca.model.Order;
import luisa.almeida.restaurant_api_lca.model.PurchaseStatus;
import luisa.almeida.restaurant_api_lca.repository.OrderRepository;

@Service
public class OrderService {
	
	@Autowired
	private OrderRepository repository;
	
	
	public List<Order> getOrder() {
		return repository.findAll();
	}
	
	@Transactional
	public Order addOrder(Order model) {
		return repository.save(model);
	}

	public Order findById(Integer id) {
		Optional<Order> optional = repository.findById(id);
		if(optional.isPresent()) {
			return optional.get();
		} else {
			return null;
		}
	}
	
	@Transactional
	public Order save(Order order) {
		return repository.saveAndFlush(order);
	}
	
	public PurchaseStatus getPurchaseStatus(Integer id) {
		Order order = repository.getById(id);
		return order.getPurchaseStatus();
	}

}
