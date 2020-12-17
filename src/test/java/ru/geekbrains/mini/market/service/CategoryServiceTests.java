package ru.geekbrains.mini.market.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.geekbrains.mini.market.entites.Category;
import ru.geekbrains.mini.market.repositories.CategoryRepository;
import ru.geekbrains.mini.market.service.CategoryService;

import java.util.Optional;

@SpringBootTest
public class CategoryServiceTests {

    @MockBean
    private CategoryRepository categoryRepository;

    @Test
    public void findOneCategoryTest() {
        Category cd = new Category();
        cd.setTitle("CD");
        cd.setId(1L);

        Mockito.doReturn(Optional.of(cd))
                .when(categoryRepository)
                .findById(1L);

        Assertions.assertNotNull(cd);
        Assertions.assertEquals(1L, cd.getId());
        Assertions.assertEquals("CD", cd.getTitle());
    }
}
