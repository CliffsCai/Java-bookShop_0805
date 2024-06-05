package com.example.controller.request;

import lombok.Data;

@Data
public class PasswordRequest {
    public String username;
    public String password;
    public String newPass;
}
