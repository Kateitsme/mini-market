package ru.geekbrains.mini.market.service;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.geekbrains.mini.market.entites.Product;
import ru.geekbrains.mini.market.repositories.ProductRepository;
import ru.geekbrains.mini.market.service.ProductService;

import java.util.Optional;

@SpringBootTest
public class ProductServiceTests {

    @MockBean
    private ProductRepository productRepository;

    @Test
    public void findOneProductTest() {
        Product nikon = new Product();
        nikon.setId(1L);
        nikon.setTitle("nikon");
        nikon.setPrice(10000);
        Mockito.doReturn(Optional.of(nikon))
                .when(productRepository)
                .findById(1L);

        Assertions.assertNotNull(nikon);
        Assertions.assertEquals("nikon", nikon.getTitle());
    }


}
