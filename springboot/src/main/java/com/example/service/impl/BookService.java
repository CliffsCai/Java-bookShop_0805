package com.example.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import com.example.controller.request.BaseRequest;
import com.example.entity.Book;
import com.example.mapper.BookMapper;
import com.example.service.IBookService;
import com.example.service.IBookService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Service
public class BookService implements IBookService {

    @Autowired
    BookMapper bookMapper;

    @Override
    public List<Book> list() {
        return bookMapper.list();
    }

    @Override
    public PageInfo<Book> page(BaseRequest baseRequest){
        PageHelper.startPage(baseRequest.getPageNum(), baseRequest.getPageSize());
        List<Book> books = bookMapper.listByCondition(baseRequest);
        return new PageInfo<>(books);
    }

    @Override
    public void save(Book book) {

        bookMapper.save(book);
    }

    @Override
    public Book  getById(Integer id) {
        return bookMapper.getById(id);
    }

    @Override
    public void update(Book book) {

        book.setUpdatetime(LocalDate.now());
        bookMapper.updateById(book);
    }

    @Override
    public void deleteById(Integer id) {
        bookMapper.deleteById(id);
    }
}
