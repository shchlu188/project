package com.scl.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * Created with IntelliJ IDEA.
 * User: chenglu
 * Date: 2020/1/28
 * Description:
 */
@EnableEurekaServer
@SpringBootApplication
public class EurkaServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(EurkaServerApplication.class, args);
    }
}
