package com.example.controller.request;
import lombok.Data;


@Data
public class BorrowPageRequest extends BaseRequest {
    private String book_name;
    private String name;     //user's name
}
