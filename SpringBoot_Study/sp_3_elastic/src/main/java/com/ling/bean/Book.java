package com.ling.bean;

import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Document;

@Data
@Document(indexName = "ling",type = "book")
public class Book {
    private Integer id;
    private String bookName;
    private String author;
}
