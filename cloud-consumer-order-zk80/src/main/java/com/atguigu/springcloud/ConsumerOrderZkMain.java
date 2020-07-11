package com.atguigu.springcloud;

import com.atguigu.rule.MySelfRule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;

@SpringBootApplication
@EnableDiscoveryClient
//RibbonClient。configuration 使用自定义的ribbon负载均衡规则
@RibbonClient(name = "cloud-provider-payment" ,configuration = MySelfRule.class)
public class ConsumerOrderZkMain {
    public static void main(String[] args) {
        SpringApplication.run(ConsumerOrderZkMain.class,args);
    }
}
