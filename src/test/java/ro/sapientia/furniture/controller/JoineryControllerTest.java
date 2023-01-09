package ro.sapientia.furniture.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import ro.sapientia.furniture.model.FurnitureBody;
import ro.sapientia.furniture.model.FurnitureItem;
import ro.sapientia.furniture.model.Joinery;
import ro.sapientia.furniture.service.FurnitureBodyService;
import ro.sapientia.furniture.service.FurnitureItemService;
import ro.sapientia.furniture.service.FurnitureItemServiceImpl;
import ro.sapientia.furniture.service.JoineryServiceImpl;
import ro.sapientia.furniture.service.JoinerySevice;

@WebMvcTest(controllers = JoineryController.class, excludeAutoConfiguration = {SecurityAutoConfiguration.class})
class JoineryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean(JoinerySevice.class)
    private JoinerySevice joinerySevice;

    @Test
    public void greetingShouldReturnMessageFromService() throws Exception {
        final Joinery joinery = new Joinery();
        joinery.setPrice(12);
        when(joinerySevice.findAllJoinery()).thenReturn(List.of(joinery));

        this.mockMvc.perform(get("/furniture-joinery/all")).andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].price", is(12.0)));
    }
}
