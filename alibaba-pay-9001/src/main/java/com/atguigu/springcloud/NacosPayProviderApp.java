package com.atguigu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * nacos 服务提供者
 * https://github.com/alibaba/spring-cloud-alibaba/wiki/Nacos-discovery
 * 1.使用nacos作为注册中心
 *
 */
@SpringBootApplication
@EnableDiscoveryClient
public class NacosPayProviderApp {
    public static void main(String[] args) {
        SpringApplication.run(NacosPayProviderApp.class,args);
    }
}
