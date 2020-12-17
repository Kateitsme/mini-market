package ru.geekbrains.mini.market.conrollers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.geekbrains.mini.market.entites.Category;
import ru.geekbrains.mini.market.entites.Product;
import ru.geekbrains.mini.market.service.ProductService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ProductControllerTests {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private ProductService productService;

    @Test
    public void getAllProductsTest() throws Exception {

        Category cameras = new Category();
        cameras.setId(1L);
        cameras.setTitle("Cameras");

        List<Product> products = new ArrayList<>();
        Product nikon = new Product();
        nikon.setId(1L);
        nikon.setTitle("nikon");
        nikon.setPrice(10000);
        nikon.setCategory(cameras);
        List<Product> allProducts = new ArrayList<>(Arrays.asList(nikon));
        products.add(nikon);
        cameras.setProducts(products);
        given(productService.getAllProducts()).willReturn(allProducts);
        mvc.perform(get("/api/v1/products")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].title", is(allProducts.get(0).getTitle())));

    }

    @Test
    public void getProductById() throws Exception {

        Category cameras = new Category();
        cameras.setId(1L);
        cameras.setTitle("Cameras");

        Product nikon = new Product();
        nikon.setId(1L);
        nikon.setTitle("nikon");
        nikon.setPrice(10000);
        nikon.setCategory(cameras);

        List<Product> products = new ArrayList<>(Arrays.asList(nikon));

        cameras.setProducts(products);
        given(productService.getOneById(1L)).willReturn(Optional.of(nikon));

        mvc.perform(get("/api/v1/products/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title", is("nikon")))
                .andExpect(jsonPath("$.categoryTitle", is("Cameras")))
                .andExpect(jsonPath("$.id", is(1)));
    }
}