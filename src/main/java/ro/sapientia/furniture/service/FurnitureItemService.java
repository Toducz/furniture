package ro.sapientia.furniture.service;

import lombok.AllArgsConstructor;
import ro.sapientia.furniture.model.FurnitureItem;

import java.util.List;
import java.util.Optional;

public interface FurnitureItemService {

    public List<FurnitureItem> findAllFurnitureItem();

    public FurnitureItem create(FurnitureItem furnitureItem);

    public Optional<FurnitureItem> getFurnitureById(Long id);

    FurnitureItem update(Long id, FurnitureItem furnitureItem);

    void delete(Long id);
}
