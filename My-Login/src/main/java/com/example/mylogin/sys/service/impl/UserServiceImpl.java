package com.example.mylogin.sys.service.impl;


import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.mylogin.sys.entity.User;
import com.example.mylogin.sys.mapper.UserMapper;
import com.example.mylogin.sys.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wow
 * @since 2024-04-15
 */

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public Map<String, Object> login(User user) {
        // 根据用户名查询
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getUsername, user.getUsername());
        User loginUser = this.baseMapper.selectOne(wrapper);

        // 结果不为空，并且密码与数据库解密后的密码匹配，生成token，将用户信息存入redis
        if (loginUser != null &&
                passwordEncoder.matches(user.getPassword(), loginUser.getPassword())    // 匹配加密密码
        ) {
            // 用UUID，终极方案是jwt
            String key = "user:" + UUID.randomUUID();

            // 存入redis
            loginUser.setPassword(null);    // 设置密码为空
            redisTemplate.opsForValue().set(key, loginUser,10, TimeUnit.MINUTES);   // timeout为登录时间

            // 返回数据
            Map<String, Object> data = new HashMap<>();
            data.put("token",key);
            return data;
        }

        // 结果不为空，生成token，前后端分离，前端无法使用session，可以使用token
        // 并将用户信息存入redis
        return null;
    }

    @Override
    public Map<String,Object> getUserInfo(String token){
        //根据token在redis中获取用户信息,
        Object obj = redisTemplate.opsForValue().get(token);
//        对obj处理成json
        //因为是序列化存储的，使用时要反序列化
        if (obj != null){
            User loginUser = JSON.parseObject(JSON.toJSONString(obj),User.class);
            //取数据
            Map<String,Object> data = new HashMap<>();
            //这个参数中的名字要与前端对应上
            data.put("name",loginUser.getUsername());
            data.put("avatar",loginUser.getAvatar());

            //得到角色列表
            //List<String> roleList = this.baseMapper.getRoleNameByUserId(loginUser.getId());
            // data.put("roles",roleList);

            return data;
        }
        return null;
    }

    @Override
    public void logout(String token) {
        redisTemplate.delete(token);    // 从redis中删除token
    }

}
