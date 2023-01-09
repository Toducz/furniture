package ro.sapientia.furniture.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ro.sapientia.furniture.model.Joinery;
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
    public void testFindById() {
        when(repositoryMock.findAll()).thenReturn(null);
        Joinery joinery = new Joinery();

        doReturn(Optional.of(joinery)).when(repositoryMock).findById(1L);

        assertEquals(joinery.toString(), "Joinery(id=null, type=null, price=0.0)");
    }
}
