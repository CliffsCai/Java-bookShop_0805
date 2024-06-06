package com.example.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;

@Data
public class Borrow {
    private int id;
    private String book_name;
    private int book_no;
    private String username;           //username
    private String name;     //name
    private String user_phone;
    @JsonFormat(pattern  = "yyyy-MM-dd", timezone = "GMT+8")
    private LocalDate createtime;
    @JsonFormat(pattern  = "yyyy-MM-dd", timezone = "GMT+8")
    private LocalDate updatetime;
    private int score;




}
