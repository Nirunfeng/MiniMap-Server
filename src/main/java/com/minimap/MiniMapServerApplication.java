package com.minimap;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan(value ="com.minimap.dao")
@ComponentScan(basePackages={"com.minimap","com.idworker"})
public class MiniMapServerApplication extends SpringBootServletInitializer {
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(MiniMapServerApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(MiniMapServerApplication.class, args);
    }

}
