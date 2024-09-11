package com.example.mylogin.sys.controller;

import com.example.mylogin.sys.service.Embedded.CpuUsage;
import com.example.mylogin.sys.service.Embedded.Memory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/display4")
public class ModuleStatusController {
    @GetMapping("/CpuUsage/{id}")
    public Result<Double> getCpuUsage(@PathVariable("id") Integer id) {
        double cpuUsage = CpuUsage.getCpuUsageById(id);
        return Result.success(cpuUsage);
    }
    @GetMapping("/MemUsage/{id}")
    public Result<Double> getMemUsage(@PathVariable("id") Integer id) {
        double memUsage = Memory.getMemUsageById(id);
        return Result.success(memUsage);
    }
    @GetMapping("/TotalMem/{id}")
    public Result<Double> getTotalMem(@PathVariable("id") Integer id) {
        double totalMem = Memory.getTotalMemById(id);
        return Result.success(totalMem);
    }
    @GetMapping("/LastMem/{id}")
    public Result<Double> getLastMem(@PathVariable("id") Integer id) {
        double lastMem = Memory.getLastMemById(id);
        return Result.success(lastMem);
    }
}
