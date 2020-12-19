package com.atguigu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 1. 链路跟踪 sleuth + zipkin。 cloud-consumer-order80 、cloud-provider-payment8001
 */
@SpringBootApplication
public class ConsumerOrderMain80 {
    public static void main(String[] args) {
        SpringApplication.run(ConsumerOrderMain80.class,args);
    }
}
