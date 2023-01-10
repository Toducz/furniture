package ro.sapientia.furniture.controller;

import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import ro.sapientia.furniture.model.FurnitureOrder;
import ro.sapientia.furniture.service.OrderService;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;


@WebMvcTest(controllers = OrderController.class, excludeAutoConfiguration = {SecurityAutoConfiguration.class})
public class FurnitureOrderControllerTest {

    OrderService mockService = mock(OrderService.class);
    OrderController controller = new OrderController(mockService);

    MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

    @Test
    public void testGetAllOrders() throws Exception {
        // setup mock service to return a list of orders
        List<FurnitureOrder> orders = Arrays.asList(new FurnitureOrder(), new FurnitureOrder());
        when(mockService.findAllOrders()).thenReturn(orders);

        // send request to controller and make assertions about the response
        mockMvc.perform(get("/orders"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[1].id", is(2)));

        // verify that the service method was called
        verify(mockService).findAllOrders();
    }
}
