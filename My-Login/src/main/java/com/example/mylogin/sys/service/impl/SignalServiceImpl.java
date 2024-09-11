package com.example.mylogin.sys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.mylogin.sys.entity.Signal;
import com.example.mylogin.sys.mapper.SignalMapper;
import com.example.mylogin.sys.service.ISignalService;
import org.springframework.stereotype.Service;

@Service
public class SignalServiceImpl extends ServiceImpl<SignalMapper, Signal> implements ISignalService {
}
