package com.atguigu.springcloud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RefreshScope //配置动态刷新
public class ConsumerOrderController {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${service-url.alibaba-pay}")
    private String PAY_SERVICE;

    @Value("${config.info}")
    private String configInfo;

    @GetMapping("consumer/payment/get/{id}")
    public String getPayment(@PathVariable("id") Integer id){
        return restTemplate.getForObject(PAY_SERVICE + "/payment/get/" + id,String.class);
    }

    @GetMapping("consumer/config/info")
    public String getConfigInfo(){
        return configInfo;
    }

}
