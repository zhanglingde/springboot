package com.ling.springdatajpademo.dao;

import com.ling.springdatajpademo.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookDao extends JpaRepository<Book, Long> {

    // get,find,read
    List<Book> getBookByAuthorEquals(String author);

    @Query(nativeQuery = true,value = "select * from t_book where id = (select max(id) from t_book)")
    Book maxIdBook();

    @Query("update t_book set book_name = :name where id = :id")
    @Modifying
    void updateBookById(String name, Long id);

}
