package com.example.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;

@Data
public class Borrow {
    private Integer id;
    private String book_name;
    private Integer book_no;
    private Integer book_number;
    private String username;           //username
    private String name;     //user's name
    private String user_phone;
    @JsonFormat(pattern  = "yyyy-MM-dd", timezone = "GMT+8")
    private LocalDate createtime;
    @JsonFormat(pattern  = "yyyy-MM-dd", timezone = "GMT+8")
    private LocalDate updatetime;
    private Integer score;
    private Integer borrow_num;




}
