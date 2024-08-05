package com.example.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import com.example.Exception.ServiceException;
import com.example.controller.request.BaseRequest;
import com.example.controller.request.UserPageRequest;
import com.example.entity.User;
import com.example.mapper.UserMapper;
import com.example.service.IUserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class UserService implements IUserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public List<User> list() {
        return userMapper.list();
    }

    @Override
    public PageInfo<User> page(BaseRequest baseRequest){
        PageHelper.startPage(baseRequest.getPageNum(), baseRequest.getPageSize());
        List<User> users = userMapper.listByCondition(baseRequest);
        return new PageInfo<>(users);
    }

    @Override
    public void save(User user) {
        Date date = new Date();
        user.setUsername(DateUtil.format(date, "yyyyMMdd")+Math.abs(IdUtil.fastSimpleUUID().hashCode()));
        try{                                  //用来输出插入重复用户名的提示，不然只能显示系统错误
            userMapper.save(user);
        }catch (Exception e){
            log.error("数据插入失败");
            throw new ServiceException("用户插入失败,用户名已存在");
        }
    }

    @Override
    public User  getById(Integer id) {
        return userMapper.getById(id);
    }

    @Override
    public void update(User user) {
        Date date = new Date();
        user.setUpdatetime(date);
        userMapper.updateById(user);
    }

    @Override
    public void deleteById(Integer id) {
        userMapper.deleteById(id);
    }

    @Override
    public void handleSocre(User user) {
        Integer score = user.getScore();
        if(score==null){
            if(score<0){
                throw new ServiceException("积分不足");
            }else{
                userMapper.updateById(user);
            }

        }else{
            user.setUser_score(user.getUser_score()+score);
            userMapper.updateById(user);
        }


    }
}
