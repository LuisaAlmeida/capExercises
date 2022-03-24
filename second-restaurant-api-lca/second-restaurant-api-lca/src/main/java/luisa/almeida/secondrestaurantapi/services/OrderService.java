package luisa.almeida.secondrestaurantapi.services;

import luisa.almeida.secondrestaurantapi.models.Menu;
import luisa.almeida.secondrestaurantapi.models.Order;
import luisa.almeida.secondrestaurantapi.models.PurchaseStatus;
import luisa.almeida.secondrestaurantapi.repositories.OrderRepository;
import luisa.almeida.secondrestaurantapi.util.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class OrderService {

    @Autowired
    private OrderRepository repository;

    @Autowired
    private MenuService menuService;

    //returns all the orders
    public List<Order> getOrder() {
        return repository.findAll();
    }

    //adds an order to the table
    @Transactional
    public Pair<Boolean, String> addOrder(Order order) {
        Pair<Boolean, String> pair = new Pair<>();
        Boolean check = false;
        try{
            Optional<Menu> menuOptional = menuService.findByDish(order.getDishName());
            Menu menu = new Menu();
            if(menuOptional.isPresent() && menuOptional.get().getActive() == true &&
                    menuOptional.get().getAmountAvailable() - order.getQuantity() >= 0) {
                menu = menuOptional.get();
                menu.setAmountAvailable(menu.getAmountAvailable() - order.getQuantity());
                check = true;
                order.setUuid(UUID.randomUUID().toString());
                System.out.println(order.getPurchaseStatus() + order.getUuid() + order.getOrderDate()
                        + order.getQuantity() + order.getDishName() + order.getCustomerName() + order.getDeliveryAddress()
                        + order.getId());
                repository.saveAndFlush(order);
                pair._first = check;
                pair._second = "Order added successfully";
                if (check && !menuService.updateMenu(menu)) {
                    check = false;
                    pair._first = check;
                    pair._second = "Problem ocurred";
                }
            } else {
                pair._first = check;
                pair._second = "Article not available";
            }
        } catch (Exception e) {
            pair._first = false;
            pair._second = "ERROR :" + e.getMessage();
        }
        return pair;
    }

    @Transactional
    public Boolean updateOrder(Order model) {
        boolean check = false;
        try{
            Order toUpdateOrder = repository.getById(model.getId());
            toUpdateOrder.setOrderDate(model.getOrderDate());
            toUpdateOrder.setDishName(model.getDishName());
            toUpdateOrder.setCustomerName(model.getCustomerName());
            toUpdateOrder.setDeliveryAddress(model.getDeliveryAddress());
            toUpdateOrder.setPurchaseStatus(model.getPurchaseStatus());
            repository.saveAndFlush(toUpdateOrder);
            check = true;
        } catch (Exception e) {
            check = false;
        }
        return check;
    }

    //finds the orders by their id
    public Optional<Order> findById(Integer id) {
        Optional<Order> optionalOrder = Optional.empty();
        try {
            optionalOrder = repository.findById(id);
        } catch (Exception e) {
            optionalOrder = Optional.empty();
        }
        return optionalOrder;
    }

    public Optional<Order> findByUuid(String uuid) {
        try {
            Optional<Order> optionalOrder = repository.findByUuid(uuid);
            return optionalOrder;
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    //finds the orders by the customer's name
    public Optional<Order> findByCustomerName(String customerName) {
        try {
            Optional<Order> optionalOrder = repository.findByCustomerName(customerName);
            return optionalOrder;
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    //finds the orders by the dish's name
    public Optional<Order> findByDishName(String dishName) {
        try {
            Optional<Order> optionalOrder = repository.findByDishName(dishName);
            return optionalOrder;
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    //finds the orders by their delivery address
    public Optional<Order> findByDeliveryAddress(String deliveryAddress){
        try {
            Optional<Order> optionalOrder = repository.findByDeliveryAddress(deliveryAddress);
            return optionalOrder;
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    //finds the orders by their quantity
    public Optional<Order> findByQuantity(Integer quantity) {
        try {
            Optional<Order> optionalOrder = repository.findByQuantity(quantity);
            return optionalOrder;
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    //finds the orders by their date
    public Optional<Order> findByOrderDate(LocalDate orderDate){
        try {
            Optional<Order> optionalOrder = repository.findByOrderDate(orderDate);
            return optionalOrder;
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    //finds the order's purchase status by its id
    public boolean getPurchaseStatus(Integer id) {
        boolean check = false;
        try {
            Order order = repository.getById(id);
            order.getPurchaseStatus();
            check = true;
        } catch (Exception e) {
            check = false;
        }
        return check;
    }

    //cancels the order
    @Transactional
    public boolean cancelOrder(String uuid) {
        try {
            Optional<Order> optionalOrder = repository.findByUuid(uuid);
            if (optionalOrder.isPresent()) {
                Order order = optionalOrder.get();
                if (order.getPurchaseStatus() == PurchaseStatus.NEW_ORDER ||
                order.getPurchaseStatus() == PurchaseStatus.BEING_PREPARED) {
                    order.setPurchaseStatus(PurchaseStatus.ORDER_CANCELED);
                    repository.saveAndFlush(order);
                    return true;
                }
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }




}
