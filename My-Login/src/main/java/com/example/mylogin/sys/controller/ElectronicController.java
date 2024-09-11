package com.example.mylogin.sys.controller;

import com.example.mylogin.sys.service.Embedded.Electricity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/display3")
public class ElectronicController {

    @GetMapping("/module")
    public Result<List<Map<String, Object>>> getModule() {
        List<Map<String, Object>> module = Electricity.getModule();
        return Result.success(module);
    }

}