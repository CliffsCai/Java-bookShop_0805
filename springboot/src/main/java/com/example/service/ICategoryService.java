package com.example.service;

import com.example.controller.request.BaseRequest;
import com.example.entity.Category;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface ICategoryService {

    List<Category> list();

    PageInfo<Category> page(BaseRequest baseRequest);

    void save(Category category);

    Category getById(Integer id);

    void update(Category category);

    void deleteById(Integer id);
}
