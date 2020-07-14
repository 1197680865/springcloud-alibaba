package com.atguigu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * hystrix 既可以放在服务端也可以放在消费端，一般放在消费端
 *
 * 服务提供者端使用hystrix
 * 1.设置自身调用超时的峰值，峰值内可正常运行
 * 2.超时处理方法，服务降级fallback handler
 * 此外，还可以在消费者端order 80 做服务降级
 *
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableCircuitBreaker //开启hystrix熔断降级
public class PaymentHystrixMain8001 {
    public static void main(String[] args) {
        SpringApplication.run(PaymentHystrixMain8001.class,args);
    }
}
