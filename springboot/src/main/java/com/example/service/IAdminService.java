package com.example.service;

import com.example.controller.request.BaseRequest;
import com.example.controller.request.UserPageRequest;
import com.example.entity.Admin;
import com.example.entity.User;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface IAdminService {

    List<Admin> list();

    PageInfo<Admin> page(BaseRequest baseRequest);

    void save(Admin obj);

    Admin getById(Integer id);

    void update(Admin user);

    void deleteById(Integer id);
}
