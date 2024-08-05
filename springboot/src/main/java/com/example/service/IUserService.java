package com.example.service;

import com.example.controller.request.BaseRequest;
import com.example.controller.request.UserPageRequest;
import com.example.entity.User;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface IUserService {

    List<User> list();

    PageInfo<User> page(BaseRequest baseRequest);

    void save(User user);

    User getById(Integer id);

    void update(User user);

    void deleteById(Integer id);

    void handleSocre(User user);
}
