package com.example.controller;


import cn.hutool.core.collection.CollUtil;
import com.common.Result;
import com.example.controller.request.BookPageRequest;
import com.example.entity.Book;
import com.example.service.IBookService;
import org.apache.ibatis.type.NStringTypeHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    IBookService bookService;

    @PostMapping("/save")
    public Result save(@RequestBody Book book){

        // 取categories的list传到category中
        List<String> categories = book.getCategories();
        StringBuilder sb = new StringBuilder();
        if(CollUtil.isNotEmpty(categories)){
            categories.forEach(v -> sb.append(v).append(" > "));
            book.setCategory(sb.toString().substring(0, sb.lastIndexOf(" > ")));     //去除最后一个箭头
        }



        bookService.save(book);
        return Result.success();
    }



    @PutMapping("/update")
    public Result update(@RequestBody Book book){

        List<String> categories = book.getCategories();
        StringBuilder sb = new StringBuilder();
        if(CollUtil.isNotEmpty(categories)){
            categories.forEach(v -> sb.append(v).append(" > "));
            book.setCategory(sb.toString().substring(0, sb.lastIndexOf(" > ")));     //去除最后一个箭头
        }
        bookService.update(book);
        return Result.success();
    }

    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable Integer id){
        bookService.deleteById(id);                 //操纵数据库
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id){
        Book book = bookService.getById(id);
        return Result.success(book);
    }


    @GetMapping("/list")
    public Result list(){
        List<Book> books = bookService.list();
        return Result.success(books);
    }

    @GetMapping("/page")
    public Result page(BookPageRequest bookPageRequest){

        return Result.success(bookService.page(bookPageRequest));
    }
}
