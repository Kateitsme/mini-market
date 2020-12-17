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
import ru.geekbrains.mini.market.service.CategoryService;

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
public class CategoryControllerTests {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private CategoryService categoryService;

    @Test
    public void getAllCategoriesTest() throws Exception {
        Category cd = new Category();
        cd.setId(1L);
        cd.setTitle("CD");
        List<Category> allCategories = new ArrayList<>(Arrays.asList(cd));
        given(categoryService.findAll()).willReturn(allCategories);

        mvc.perform(get("/api/v1/categories")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].title", is(allCategories.get(0).getTitle())));
    }

    @Test
    public void getCategoryById() throws Exception {
        Category cd = new Category();
        cd.setId(1L);
        cd.setTitle("CD");
        List<Category> allCategories = new ArrayList<>(Arrays.asList(cd));
        given(categoryService.findAll()).willReturn(allCategories);

        List<Product> products = new ArrayList<>();
        Product product = new Product();
        product.setId(1L);
        product.setTitle("lipgloss");
        product.setPrice(100);
        product.setCategory(cd);
        products.add(product);

        cd.setProducts(products);
        given(categoryService.getOneById(1L)).willReturn(Optional.of(cd));

        mvc.perform(get("/api/v1/categories/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.products").isArray())
                .andExpect(jsonPath("$.products", hasSize(1)))
                .andExpect(jsonPath("$.title", is("CD")));
    }



}