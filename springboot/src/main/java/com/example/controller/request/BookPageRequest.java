package com.example.controller.request;
import lombok.Data;


@Data
public class BookPageRequest extends BaseRequest {
    private String name;
    private Integer book_no;
}
