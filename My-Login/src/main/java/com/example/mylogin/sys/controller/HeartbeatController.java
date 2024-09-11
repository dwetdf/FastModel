package com.example.mylogin.sys.controller;

import com.example.mylogin.sys.service.Embedded.LinkTest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/test4")
public class HeartbeatController {
    @GetMapping("/heartbeat")
    public Result<List<Map<String, Object>>> reportHeartbeat() {
        List<Map<String, Object>> Hb = LinkTest.Heartbeat();
        return Result.success(Hb);
    }
    @GetMapping("/taskcomm")
    public Result<List<Map<String, Object>>> reportTaskComm() {
        List<Map<String, Object>> Tc = LinkTest.TaskComm();
        return Result.success(Tc);
    }
    @GetMapping("/weblink")
    public Result<List<Map<String, Object>>> reportWebLink() {
        List<Map<String, Object>> Wl = LinkTest.WebLink();
        return Result.success(Wl);
    }
}
