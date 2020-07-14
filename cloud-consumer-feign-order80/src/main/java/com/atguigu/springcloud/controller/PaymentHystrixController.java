package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.service.PaymentFeignHystrixService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaymentHystrixController {

    @Autowired
    private PaymentFeignHystrixService paymentFeignHystrixService;

    @GetMapping("/consumer/payment/hystrix/ok/{id}")
    @ApiOperation(value = "正常访问",tags = "支付管理(Hystrix)")
    public String paymentInfoOk(@PathVariable("id") Integer id){
        return paymentFeignHystrixService.paymentInfoOk(id);
    }


    @GetMapping("/consumer/payment/hystrix/timeout/{id}")
    @ApiOperation(value = "超时3秒访问",tags = "支付管理(Hystrix)")
    public String paymentInfoTimeout(@PathVariable("id") Integer id){
        //TODO 为什么会报出超时异常？ java.util.concurrent.TimeoutException: null
        return paymentFeignHystrixService.paymentInfoTimeout(id);
    }

    @GetMapping("/consumer/payment/hystrixby80/timeout/{id}")
    @ApiOperation(value = "超时访问",tags = "支付管理(Hystrix)")
    @HystrixCommand(fallbackMethod = "paymentInfoHystrixTimeoutHandler" ,commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "1500")
    })
    public String paymentInfoHystrixTimeout(@PathVariable("id") Integer id){
        return paymentFeignHystrixService.paymentInfoTimeout(id);
    }

    /**
     * 需要入参（和回参）类型一致
     * @param id
     * @return
     */
    private String paymentInfoHystrixTimeoutHandler(Integer id){
        return "消费者80，对方支付系统繁忙，请稍后重试";
    }




}
