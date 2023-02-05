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

import ro.sapientia.furniture.error.JoineryNotFoundException;
import ro.sapientia.furniture.model.Joinery;
import ro.sapientia.furniture.repository.JoineryRepository;

class JoinerySeviceTest {
    private JoineryRepository repositoryMock;

    private JoineryServiceImpl service;

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
        repositoryMock = mock(JoineryRepository.class);
        service = new JoineryServiceImpl(repositoryMock);
    }

    @Test
    public void testFindAllJoiners_emptyList() {
        when(repositoryMock.findAll()).thenReturn(Collections.emptyList());
        final List<Joinery> joiners =  service.findAllJoinery();

        assertEquals(0, joiners.size());
    }

    @Test
    public void testFindAllJoiners_null() {
        when(repositoryMock.findAll()).thenReturn(null);
        final List<Joinery> joiners =  service.findAllJoinery();

        assertNull(joiners);
    }

    @Test
    public void testFindAllJoiners() {
        // WHEN
        when(repositoryMock.findAll()).thenReturn(List.of(expectedJoinery));

        // THEN
        final List<Joinery> joineries =  service.findAllJoinery();

        assertEquals(1, joineries.size());
    }

    @Test
    public void testFindNullJoineryById() {
        when(repositoryMock.findById(1L)).thenReturn(null);
        Joinery joinery = new Joinery();

        doReturn(Optional.of(joinery)).when(repositoryMock).findById(1L);

        assertEquals(joinery.toString(), "Joinery(id=null, type=null, price=0.0)");
    }

    @Test
    public void testCreateJoinery_Success() {
        // WHEN
        when(repositoryMock.save(any())).thenReturn(expectedJoinery);

        // THEN
        Joinery actualJoinery = service.create(expectedJoinery);

        assertEquals(expectedJoinery, actualJoinery);
    }

    @SneakyThrows
    @Test
    public void testFindJoinerySuccessById() {
        // WHEN
        when(repositoryMock.findById(any())).thenReturn(Optional.of(expectedJoinery));

        // THEN
        Optional<Joinery> actualFurnitureItem = service.findJoineryById(1L);

        assertEquals(expectedJoinery, actualFurnitureItem.get());
    }

    @Test
    public void testGivenJoineryDoesNotExist() throws JoineryNotFoundException {
        // WHEN
        when(repositoryMock.findById(any())).thenReturn(Optional.empty());

        // THEN
        assertThrows(JoineryNotFoundException.class, () -> {
            service.findJoineryById(1L);
        });
    }

    @Test
    public void testUpdateJoinery() {
        // WHEN
        when(repositoryMock.findById(any())).thenReturn(Optional.of(expectedJoinery));
        when(repositoryMock.save(any())).thenReturn(updatedJoinery);

        Joinery actualFurnitureItem = service.update(expectedJoinery.getId(), updatedJoinery);

        assertEquals(updatedJoinery, actualFurnitureItem);
    }

    @SneakyThrows
    @Test
    public void testDeleteJoinery() {
        // THEN
        when(repositoryMock.findById(expectedJoinery.getId())).thenReturn(Optional.of(expectedJoinery));

        service.delete(expectedJoinery.getId());

        verify(repositoryMock, times(1)).delete(expectedJoinery);
    }
}
