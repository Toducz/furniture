package ro.sapientia.furniture.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.sapientia.furniture.model.FurnitureOrder;

public interface OrderRepository extends JpaRepository<FurnitureOrder, Long> {

    FurnitureOrder findOrderById(Long id);
}
