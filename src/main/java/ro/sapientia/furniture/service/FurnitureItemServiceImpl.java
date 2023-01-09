package ro.sapientia.furniture.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.sapientia.furniture.model.FurnitureItem;
import ro.sapientia.furniture.repository.FurnitureItemRepository;

import java.util.List;

@Service
public class FurnitureItemServiceImpl implements FurnitureItemService {

    @Autowired
    FurnitureItemRepository furnitureItemRepository;

    @Override
    public List<FurnitureItem> findAllFurniteItem() {
        return furnitureItemRepository.findAll();
    }

    @Override
    public FurnitureItem create(FurnitureItem furnitureItem) {
        return furnitureItemRepository.save(furnitureItem);
    }
}
