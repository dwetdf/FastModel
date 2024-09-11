package com.example.mylogin.sys.mapper;

import com.example.mylogin.sys.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author wow
 * @since 2024-04-15
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

    List<String> getRoleNameByUserId(Integer userId);
}
