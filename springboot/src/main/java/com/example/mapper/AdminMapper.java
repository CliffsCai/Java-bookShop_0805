package com.example.mapper;

import com.example.controller.request.BaseRequest;
import com.example.controller.request.UserPageRequest;
import com.example.entity.Admin;
import com.example.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AdminMapper {

    List<Admin> list();


    List<Admin> listByCondition(BaseRequest baseRequest);

    void save(Admin obj);

    Admin getById(Integer id);

    void updateById(Admin obj);

    void deleteById(Integer id);
}
