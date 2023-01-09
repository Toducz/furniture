package ro.sapientia.furniture.service;

import org.springframework.stereotype.Service;
import ro.sapientia.furniture.model.FurnitureOrder;
import ro.sapientia.furniture.repository.OrderRepository;

import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(final OrderRepository orderRepository){
        this.orderRepository = orderRepository;
    }

    public List<FurnitureOrder> findAllOrders(){
        return this.orderRepository.findAll();
    }

    public FurnitureOrder findOrderById(final Long id){
        return this.orderRepository.findOrderById(id);
    }

    public FurnitureOrder create(FurnitureOrder furnitureOrder){
        return this.orderRepository.saveAndFlush(furnitureOrder);
    }

    public FurnitureOrder update(FurnitureOrder furnitureOrder){
        return this.orderRepository.saveAndFlush(furnitureOrder);
    }

    public void delete(Long id){
        this.orderRepository.deleteById(id);
    }
}
