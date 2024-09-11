package com.example.mylogin;

import com.example.mylogin.sys.entity.User;
import com.example.mylogin.sys.mapper.UserMapper;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@MapperScan("com.example.mylogin.sys.mapper")
@SpringBootTest
class MyLoginApplicationTests {

	@Resource
	private UserMapper userMapper;

	@Test
	void testMapper() {
		List<User> users = userMapper.selectList(null);
		users.forEach(System.out::println);
	}

}
