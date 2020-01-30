package com.scl.edumicroservicesysuser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class EduMicroserviceSysuserApplication {

    public static void main(String[] args) {
        SpringApplication.run(EduMicroserviceSysuserApplication.class, args);
    }

}
