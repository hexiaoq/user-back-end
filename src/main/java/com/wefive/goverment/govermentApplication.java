package com.wefive.goverment;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

@MapperScan("com.wefive.goverment.dao")
//@ComponentScan("com.wefive.goverment.entity")
@SpringBootApplication
public class govermentApplication extends SpringBootServletInitializer{
    public static void main(String[] args) {
        SpringApplication.run(govermentApplication.class, args);
    }
    @Override//为了打包springboot项目
    protected SpringApplicationBuilder configure(
            SpringApplicationBuilder builder) {
        return builder.sources(this.getClass());
    }
    
}
