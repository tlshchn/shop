package com.example.springsecurityapplication.services;

import com.example.springsecurityapplication.models.Category;
import com.example.springsecurityapplication.models.Image;
import com.example.springsecurityapplication.models.Product;
import com.example.springsecurityapplication.repositories.CategoryRepository;
import com.example.springsecurityapplication.repositories.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    // Данный метод позволяет получить список всех товаров
    public List<Product> getAllProduct(){
        return productRepository.findAll();
    }

    // Данный метод позволяет получить товар по id
    public Product getProductId(int id){
        Optional<Product> optionalProduct = productRepository.findById(id);
        return optionalProduct.orElse(null);
    }

    // Данный метод позволяет сохранить товар
    @Transactional
    public void saveProduct(Product product, Category category){
        product.setCategory(category);
        productRepository.save(product);
    }

    // Данный метод позволяет обновить данные о товаре
    @Transactional
    public void updateProduct(int id, Product product){
        product.setId(id);
        productRepository.save(product);
    }

    // Данный метод позволяет удалить товар по id
    @Transactional
    public void deleteProduct(int id){
        productRepository.deleteById(id);
    }

    @Transactional
    public void fillDefaultProducts() {
        boolean isEmptyProducts = productRepository.findAll().isEmpty();
        if (isEmptyProducts) {
            Category shoesCategory = categoryRepository.findByName("Обувь");
            List<Image> shoesImages = new ArrayList<>();
            Product product1 = new Product();
            Image imageShoes = new Image();
            imageShoes.setProduct(product1);
            imageShoes.setFileName("shoe1.jpg");
            shoesImages.add(imageShoes);
            product1.setTitle("Тапки мужские кожаные");
            product1.setDescription("Защитные Водонепроницаемые Нескользящие");
            product1.setPrice(982);
            product1.setWarehouse("China");
            product1.setCategory(shoesCategory);
            product1.setSeller("Дядя Лиао");
            product1.setImageList(shoesImages);
            productRepository.save(product1);
        }
    }
}
