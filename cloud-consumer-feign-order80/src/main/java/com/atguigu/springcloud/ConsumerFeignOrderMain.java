package com.atguigu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;


/**
 * hystrix 既可以放在服务端也可以放在消费端，一般放在消费端
 * @EnableHystrix //开启 hystrix
 */
@SpringBootApplication
@EnableFeignClients //开启feign  feign默认使用ribbon做负载均衡
@EnableHystrix //开启 hystrix
public class ConsumerFeignOrderMain {
    public static void main(String[] args) {
        SpringApplication.run(ConsumerFeignOrderMain.class,args);
    }
}
