package com.zhcnnet.zenglish;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@ComponentScan(basePackages="com.zhcnnet.zenglish")
@EnableTransactionManagement
public class App
{
    public static void main( String[] args )
    {
    	SpringApplication.run(App.class, args);
    }
}