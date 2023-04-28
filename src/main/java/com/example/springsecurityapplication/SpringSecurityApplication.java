package com.example.springsecurityapplication;

import com.example.springsecurityapplication.services.CategoryService;
import com.example.springsecurityapplication.services.PersonService;
import com.example.springsecurityapplication.services.ProductService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;

import java.io.File;
import java.net.URISyntaxException;
import java.util.Collections;
import java.util.Objects;

@SpringBootApplication
public class SpringSecurityApplication {

    SpringSecurityApplication(
            PersonService personService,
            CategoryService categoryService,
            ProductService productService
    ) {
        personService.registerAdmin();
        categoryService.addDefaultCategories();
        productService.fillDefaultProducts();
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringSecurityApplication.class, args);
    }

}
