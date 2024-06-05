package com.example.controller;


import com.common.Result;
import com.example.controller.dto.LoginDTO;
import com.example.controller.request.AdminPageRequest;
import com.example.controller.request.LoginRequest;
import com.example.controller.request.PasswordRequest;
import com.example.entity.Admin;
import com.example.service.IAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin

@RequestMapping("/admin")    // 关系到views中前端网页的地址
@RestController
public class AdminController {

    @Autowired
    IAdminService adminService;

    @PostMapping("/login")
    public Result login(@RequestBody LoginRequest request){
        LoginDTO login = adminService.login(request);
        return Result.success(login);
    }


    @PutMapping("/password")
    public Result password(@RequestBody PasswordRequest request){
        adminService.changePass(request);
        return Result.success();
    }

    @PostMapping("/save")
    public Result save(@RequestBody Admin obj){
        adminService.save(obj);
        return Result.success();
    }



    @PutMapping("/update")
    public Result update(@RequestBody Admin obj){
        adminService.update(obj);
        return Result.success();
    }

    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable Integer id){
        adminService.deleteById(id);                 //操纵数据库
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id){
        Admin obj = adminService.getById(id);
        return Result.success(obj);
    }


    @GetMapping("/list")
    public Result list(){
        List<Admin> users = adminService.list();
        return Result.success(users);
    }

    @GetMapping("/page")
    public Result page(AdminPageRequest adminPageRequest){

        return Result.success(adminService.page(adminPageRequest));
    }
}
