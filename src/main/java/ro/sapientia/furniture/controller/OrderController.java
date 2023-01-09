package ro.sapientia.furniture.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.sapientia.furniture.model.FurnitureOrder;
import ro.sapientia.furniture.service.OrderService;

import java.util.List;

@RestController
@RequestMapping("/furniture_order")
public class OrderController {

    private final OrderService orderService;

    public OrderController(final OrderService orderService){
        this.orderService = orderService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<FurnitureOrder>> getAllOrders(){
        final List<FurnitureOrder> furnitureOrders = orderService.findAllOrders();
        return new ResponseEntity<>(furnitureOrders, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<FurnitureOrder> getOrderById(@PathVariable("id") Long id){
        final FurnitureOrder furnitureOrder = orderService.findOrderById(id);
        return new ResponseEntity<>(furnitureOrder, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<FurnitureOrder> addOrder(@RequestBody FurnitureOrder furnitureOrder){
        final FurnitureOrder persistentFurnitureOrder = orderService.create(furnitureOrder);
        return new ResponseEntity<>(persistentFurnitureOrder, HttpStatus.CREATED);
    }

    @PostMapping("/update")
    public ResponseEntity<FurnitureOrder> updateOrder(@RequestBody FurnitureOrder furnitureOrder){
        final FurnitureOrder persistentFurnitureOrder = orderService.update(furnitureOrder);
        return new ResponseEntity<>(persistentFurnitureOrder, HttpStatus.OK);
    }

    @GetMapping("/delete/{id}")
    public ResponseEntity<?> deleteOrder(@PathVariable("id") Long id){
        orderService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
