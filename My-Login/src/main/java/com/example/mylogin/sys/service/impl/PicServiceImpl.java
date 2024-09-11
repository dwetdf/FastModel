package com.example.mylogin.sys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.mylogin.sys.entity.Pic;
import com.example.mylogin.sys.mapper.PicMapper;
import com.example.mylogin.sys.service.IPicService;
import org.springframework.stereotype.Service;

@Service
public class PicServiceImpl extends ServiceImpl<PicMapper, Pic> implements IPicService {
}
