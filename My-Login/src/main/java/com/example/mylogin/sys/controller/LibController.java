package com.example.mylogin.sys.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.mylogin.sys.entity.Ai;
import com.example.mylogin.sys.entity.Signal;
import com.example.mylogin.sys.entity.Pic;
import com.example.mylogin.sys.service.IAiService;
import com.example.mylogin.sys.service.ISignalService;
import com.example.mylogin.sys.service.IPicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/lib")
public class LibController {
    private final ISignalService signalService;
    private final IPicService picService;
    private final IAiService aiService;


    @Autowired
    public LibController(ISignalService signalService,IPicService picService,IAiService aiService) {
        this.signalService = signalService;
        this.picService = picService;
        this.aiService = aiService;
    }


    @GetMapping("/signal")
    public Result<Map<String,Object>> GetSignal(@RequestParam(value = "shortname",required = false) String shortname,
                                                @RequestParam(value = "user",required = false) String user,
                                                @RequestParam(value = "pageNo") Long pageNo,
                                                @RequestParam(value = "pageSize") Long pageSize){

        LambdaQueryWrapper<Signal> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(StringUtils.hasLength(shortname), Signal::getShortname,shortname);
        wrapper.eq(StringUtils.hasLength(user),Signal::getUser,user);
        // 苞米豆 里的Page包，不是Spring的，传入当前页数和每页大小
        Page<Signal> page = new Page<>(pageNo, pageSize);

        // 分页查找，用 .page()方法
        this.signalService.page(page,wrapper);

        Map<String,Object> data = new HashMap<>();
        data.put("total",page.getTotal());
        data.put("rows",page.getRecords());

        return Result.success(data);
    }
    @GetMapping("/pic")
    public Result<Map<String,Object>> GetPic(@RequestParam(value = "shortname",required = false) String shortname,
                                                @RequestParam(value = "user",required = false) String user,
                                                @RequestParam(value = "pageNo") Long pageNo,
                                                @RequestParam(value = "pageSize") Long pageSize){

        LambdaQueryWrapper<Pic> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(StringUtils.hasLength(shortname), Pic::getShortname,shortname);
        wrapper.eq(StringUtils.hasLength(user),Pic::getUser,user);
        // 苞米豆 里的Page包，不是Spring的，传入当前页数和每页大小
        Page<Pic> page = new Page<>(pageNo, pageSize);

        // 分页查找，用 .page()方法
        this.picService.page(page,wrapper);

        Map<String,Object> data = new HashMap<>();
        data.put("total",page.getTotal());
        data.put("rows",page.getRecords());

        return Result.success(data);
    }
    @GetMapping("/ai")
    public Result<Map<String,Object>> GetAi(@RequestParam(value = "shortname",required = false) String shortname,
                                             @RequestParam(value = "user",required = false) String user,
                                             @RequestParam(value = "pageNo") Long pageNo,
                                             @RequestParam(value = "pageSize") Long pageSize){

        LambdaQueryWrapper<Ai> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(StringUtils.hasLength(shortname), Ai::getShortname,shortname);
        wrapper.eq(StringUtils.hasLength(user),Ai::getUser,user);
        // 苞米豆 里的Page包，不是Spring的，传入当前页数和每页大小
        Page<Ai> page = new Page<>(pageNo, pageSize);

        // 分页查找，用 .page()方法
        this.aiService.page(page,wrapper);

        Map<String,Object> data = new HashMap<>();
        data.put("total",page.getTotal());
        data.put("rows",page.getRecords());

        return Result.success(data);
    }
}
