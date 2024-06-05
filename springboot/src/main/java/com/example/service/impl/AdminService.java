package com.example.service.impl;

import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import com.example.Exception.ServiceException;
import com.example.controller.dto.LoginDTO;
import com.example.controller.request.BaseRequest;
import com.example.controller.request.LoginRequest;
import com.example.controller.request.PasswordRequest;
import com.example.entity.Admin;
import com.example.mapper.AdminMapper;
import com.example.service.IAdminService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class AdminService implements IAdminService {

    @Autowired
    AdminMapper adminMapper;

    private static final String DEFAULT_PASSWORD = "123";
    private static final String PASS_SALT = "caige";
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
        if(StrUtil.isBlank(obj.getPassword())){      //设置密码
            obj.setPassword(DEFAULT_PASSWORD);
        }
       obj.setPassword(securePass(obj.getPassword()));     //对数据库中的密码进行  md5 加密 加盐

        try{                                  //用来输出插入重复用户名的提示，不然只能显示系统错误
            adminMapper.save(obj);
        }catch (Exception e){
            log.error("数据插入失败");
            throw new ServiceException("用户插入失败,用户名已存在");
}

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

    @Override
    public LoginDTO login(LoginRequest request)   //返回LoginDTO
    {
        Admin admin = null;

        request.setPassword(securePass(request.getPassword()));    //md5 加密 加盐


        // todo 检测重复用户名，待改进
        try{
             admin = adminMapper.getByUsername(request.getUsername());
        }catch (Exception e){
            log.error("根据用户名{}查询出错", request.getUsername());
            throw new ServiceException("用户名错误");
        }

//        String securePass = securePass(admin.getPassword());
//        if (!securePass.equals(admin.getPassword())){
//            throw new ServiceException("用户名或密码错误");
//        }




        if (admin == null){
            throw new ServiceException("用户名或密码错误");
        }

        if (!admin.isStatus()){
            throw new ServiceException("当前用户已被禁用");
        }
        LoginDTO loginDTO = new LoginDTO();
        BeanUtils.copyProperties(admin,loginDTO);
        return loginDTO;
    }

    @Override
    public void changePass(PasswordRequest request) {
        request.setNewPass(securePass(request.getNewPass()));  //加密
       int count = adminMapper.updatePassword(request);
       if (count <= 0){
           throw new ServiceException("修改失败");
       }
    }

    private String securePass(String password){
        return SecureUtil.md5(password+ PASS_SALT);
    }
}
