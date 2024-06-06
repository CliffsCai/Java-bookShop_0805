package com.example.mapper;

import com.example.controller.request.BaseRequest;
import com.example.entity.Borrow;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BorrowMapper {

    //@Select("select * from borrow")
    List<Borrow> list();


    List<Borrow> listByCondition(BaseRequest baseRequest);

    void save(Borrow borrow);

    Borrow getById(Integer id);

    void updateById(Borrow borrow);

    void deleteById(Integer id);
}
