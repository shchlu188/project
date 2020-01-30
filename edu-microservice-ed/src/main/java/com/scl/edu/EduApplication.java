package com.scl.edu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created with IntelliJ IDEA.
 * User: chenglu
 * Date: 2020/1/17
 * Description:
 */
//@EnableEurekaClient
@SpringBootApplication
@ComponentScan(basePackages = {"com.scl.edu", "com.scl.common"})
public class EduApplication {
    public static void main(String[] args) {

        SpringApplication.run(EduApplication.class, args);
    }
}
