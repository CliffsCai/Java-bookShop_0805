package com.example.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import com.example.controller.request.BaseRequest;
import com.example.entity.Category;
import com.example.mapper.CategoryMapper;
import com.example.mapper.CategoryMapper;
import com.example.service.ICategoryService;
import com.example.service.ICategoryService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class CategoryService implements ICategoryService {


    @Resource
    CategoryMapper categoryMapper;


    @Override
    public List<Category> list() {
        return categoryMapper.list();
    }

    @Override
    public PageInfo<Category> page(BaseRequest baseRequest){
        PageHelper.startPage(baseRequest.getPageNum(), baseRequest.getPageSize());
        List<Category> categories = categoryMapper.listByCondition(baseRequest);
        return new PageInfo<>(categories);
    }

    @Override
    public void save(Category category) {
        categoryMapper.save(category);
    }

    @Override
    public Category  getById(Integer id) {
        return categoryMapper.getById(id);
    }

    @Override
    public void update(Category obj) {
        obj.setUpdatetime(LocalDate.now());
        categoryMapper.updateById(obj);
    }

    @Override
    public void deleteById(Integer id) {
        categoryMapper.deleteById(id);
    }
}
