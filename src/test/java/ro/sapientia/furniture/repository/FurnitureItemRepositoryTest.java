package ro.sapientia.furniture.repository;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import ro.sapientia.furniture.model.FurnitureItem;
import ro.sapientia.furniture.model.Joinery;
import ro.sapientia.furniture.model.Material;

@DataJpaTest
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
@TestPropertySource(locations = "classpath:test.properties")
public class FurnitureItemRepositoryTest {
    @Autowired
    FurnitureItemRepository furnitureItemRepository;

    private static FurnitureItem expectedFurnitureItem = new FurnitureItem();
    private static Material material = new Material();
    private static Joinery joinery = new Joinery();

    // GIVEN
    public static void initTestData() {
        material.setId(1L);
        material.setPrice(1.0);
        material.setType("type1");

        joinery.setId(1L);
        joinery.setPrice(1.0);
        joinery.setType("type1");

        expectedFurnitureItem.setId(1L);
        expectedFurnitureItem.setMaterial(material);
        expectedFurnitureItem.setJoinery(joinery);
    }

    @BeforeEach
    public void setUp() {
        initTestData();
        furnitureItemRepository.deleteAll();
        furnitureItemRepository.flush();
    }

    @Test
    public void furniture_item_list_should_be_empty() {
        var result = furnitureItemRepository.findAll();
        assertEquals(0, result.size());
    }

    @Test
    public void save() {
        var result = furnitureItemRepository.save(expectedFurnitureItem);
        assertEquals(expectedFurnitureItem, result);
    }

}
