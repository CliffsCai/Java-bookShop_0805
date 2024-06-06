package com.example.controller;


import cn.hutool.core.collection.CollUtil;
import com.common.Result;
import com.example.controller.request.CategoryPageRequest;
import com.example.entity.Category;
import com.example.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
        List<Category> list = categoryService.list();
        return Result.success(list);
    }

    @GetMapping("/tree")
    public Result tree(){
        List<Category> list = categoryService.list();

//       List<Category> treelist =  list.stream().filter(v -> v.getPid() == null).collect(Collectors.toList());  //第一层


        return Result.success(createTree(null,list));
    }

    private List<Category> createTree(Integer pid, List<Category> categories) {   //遍历
        List<Category> treeList = new ArrayList<>();
        for (Category category : categories) {
            if(pid==null){
                if(category.getPid()==null){
                    treeList.add(category);
                    category.setChildren(createTree(category.getId(),categories));
                }
            }else{
                if (pid.equals( category.getPid())) {    //如果相等传到treeList中
                    treeList.add(category);
                    category.setChildren(createTree(category.getId(), categories));
                }
            }
            if(CollUtil.isEmpty(category.getChildren())){
                category.setChildren(null);
            }
        }
        return treeList;
    }




    @GetMapping("/page")
    public Result page(CategoryPageRequest categoryPageRequest){
        return Result.success(categoryService.page(categoryPageRequest));
    }
}
