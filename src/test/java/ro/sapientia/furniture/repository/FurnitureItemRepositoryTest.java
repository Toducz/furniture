package ro.sapientia.furniture.repository;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;

@DataJpaTest
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
@TestPropertySource(locations = "classpath:test.properties")
class FurnitureItemRepositoryTest {
    @Autowired
    FurnitureItemRepository furnitureItemRepository;

    @Test
    public void furniture_item_list_should_be_empty() {
        var result = furnitureItemRepository.findAll();
        assertEquals(0, result.size());
    }
}
