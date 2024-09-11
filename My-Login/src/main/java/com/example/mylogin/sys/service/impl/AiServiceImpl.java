package com.example.mylogin.sys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.mylogin.sys.entity.Ai;
import com.example.mylogin.sys.mapper.AiMapper;
import com.example.mylogin.sys.service.IAiService;
import org.springframework.stereotype.Service;

@Service
public class AiServiceImpl extends ServiceImpl<AiMapper, Ai> implements IAiService {
}
