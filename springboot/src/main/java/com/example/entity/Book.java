package com.example.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class Book {
    private Integer id;
    private String name;
    private String description;
    private String publish_date;
    private String author;
    private String publisher;
    private String category;
    private Integer book_no;
    private String cover;
    @JsonFormat(pattern  = "yyyy-MM-dd", timezone = "GMT+8")
    private LocalDate createtime;
    @JsonFormat(pattern  = "yyyy-MM-dd", timezone = "GMT+8")
    private LocalDate updatetime;
    private Integer score;

    private List<String> categories;   //传进新增的



}
