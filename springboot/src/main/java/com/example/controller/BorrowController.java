package com.example.controller;


import com.common.Result;
import com.example.controller.request.BorrowPageRequest;
import com.example.entity.Borrow;
import com.example.service.IBorrowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/borrow")
public class BorrowController {

    @Autowired
    IBorrowService borrowService;

    @PostMapping("/save")
    public Result save(@RequestBody Borrow borrow){
        borrowService.save(borrow);
        return Result.success();
    }



    @PutMapping("/update")
    public Result update(@RequestBody Borrow borrow){
        borrowService.update(borrow);
        return Result.success();
    }

    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable Integer id){
        borrowService.deleteById(id);                 //操纵数据库
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id){
        Borrow borrow = borrowService.getById(id);
        return Result.success(borrow);
    }


    @GetMapping("/list")
    public Result list(){
        List<Borrow> borrows = borrowService.list();
        return Result.success(borrows);
    }

    @GetMapping("/page")
    public Result page(BorrowPageRequest borrowPageRequest){

        return Result.success(borrowService.page(borrowPageRequest));
    }
}
