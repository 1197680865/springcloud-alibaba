package com.atguigu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Payement 8004 使用Zookeeper做服务发现
 */
@SpringBootApplication
@EnableDiscoveryClient
public class PayementMain8004 {
    public static void main(String[] args) {
        SpringApplication.run(PayementMain8004.class,args);
    }
}
