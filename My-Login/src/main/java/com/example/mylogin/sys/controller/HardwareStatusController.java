package com.example.mylogin.sys.controller;

import com.example.mylogin.sys.service.Embedded.CpuUsage;
import com.example.mylogin.sys.service.Embedded.Memory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/display1")
public class HardwareStatusController {

    @GetMapping("/CpuUsage")
    public Result<Double> getCpuUsage() {
        double cpuUsage = CpuUsage.getCpuUsage();
        return Result.success(cpuUsage);
    }
    @GetMapping("/MemUsage")
    public Result<Double> getMemUsage() {
        double memUsage = Memory.getMemUsage();
        return Result.success(memUsage);
    }
    @GetMapping("/TotalMem")
    public Result<Double> getTotalMem() {
        double totalMem = Memory.getTotalMem();
        return Result.success(totalMem);
    }
    @GetMapping("/LastMem")
    public Result<Double> getLastMem() {
        double lastMem = Memory.getLastMem();
        return Result.success(lastMem);
    }
}