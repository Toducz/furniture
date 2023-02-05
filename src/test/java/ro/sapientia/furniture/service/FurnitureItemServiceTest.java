package ro.sapientia.furniture.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ro.sapientia.furniture.error.FurnitureItemNotFoundException;
import ro.sapientia.furniture.model.FurnitureItem;
import ro.sapientia.furniture.model.Joinery;
import ro.sapientia.furniture.model.Material;
import ro.sapientia.furniture.repository.FurnitureItemRepository;

public class FurnitureItemServiceTest {
    private FurnitureItemRepository repositoryMock;

    private FurnitureItemServiceImpl service;

    private static FurnitureItem expectedFurnitureItem = new FurnitureItem();
    private static FurnitureItem updatedFurnitureItem = new FurnitureItem();
    private static Material material = new Material();
    private static Material material2 = new Material();
    private static Joinery joinery = new Joinery();
    private static Joinery joinery2 = new Joinery();

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

        material2.setId(2L);
        material2.setPrice(1.0);
        material2.setType("type9");

        joinery2.setId(2L);
        joinery2.setPrice(1.0);
        joinery2.setType("type9");

        updatedFurnitureItem.setId(1L);
        updatedFurnitureItem.setMaterial(material2);
        updatedFurnitureItem.setJoinery(joinery2);
    }

    @BeforeEach
    public void setUp() {
        initTestData();
        repositoryMock = mock(FurnitureItemRepository.class);
        service = new FurnitureItemServiceImpl(repositoryMock);
    }

    @Test
    public void testFindAllFurnitureItems_emptyList() {
        // WHEN
        when(repositoryMock.findAll()).thenReturn(Collections.emptyList());

        // THEN
        final List<FurnitureItem> furnitureItems =  service.findAllFurnitureItem();

        assertEquals(0, furnitureItems.size());
    }

    @Test
    public void testFindAllFurnitureItems_null() {
        // WHEN
        when(repositoryMock.findAll()).thenReturn(null);

        // THEN
        final List<FurnitureItem> furnitureItems =  service.findAllFurnitureItem();

        assertNull(furnitureItems);
    }

    @Test
    public void testFindAllFurnitureItems() {
        // WHEN
        when(repositoryMock.findAll()).thenReturn(List.of(expectedFurnitureItem));

        // THEN
        final List<FurnitureItem> furnitureItems =  service.findAllFurnitureItem();

        assertEquals(1, furnitureItems.size());
    }

    @Test
    public void testCreateFurnitureItem_Success() {
        // WHEN
        when(repositoryMock.save(any())).thenReturn(expectedFurnitureItem);

        // THEN
        FurnitureItem actualFurnitureItem = service.create(expectedFurnitureItem);

        assertEquals(expectedFurnitureItem, actualFurnitureItem);
    }

    @Test
    public void testFindNullFurnitureItem() {
        when(repositoryMock.findById(1L)).thenReturn(null);
        FurnitureItem furnitureItem = new FurnitureItem();

        doReturn(Optional.of(furnitureItem)).when(repositoryMock).findById(1L);

        assertEquals(furnitureItem.toString(), "FurnitureItem(id=null, material=null, joinery=null)");
    }

    @SneakyThrows
    @Test
    public void testFindFurnitureItemSuccessById() {
        // WHEN
        when(repositoryMock.findById(any())).thenReturn(Optional.of(expectedFurnitureItem));

        // THEN
        Optional<FurnitureItem> actualFurnitureItem = service.getFurnitureById(1L);

        assertEquals(expectedFurnitureItem, actualFurnitureItem.get());
    }

    @Test
    public void testGivenFurnitureItemDoesNotExist() throws FurnitureItemNotFoundException {
        // WHEN
        when(repositoryMock.findById(any())).thenReturn(Optional.empty());

        // THEN
        assertThrows(FurnitureItemNotFoundException.class, () -> {
            service.getFurnitureById(1L);
        });
    }

    @Test
    public void testUpdateFurnitureItem() {
        // WHEN
        when(repositoryMock.findById(any())).thenReturn(Optional.of(expectedFurnitureItem));
        when(repositoryMock.save(any())).thenReturn(updatedFurnitureItem);

        FurnitureItem actualFurnitureItem = service.update(expectedFurnitureItem.getId(), updatedFurnitureItem);

        assertEquals(updatedFurnitureItem, actualFurnitureItem);
    }

    @SneakyThrows
    @Test
    public void testDeleteFurnitureItem() {
        // THEN
        when(repositoryMock.findById(expectedFurnitureItem.getId())).thenReturn(Optional.of(expectedFurnitureItem));

        service.delete(expectedFurnitureItem.getId());

        verify(repositoryMock, times(1)).delete(expectedFurnitureItem);
    }

}

