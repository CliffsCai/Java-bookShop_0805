package com.example.controller;


import com.common.Result;
import com.example.controller.request.CategoryPageRequest;
import com.example.entity.Category;
import com.example.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin

@RequestMapping("/category")    // 关系到views中前端网页的地址
@RestController
public class CategoryController {

    @Autowired
    ICategoryService categoryService;

    

    @PostMapping("/save")
    public Result save(@RequestBody Category obj){
        categoryService.save(obj);
        return Result.success();
    }



    @PutMapping("/update")
    public Result update(@RequestBody Category obj){
        categoryService.update(obj);
        return Result.success();
    }

    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable Integer id){
        categoryService.deleteById(id);                 //操纵数据库
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id){
        Category obj = categoryService.getById(id);
        return Result.success(obj);
    }


    @GetMapping("/list")
    public Result list(){
        List<Category> users = categoryService.list();
        return Result.success(users);
    }

    @GetMapping("/page")
    public Result page(CategoryPageRequest categoryPageRequest){

        return Result.success(categoryService.page(categoryPageRequest));
    }
}
