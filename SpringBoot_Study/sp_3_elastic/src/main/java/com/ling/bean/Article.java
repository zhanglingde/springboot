package com.ling.bean;

import io.searchbox.annotations.JestId;

import lombok.Data;

@Data
public class Article {

    @JestId                // 表示主键
    private Integer id;
    private String author;
    private String title;
    private String content;

}
