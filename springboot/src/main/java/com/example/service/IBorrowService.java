package com.example.service;

import com.example.controller.request.BaseRequest;
import com.example.entity.Borrow;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface IBorrowService {

    List<Borrow> list();

    PageInfo<Borrow> page(BaseRequest baseRequest);

    void save(Borrow borrow);

    Borrow getById(Integer id);

    void update(Borrow borrow);

    void deleteById(Integer id);
}
