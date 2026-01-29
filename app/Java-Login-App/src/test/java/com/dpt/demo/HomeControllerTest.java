package com.dpt.demo;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(HomeController.class)
class HomeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser(username = "testuser", roles = {"USER"})
    void homeEndpointShouldReturnHomeView_authenticatedUser() throws Exception {
        mockMvc.perform(get("/home"))
               .andExpect(status().isOk())
               .andExpect(view().name("home"));
    }

    @Test
    @WithMockUser(username = "testuser", roles = {"USER"})
    void rootEndpointShouldReturnHomeView_authenticatedUser() throws Exception {
        mockMvc.perform(get("/"))
               .andExpect(status().isOk())
               .andExpect(view().name("home"));
    }
}
