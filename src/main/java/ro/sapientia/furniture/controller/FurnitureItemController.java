package ro.sapientia.furniture.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.sapientia.furniture.model.FurnitureItem;
import ro.sapientia.furniture.service.FurnitureItemService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/furniture-item")
public class FurnitureItemController {

    @Autowired
    FurnitureItemService furnitureItemService;

    @GetMapping("/all")
    public List<FurnitureItem> getFurnitureItems() {
        return furnitureItemService.findAllFurnitureItem();
    }

    @PostMapping("/add")
    public FurnitureItem create(@RequestBody FurnitureItem furnitureItem) {
        return furnitureItemService.create(furnitureItem);
    }

    @GetMapping("/find/{id}")
    public Optional<FurnitureItem> getFurnitureById(@PathVariable Long id) {
        return furnitureItemService.getFurnitureById(id);
    }

    @PutMapping("/update/{id}")
    public FurnitureItem update(@PathVariable Long id, @RequestBody FurnitureItem furnitureItem) {
        return furnitureItemService.update(id, furnitureItem);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id) {
        furnitureItemService.delete(id);
    }
}
