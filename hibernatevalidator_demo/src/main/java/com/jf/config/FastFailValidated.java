package com.jf.config;

import org.hibernate.validator.HibernateValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.validation.Validation;
import javax.validation.Validator;

/**
 * @author seinfish
 * @version 1.0
 * @description TODO
 * @date 2023/5/22 14:02
 */
public class FastFailValidated {

    @Bean
    public Validator validator(){
        // 在官网抄的配置12.2
        return Validation.byProvider(HibernateValidator.class)
                .configure()
                .failFast(true)
                .buildValidatorFactory()
                .getValidator();
    }
}
