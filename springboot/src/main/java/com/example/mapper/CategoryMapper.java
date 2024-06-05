package com.example.mapper;

import com.example.controller.request.BaseRequest;
import com.example.entity.Category;
import com.example.entity.Category;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CategoryMapper {

    //@Select("select * from user")
    List<Category> list();


    List<Category> listByCondition(BaseRequest baseRequest);

    void save(Category user);

    Category getById(Integer id);

    void updateById(Category user);

    void deleteById(Integer id);
}
