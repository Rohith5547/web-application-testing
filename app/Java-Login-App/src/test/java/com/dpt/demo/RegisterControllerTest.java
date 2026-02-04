package com.dpt.demo;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;


@WebMvcTest(register.class)
class RegisterControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser
    void getRegisterPage_shouldReturnRegisterView() throws Exception {
        mockMvc.perform(get("/register"))
               .andExpect(status().isOk())
               .andExpect(view().name("register"));
    }
    @Test
    @WithMockUser
    void postRegister_shouldReturnSuccessMessage() throws Exception {
        mockMvc.perform(post("/register")
                .with(csrf())
                .param("firstName", "John")
                .param("lastName", "Doe")
                .param("email", "john@test.com")
                .param("userName", "johndoe")
                .param("password", "1234"))
            .andExpect(status().isOk())
            .andExpect(model().attributeExists("message"))
            .andExpect(view().name("register"));
    }
}
