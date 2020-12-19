package com.atguigu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Payement 8001 没有使用服务发现
 * 1. 链路跟踪 sleuth + zipkin。 cloud-consumer-order80 、cloud-provider-payment8001
 */
@SpringBootApplication
public class PaymentMain8001 {
    public static void main(String[] args) {
        SpringApplication.run(PaymentMain8001.class,args);
    }
}
