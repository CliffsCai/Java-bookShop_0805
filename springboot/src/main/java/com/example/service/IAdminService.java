package com.example.service;

import com.example.controller.dto.LoginDTO;
import com.example.controller.request.BaseRequest;
import com.example.controller.request.LoginRequest;
import com.example.controller.request.PasswordRequest;
import com.example.entity.Admin;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface IAdminService {

    List<Admin> list();

    PageInfo<Admin> page(BaseRequest baseRequest);

    void save(Admin obj);

    Admin getById(Integer id);

    void update(Admin user);

    void deleteById(Integer id);

    LoginDTO login(LoginRequest request);

    void changePass(PasswordRequest request);
}

