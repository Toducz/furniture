package ro.sapientia.furniture.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ro.sapientia.furniture.model.FurnitureBody;
import ro.sapientia.furniture.model.Joinery;
import ro.sapientia.furniture.repository.FurnitureBodyRepository;
import ro.sapientia.furniture.repository.FurnitureItemRepository;
import ro.sapientia.furniture.repository.JoineryRepository;

class JoinerySeviceTest {
    private JoineryRepository repositoryMock;

    private JoineryServiceImpl service;

    @BeforeEach
    public void setUp() {
        repositoryMock = mock(JoineryRepository.class);
        service = new JoineryServiceImpl(repositoryMock);
    }

    @Test
    public void testFindAllFurnitureBodies_emptyList() {
        when(repositoryMock.findAll()).thenReturn(Collections.emptyList());
        final List<Joinery> joineries =  service.findAllJoinery();

        assertEquals(0, joineries.size());
    }

    @Test
    public void testFindAllFurnitureBodies_null() {
        when(repositoryMock.findAll()).thenReturn(null);
        final List<Joinery> joineries =  service.findAllJoinery();

        assertNull(joineries);
    }
}
