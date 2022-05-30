package com.ling.springdatajpademo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ling.springdatajpademo.model.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

/**
 * @author zhangling
 * @date 2021/9/28 4:32 下午
 */
@SpringBootTest
public class JsonTest {

    @Autowired
    JacksonConfig jacksonConfig;

    @Test
    public void testJson() throws JsonProcessingException {
        Book book = new Book();
        book.setName("书籍名称");
        book.setAuthor("author");
        book.setPublish(LocalDateTime.now());
        ObjectMapper om = new ObjectMapper();
//        String json = om.writeValueAsString(book);
        ObjectMapper objectMapper = jacksonConfig.objectMapper();
        String json = objectMapper.writeValueAsString(book);
        System.out.println(json);

    }
}
