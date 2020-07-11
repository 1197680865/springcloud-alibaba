package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entity.CommonResult;
import com.atguigu.springcloud.entity.Payment;
import com.atguigu.springcloud.service.PaymentFeignService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaymentController {

    @Autowired
    private PaymentFeignService paymentFeignService;

    @GetMapping("/consumer/payment/get/{id}")
    @ApiOperation(value = "支付查询",tags = "支付管理")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id){
        return  paymentFeignService.getPaymentById(id);
    }

    @GetMapping("/consumer/payment/test/timeout")
    @ApiOperation(value = "超时测试（3秒）",tags = "支付管理")
    public String testTimeout(){
        return paymentFeignService.testTimeout();
    }
}
