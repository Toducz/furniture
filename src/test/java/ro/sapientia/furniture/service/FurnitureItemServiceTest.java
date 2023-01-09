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
import ro.sapientia.furniture.model.FurnitureItem;
import ro.sapientia.furniture.repository.FurnitureBodyRepository;
import ro.sapientia.furniture.repository.FurnitureItemRepository;

class FurnitureItemServiceTest {
    private FurnitureItemRepository repositoryMock;

    private FurnitureItemServiceImpl service;

    @BeforeEach
    public void setUp() {
        repositoryMock = mock(FurnitureItemRepository.class);
        service = new FurnitureItemServiceImpl(repositoryMock);
    }

    @Test
    public void testFindAllFurnitureBodies_emptyList() {
        when(repositoryMock.findAll()).thenReturn(Collections.emptyList());
        final List<FurnitureItem> furnitureItems =  service.findAllFurnitureItem();

        assertEquals(0, furnitureItems.size());
    }

    @Test
    public void testFindAllFurnitureBodies_null() {
        when(repositoryMock.findAll()).thenReturn(null);
        final List<FurnitureItem> furnitureItems =  service.findAllFurnitureItem();

        assertNull(furnitureItems);
    }
}
