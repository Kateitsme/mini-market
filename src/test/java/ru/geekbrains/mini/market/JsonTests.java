package ru.geekbrains.mini.market;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import ru.geekbrains.mini.market.entites.Product;

import static org.assertj.core.api.Assertions.assertThat;

@JsonTest
public class JsonTests {

    @Autowired
    private JacksonTester<Product> jackson;

    @Test
    public void jsonSerializationTest() throws Exception {
        Product album = new Product();
        album.setId(1L);
        album.setTitle("Album");

        assertThat(jackson.write(album))
                .hasJsonPathNumberValue("$.id")
                .extractingJsonPathStringValue("$.title").isEqualTo("Album");
    }

    @Test
    public void jsonDeserializationTest() throws Exception {
        String content = "{\"id\": 1,\"title\":\"Album\"}";
        Product album = new Product();
        album.setId(1L);
        album.setTitle("Album");

        assertThat(jackson.parse(content)).isEqualTo(album);
        assertThat(jackson.parseObject(content).getTitle()).isEqualTo("Album");
    }
}
