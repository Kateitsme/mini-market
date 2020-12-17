package ru.geekbrains.mini.market;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import ru.geekbrains.mini.market.entites.Category;
import ru.geekbrains.mini.market.entites.Product;
import ru.geekbrains.mini.market.repositories.CategoryRepository;
import ru.geekbrains.mini.market.repositories.ProductRepository;

import java.util.List;

@DataJpaTest
public class RepositoriesTests {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void productRepositoryTest() {
        Product product = new Product();
        product.setTitle("Nikon");
        entityManager.persist(product);
        entityManager.flush();

        List<Product> productsList = productRepository.findAll();

        Assertions.assertEquals(6, productsList.size());
        Assertions.assertEquals("Nikon", productsList.get(5).getTitle());
    }

    @Test
    public void categoryRepositoryTest() {
        Category category = new Category();
        category.setTitle("Cameras");
        entityManager.persist(category);
        entityManager.flush();

        List<Category> categoryList = categoryRepository.findAll();

        Assertions.assertEquals(3, categoryList.size());
        Assertions.assertEquals("Cameras", categoryList.get(2).getTitle());
    }
}