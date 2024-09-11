package com.example.mylogin.sys.controller;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

//这个类是为了前后端分离之后的前后端交互统一结果的相应
@Data
//这个注解可以自动为属性生成get&set方法
@NoArgsConstructor
@AllArgsConstructor
public class Result<T> {
    private Integer code;   //响应码 20000-成功 20001失败
    private String message; //响应信息 描述字符串
    private T data;         //返回的数据

    public static <T> Result<T> success(){
        //增删改 成功响应
        return new Result<>(20000,"success",null);
    }

    public static <T> Result<T> success(T data){
        //查询 成功响应
        return new Result<>(20000,"success",data);
    }

    public static <T> Result<T> success(T data,String message){
        return new Result<>(20000,message,data);
    }

    public static <T> Result<T> success(String message){
        return new Result<>(20000,message,null);
    }

    public static<T>  Result<T> error(){
        return new Result<>(20001,"fail",null);
    }

    public static<T>  Result<T> error(Integer code){
        return new Result<>(code,"fail",null);
    }

    public static<T>  Result<T> error(Integer code, String message){
        return new Result<>(code,message,null);
    }

    public static<T>  Result<T> error(String message){
        return new Result<>(20001,message,null);
    }

}