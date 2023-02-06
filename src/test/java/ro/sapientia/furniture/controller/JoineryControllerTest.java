package ro.sapientia.furniture.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import ro.sapientia.furniture.model.Joinery;
import ro.sapientia.furniture.service.JoinerySevice;

@WebMvcTest(controllers = JoineryController.class, excludeAutoConfiguration = {SecurityAutoConfiguration.class})
class JoineryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean(JoinerySevice.class)
    private JoinerySevice joinerySevice;

    @Test
    public void findAll() throws Exception {
        final Joinery joinery = new Joinery();
        joinery.setPrice(12);
        when(joinerySevice.findAllJoinery()).thenReturn(List.of(joinery));

        this.mockMvc.perform(get("/furniture-joinery/all")).andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].price", is(12.0)));
    }

    @Test
    public void findById() throws Exception {
        final Joinery joinery = new Joinery();
        joinery.setPrice(12.0);
        joinery.setId(1L);
        when(joinerySevice.findJoineryById(1L)).thenReturn(Optional.of(joinery));

        this.mockMvc.perform(get("/furniture-joinery/find/1")).andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.price", is(12.0)));
    }

    @Test
    public void deleteJoinery() throws Exception {
        this.mockMvc.perform(delete("/furniture-joinery/delete/1"))
                .andDo(print())
                .andExpect(status().isOk());
    }

}
