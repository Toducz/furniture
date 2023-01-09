package ro.sapientia.furniture.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.sapientia.furniture.model.FurnitureItem;
import ro.sapientia.furniture.service.FurnitureItemService;

import java.util.List;

@RestController
@RequestMapping("/furniture/item")
public class FurnitureItemController {

    @Autowired
    FurnitureItemService furnitureItemService;

    @GetMapping("/all")
    public List<FurnitureItem> getFurnitureItems() {
        return furnitureItemService.findAllFurniteItem();
    }

    @GetMapping("/add")
    public FurnitureItem create(@RequestBody FurnitureItem furnitureItem) {
        return furnitureItemService.create(furnitureItem);
    }
}
