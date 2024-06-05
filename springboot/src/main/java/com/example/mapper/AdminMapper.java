package com.example.mapper;

import com.example.controller.request.BaseRequest;
import com.example.controller.request.LoginRequest;
import com.example.controller.request.PasswordRequest;
import com.example.controller.request.UserPageRequest;
import com.example.entity.Admin;
import com.example.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AdminMapper {     //链接数据库的class

    List<Admin> list();


    List<Admin> listByCondition(BaseRequest baseRequest);

    void save(Admin obj);

    Admin getById(Integer id);

    void updateById(Admin obj);

    void deleteById(Integer id);

    Admin getByUsernameAndPassword(@Param("username") String username, @Param("password") String password);

    int updatePassword(PasswordRequest request);

    Admin getByUsername(String username);
}
