package ro.sapientia.furniture.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import ro.sapientia.furniture.model.FurnitureBody;
import ro.sapientia.furniture.model.FurnitureItem;
import ro.sapientia.furniture.model.Joinery;
import ro.sapientia.furniture.model.Material;
import ro.sapientia.furniture.service.FurnitureBodyService;
import ro.sapientia.furniture.service.FurnitureItemService;
import ro.sapientia.furniture.service.FurnitureItemServiceImpl;

@WebMvcTest(controllers = FurnitureItemController.class, excludeAutoConfiguration = {SecurityAutoConfiguration.class})
class FurnitureItemControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean(FurnitureItemService.class)
    private FurnitureItemService furnitureItemService;

    @Test
    public void greetingShouldReturnMessageFromService() throws Exception {
        final FurnitureItem furnitureItem = new FurnitureItem();

        Material material = new Material();
        material.setType("type1");
        material.setPrice(1);

        Joinery joinery = new Joinery();
        joinery.setType("type2");
        joinery.setPrice(2);

        furnitureItem.setJoinery(joinery);
        furnitureItem.setMaterial(material);

        when(furnitureItemService.findAllFurnitureItem()).thenReturn(List.of(furnitureItem));

        this.mockMvc.perform(get("/furniture-item/all")).andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].material.price", is(1.0)));
    }
}
