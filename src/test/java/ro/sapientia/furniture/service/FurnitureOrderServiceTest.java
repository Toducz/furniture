package ro.sapientia.furniture.service;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ro.sapientia.furniture.model.FurnitureOrder;
import ro.sapientia.furniture.repository.OrderRepository;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import static org.mockito.Mockito.*;

public class FurnitureOrderServiceTest {

    private OrderRepository repositoryMock;

    private OrderService service;

    @BeforeEach
    public void setUp(){
        repositoryMock = mock(OrderRepository.class);
        service = new OrderService(repositoryMock);
    }

    @Test
    public void testFindAllOrders_emptyList(){
        when(repositoryMock.findAll()).thenReturn(Collections.emptyList());
        final List<FurnitureOrder> orders =  service.findAllOrders();

        Assertions.assertEquals(0, orders.size());
    }

    @Test
    public void testFindAllOrders_null(){
        when(repositoryMock.findAll()).thenReturn(null);
        final List<FurnitureOrder> orders =  service.findAllOrders();

        Assertions.assertNull(orders);
    }

    @Test
    public void testFindById_notFound() {
        doReturn(null).when(repositoryMock).findOrderById(1L);

        FurnitureOrder actualOrder = service.findOrderById(1L);
        verify(repositoryMock).findOrderById(1L);
        Assertions.assertNull(actualOrder);
    }

    @Test
    public void testAddOrder() {
        FurnitureOrder order = new FurnitureOrder();
        order.setId(1L);

        doReturn(order).when(repositoryMock).saveAndFlush(order);

        FurnitureOrder expectedOrder = new FurnitureOrder();
        expectedOrder.setId(1L);
        FurnitureOrder actualOrder = service.create(order);
        verify(repositoryMock).saveAndFlush(order);
        Assertions.assertEquals(expectedOrder.getId(), actualOrder.getId());
    }

    @Test
    public void testUpdateOrder() {
        FurnitureOrder order = new FurnitureOrder();
        order.setId(1L);
        doReturn(order).when(repositoryMock).saveAndFlush(order);

        FurnitureOrder expectedOrder = new FurnitureOrder();
        expectedOrder.setId(1L);
        FurnitureOrder actualOrder = service.update(order);
        verify(repositoryMock).saveAndFlush(order);
        Assertions.assertEquals(expectedOrder.getId(), actualOrder.getId());
    }
}
