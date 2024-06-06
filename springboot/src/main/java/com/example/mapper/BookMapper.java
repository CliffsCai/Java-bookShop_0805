package com.example.mapper;

import com.example.controller.request.BaseRequest;
import com.example.entity.Book;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BookMapper {

    //@Select("select * from book")
    List<Book> list();


    List<Book> listByCondition(BaseRequest baseRequest);

    void save(Book book);

    Book getById(Integer id);

    void updateById(Book book);

    void deleteById(Integer id);
}
