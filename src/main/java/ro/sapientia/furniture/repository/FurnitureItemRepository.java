package ro.sapientia.furniture.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.sapientia.furniture.model.FurnitureItem;

@Repository
public interface FurnitureItemRepository extends JpaRepository<FurnitureItem, Long> {
}
