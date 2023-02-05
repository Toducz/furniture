package ro.sapientia.furniture.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.sapientia.furniture.error.FurnitureItemNotFoundException;
import ro.sapientia.furniture.model.FurnitureItem;
import ro.sapientia.furniture.repository.FurnitureItemRepository;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class FurnitureItemServiceImpl implements FurnitureItemService {

    @Autowired
    FurnitureItemRepository furnitureItemRepository;

    @Override
    public List<FurnitureItem> findAllFurnitureItem() {
        return furnitureItemRepository.findAll();
    }

    @Override
    public FurnitureItem create(FurnitureItem furnitureItem) {
        return furnitureItemRepository.save(furnitureItem);
    }

    @Override
    public Optional<FurnitureItem> getFurnitureById(Long id) throws FurnitureItemNotFoundException {

        var currentFurnitureItem = furnitureItemRepository.findById(id);
        if(currentFurnitureItem.isEmpty()) {
            throw new FurnitureItemNotFoundException("Furniture item doesn't exist!");
        }

        return Optional.of(currentFurnitureItem.get());
    }

    @Override
    public FurnitureItem update(Long id, FurnitureItem furnitureItem) {

        FurnitureItem furnitureItem1 = furnitureItemRepository.findById(id).orElseThrow();

        furnitureItem1.setMaterial(furnitureItem.getMaterial());
        furnitureItem1.setJoinery(furnitureItem1.getJoinery());

        return furnitureItemRepository.save(furnitureItem1);
    }

    @Override
    public void delete(Long id) {
        FurnitureItem furnitureItem = furnitureItemRepository.findById(id).orElseThrow();
        furnitureItemRepository.delete(furnitureItem);
    }
}
