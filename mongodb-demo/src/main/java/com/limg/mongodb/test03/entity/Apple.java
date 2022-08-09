package com.limg.mongodb.test03.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;


@Data
@AllArgsConstructor
public class Apple {

    /**
     * id
     */
    private String color;
    /**
     * 价格
     */
    private Integer price;



}
