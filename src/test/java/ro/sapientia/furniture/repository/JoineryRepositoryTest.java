package ro.sapientia.furniture.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import ro.sapientia.furniture.model.Joinery;

import static org.junit.Assert.assertEquals;

@DataJpaTest
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
@TestPropertySource(locations = "classpath:test.properties")
class JoineryRepositoryTest {
    @Autowired
    JoineryRepository joineryRepository;

    private static Joinery expectedJoinery = new Joinery();
    private static Joinery updatedJoinery = new Joinery();

    public static void initTestData() {
        expectedJoinery.setId(1L);
        expectedJoinery.setPrice(1.0);
        expectedJoinery.setType("type1");

        updatedJoinery.setId(2L);
        updatedJoinery.setPrice(1.0);
        updatedJoinery.setType("type9");
    }


    @BeforeEach
    public void setUp() {
        initTestData();
        joineryRepository.deleteAll();
        joineryRepository.flush();
    }

    @Test
    public void joinery_list_should_be_empty() {
        var result = joineryRepository.findAll();
        assertEquals(0, result.size());
    }

    @Test
    public void save() {
        var result = joineryRepository.save(updatedJoinery);
        assertEquals(updatedJoinery, result);
    }
}
