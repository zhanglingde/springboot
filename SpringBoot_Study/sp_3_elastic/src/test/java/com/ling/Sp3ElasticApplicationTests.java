package com.ling;

import com.ling.bean.Article;
import com.ling.bean.Book;
import com.ling.repository.BookRepository;
import io.searchbox.client.JestClient;
import io.searchbox.core.Index;
import io.searchbox.core.Search;
import io.searchbox.core.SearchResult;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;


@SpringBootTest
class Sp3ElasticApplicationTests {

    @Autowired
    private JestClient jestClient;

    @Autowired
    private BookRepository bookRepository;

    @Test
    public void test02(){
        Book book = new Book();
        book.setId(1);
        book.setBookName("西游记");
        book.setAuthor("吴承恩");
        bookRepository.index(book);

        for (Book b : bookRepository.findByBookNameLike("游")) {
            System.out.println(b);
        }

    }
    @Test
    void contextLoads() {
        //1、给Es中索引（保存）一个文档；
        Article article = new Article();
        article.setId(1);
        article.setAuthor("张三");
        article.setTitle("好消息");
        article.setContent("Hello World");

        //构建一个索引功能
        Index index = new Index.Builder(article).index("ling").type("news").build();
        try {
            // 执行
            jestClient.execute(index);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void search(){
        //查询表达式
        String json ="{\n" +
                "    \"query\" : {\n" +
                "        \"match\" : {\n" +
                "            \"content\" : \"hello\"\n" +
                "        }\n" +
                "    }\n" +
                "}";
        //构建搜索功能
        Search search = new Search.Builder(json).addIndex("ling").addType("news").build();
        try {
            SearchResult result = jestClient.execute(search);
            System.out.println(result.getJsonString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
