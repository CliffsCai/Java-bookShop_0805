package com.example.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import com.example.controller.request.BaseRequest;
import com.example.entity.Admin;
import com.example.entity.User;
import com.example.mapper.AdminMapper;
import com.example.mapper.UserMapper;
import com.example.service.IAdminService;
import com.example.service.IUserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class AdminService implements IAdminService {

    @Autowired
    AdminMapper adminMapper;

    @Override
    public List<Admin> list() {
        return adminMapper.list();
    }

    @Override
    public PageInfo<Admin> page(BaseRequest baseRequest){
        PageHelper.startPage(baseRequest.getPageNum(), baseRequest.getPageSize());
        List<Admin> admins = adminMapper.listByCondition(baseRequest);
        return new PageInfo<>(admins);
    }

    @Override
    public void save(Admin obj) {
        Date date = new Date();
        obj.setUsername(DateUtil.format(date, "yyyyMMdd")+Math.abs(IdUtil.fastSimpleUUID().hashCode()));
        adminMapper.save(obj);
    }

    @Override
    public Admin  getById(Integer id) {
        return adminMapper.getById(id);
    }

    @Override
    public void update(Admin admin) {
        Date date = new Date();
        admin.setUpdatetime(date);
        adminMapper.updateById(admin);
    }

    @Override
    public void deleteById(Integer id) {
        adminMapper.deleteById(id);
    }
}
