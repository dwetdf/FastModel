package com.example.mylogin.sys.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.mylogin.sys.entity.User;
import com.example.mylogin.sys.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wow
 * @since 2024-04-15
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private IUserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/all")
    public Result<List<User>> getAllUser() {
        List<User> list = userService.list();
        return Result.success(list,"查询成功");
    }

    @PostMapping("/login")
    //这个user接收过来时是一个json格式，需要用@RequestMapping转换为实体类
    public Result<Map<String,Object>> login(@RequestBody User user){
        Map<String,Object> data = userService.login(user);
        if (data != null){
            return Result.success(data);
        }
        return Result.error(2002,"用户名或密码错误");
    }
    @GetMapping("/info")
    //参数是在url后面的，用@RequestParam可以获取到
    public Result<Map<String,Object>> getUserInfo(@RequestParam("token") String token){
        //根据token从redis中获取用户信息
        Map<String, Object> data = userService.getUserInfo(token);
        if (data != null){
            return Result.success(data);
        }
        return Result.error(2003,"登录信息无效");
    }

    @PostMapping("/logout")
    public Result<?> logout(@RequestHeader("X-Token") String token) {
        userService.logout(token);
        return Result.success();
    }
    // 增删改查，增：post请求，查：get请求，修改：put请求，删除：delete请求
    @GetMapping("/list")
    public Result<Map<String,Object>> getUserList(@RequestParam(value = "username",required = false) String username,
                                                  @RequestParam(value = "phone",required = false) String phone,
                                                  @RequestParam(value = "pageNo") Long pageNo,
                                                  @RequestParam(value = "pageSize") Long pageSize)
    {

        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();

        // 第一个参数对username进行判空条件，第三个username是传过来进行比较的值
        wrapper.eq(StringUtils.hasLength(username),User::getUsername,username);
        wrapper.eq(StringUtils.hasLength(phone),User::getPhone,phone);

        // 传入当前页数和每页大小
        Page<User> page = new Page<>(pageNo, pageSize);

        // 分页查找，用 .page()方法
        userService.page(page,wrapper);

        Map<String,Object> data = new HashMap<>();
        data.put("total",page.getTotal());
        data.put("rows",page.getRecords());

        return Result.success(data);
    }
    // 前端addUser方法的url就是"/user" 故此处无url
    @PostMapping
    public Result<?> addUser(@RequestBody User user){
        // @RequestBody 用于json转为实体对象

        // 做了加盐处理
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.save(user);
        return Result.success("新增用户成功");
    }
    @PutMapping
    //前端传来的user对象是一个json格式
    public Result<?> updateUser(@RequestBody User user){
        user.setPassword(null);
        userService.updateById(user);
        return Result.success("修改用户成功");
    }

    //根据id查询用户信息，方便修改的时候在框中展现信息以方便对照修改
    @GetMapping("/{id}")
    public Result<User> getUserById(@PathVariable("id") Integer id){
        User user = userService.getById(id);
        return Result.success(user);
    }


    @DeleteMapping("/{id}")
    public Result<?> deleteUserById(@PathVariable("id") Integer id){
        userService.removeById(id);
        return Result.success("删除用户成功");
    }

}
