package com.example.service;

import com.example.controller.request.BaseRequest;
import com.example.entity.Book;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface IBookService {

    List<Book> list();

    PageInfo<Book> page(BaseRequest baseRequest);

    void save(Book book);

    Book getById(Integer id);

    void update(Book book);

    void deleteById(Integer id);

    void handleNumber(Book book);
}
