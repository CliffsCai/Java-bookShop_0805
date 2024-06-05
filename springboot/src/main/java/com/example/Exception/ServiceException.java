package com.example.Exception;


public class ServiceException extends RuntimeException {

    private String code;

    public String getCode(){
        return code;
    }

    public ServiceException(String massage) {
        super(massage);
    }
    public ServiceException(String massage, String code) {

        super(massage);
        this.code=code;
    }
}
