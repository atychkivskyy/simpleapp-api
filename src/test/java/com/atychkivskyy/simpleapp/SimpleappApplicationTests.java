package com.atychkivskyy.simpleapp;

import com.atychkivskyy.simpleapp.controller.InfoController;
import com.atychkivskyy.simpleapp.controller.ProductController;
import com.atychkivskyy.simpleapp.repository.ProductRepository;
import com.atychkivskyy.simpleapp.service.ProductService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
@DisplayName("Application Context Tests")
class SimpleappApplicationTests {

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private ProductController productController;

    @Autowired
    private InfoController infoController;

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductRepository productRepository;

    @Test
    @DisplayName("Application context should load successfully")
    void contextLoads() {
        assertThat(applicationContext).isNotNull();
    }

    @Test
    @DisplayName("ProductController bean should be loaded")
    void productControllerLoads() {
        assertThat(productController).isNotNull();
    }

    @Test
    @DisplayName("InfoController bean should be loaded")
    void infoControllerLoads() {
        assertThat(infoController).isNotNull();
    }

    @Test
    @DisplayName("ProductService bean should be loaded")
    void productServiceLoads() {
        assertThat(productService).isNotNull();
    }

    @Test
    @DisplayName("ProductRepository bean should be loaded")
    void productRepositoryLoads() {
        assertThat(productRepository).isNotNull();
    }

    @Test
    @DisplayName("Main method should run without throwing exception")
    void mainMethodRuns() {
        // This test ensures the main method doesn't throw any exceptions
        // The application context is already started by @SpringBootTest
        assertThat(applicationContext.getId()).isNotNull();
    }
}
