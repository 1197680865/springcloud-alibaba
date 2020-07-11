package com.atguigu.rule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MySelfRule {

    @Bean
    public IRule myRule(){
        //默认是轮询的方式
        //RandomRule 随机
        //CustomRandomRule 自定义随机
        return new CustomRandomRule();
    }
}
