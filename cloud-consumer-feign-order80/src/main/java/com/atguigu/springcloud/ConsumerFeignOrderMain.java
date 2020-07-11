package com.atguigu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients //开启feign  feign默认使用ribbon做负载均衡
public class ConsumerFeignOrderMain {
    public static void main(String[] args) {
        SpringApplication.run(ConsumerFeignOrderMain.class,args);
    }
}
