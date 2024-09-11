package com.example.mylogin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@MapperScan ("com.example.mylogin.sys.mapper")
@SpringBootApplication
public class MyLoginApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyLoginApplication.class, args);
	}

    @Bean
    public PasswordEncoder passwordEncoder(){
        // 密码加密的工具类，来自依赖spring-security-core
        return new BCryptPasswordEncoder();
    }
}
