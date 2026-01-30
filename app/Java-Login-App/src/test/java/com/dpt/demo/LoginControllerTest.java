package com.dpt.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;


@WebMvcTest(login.class)
class LoginControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser(username = "testuser", roles = {"USER"})
    void getLoginPage_shouldRenderLoginHtml() throws Exception {
        mockMvc.perform(get("/login"))
           .andExpect(status().isOk())
           .andExpect(content().string(org.hamcrest.Matchers.containsString("Please sign in")));
    }
}
