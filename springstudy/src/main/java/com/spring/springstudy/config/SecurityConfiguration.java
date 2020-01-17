package com.spring.springstudy.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.header.Header;
import org.springframework.security.web.header.writers.StaticHeadersWriter;

import java.util.Arrays;

/**
 * @author chenlilai
 * @title: SecurityConfiguration
 * @projectName javaStudy
 * @description:
 * @date 2020/1/616:14
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {


    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
         .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) //// 禁用 Spring Security 自带的跨域处理
         .and()
         .csrf().disable()//  CRSF禁用，因为不使用session
         .cors().and().headers().addHeaderWriter(new StaticHeadersWriter(Arrays.asList(
                new Header("Access-control-Allow-Origin","*")))).and()
         .authorizeRequests()
         .antMatchers("/web/**").permitAll()
         .antMatchers("api/**").hasAuthority("api")
        .anyRequest().authenticated();

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
