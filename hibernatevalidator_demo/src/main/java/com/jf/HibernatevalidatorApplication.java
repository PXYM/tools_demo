package com.jf;

import com.jf.config.EnableFastFailValidated;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableFastFailValidated
@SpringBootApplication
public class HibernatevalidatorApplication {

    public static void main(String[] args) {
        SpringApplication.run(HibernatevalidatorApplication.class, args);
    }

}
