package com.example.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import com.example.Exception.ServiceException;
import com.example.controller.request.BaseRequest;
import com.example.entity.Book;
import com.example.mapper.BookMapper;
import com.example.service.IBookService;
import com.example.service.IBookService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
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
        try{                                  //用来输出插入重复用户名的提示，不然只能显示系统错误
            bookMapper.save(book);
        }catch (Exception e){
            log.error("数据插入失败");
            throw new ServiceException("用户插入失败,用户名已存在");
        }
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

    @Override
    public void handleNumber(Book book) {
        if(book.getNumber()<0){
            throw new ServiceException("图书数量不足");
        }else{
            bookMapper.updateById(book);
        }

    }
}
