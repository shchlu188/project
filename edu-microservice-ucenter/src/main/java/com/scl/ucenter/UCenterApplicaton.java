package com.scl.ucenter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created with IntelliJ IDEA.
 * User: chenglu
 * Date: 2020/1/28
 * Description:
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.scl.ucenter","com.scl.common"})
@EnableEurekaClient
public class UCenterApplicaton {
    public static void main(String[] args){
      SpringApplication.run(UCenterApplicaton.class,args);
    }
}
