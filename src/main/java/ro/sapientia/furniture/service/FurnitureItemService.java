package ro.sapientia.furniture.service;

import ro.sapientia.furniture.model.FurnitureItem;

import java.util.List;

public interface FurnitureItemService {

    public List<FurnitureItem> findAllFurniteItem();

    public FurnitureItem create(FurnitureItem furnitureItem);
}
