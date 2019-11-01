package com.rhenium.timelinebackend;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.rhenium.timelinebackend.mapper")
public class TimelineBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(TimelineBackendApplication.class, args);
    }

}
