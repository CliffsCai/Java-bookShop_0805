package com.example.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import com.example.controller.request.BaseRequest;
import com.example.entity.Borrow;
import com.example.mapper.BorrowMapper;
import com.example.service.IBorrowService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Service
public class BorrowService implements IBorrowService {

    @Autowired
    BorrowMapper borrowMapper;

    @Override
    public List<Borrow> list() {
        return borrowMapper.list();
    }

    @Override
    public PageInfo<Borrow> page(BaseRequest baseRequest){
        PageHelper.startPage(baseRequest.getPageNum(), baseRequest.getPageSize());
        List<Borrow> borrows = borrowMapper.listByCondition(baseRequest);
        return new PageInfo<>(borrows);
    }

    @Override
    public void save(Borrow borrow) {
        borrow.setCreatetime(LocalDate.now());
        borrowMapper.save(borrow);
    }

    @Override
    public Borrow  getById(Integer id) {
        return borrowMapper.getById(id);
    }

    @Override
    public void update(Borrow borrow) {

        borrow.setUpdatetime(LocalDate.now());
        borrowMapper.updateById(borrow);
    }

    @Override
    public void deleteById(Integer id) {
        borrowMapper.deleteById(id);
    }
}
