package com.dpt.demo;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.boot.builder.SpringApplicationBuilder;

class ServletInitializerTest {

    @Test
    void configure_shouldReturnApplicationBuilder() {
        ServletInitializer initializer = new ServletInitializer();
        SpringApplicationBuilder builder =
                initializer.configure(new SpringApplicationBuilder());

        assertNotNull(builder);
    }
}
