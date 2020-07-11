package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entity.CommonResult;
import com.atguigu.springcloud.entity.Payment;
import com.atguigu.springcloud.service.PaymentService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    private final static Logger log = LoggerFactory.getLogger(PaymentController.class);

    @Value("${server.port}")
    private String serverPort;


    @Autowired
    private PaymentService paymentService;

    @GetMapping("/zk")
    public String paymentZk(){
        return "springcloud with zookeeper: " +serverPort +
                "\t" + UUID.randomUUID().toString();
    }



    @PostMapping("create")
    @ApiOperation(value = "新增订单",tags = "订单模块")
    //必须要@RequestBody注解，否则其他模块在调用次接口时，获取不到值
    public CommonResult create(@RequestBody Payment payment)
    {
        int result = paymentService.create(payment);
        log.info("*****插入结果，{}" ,result);
        if (result>0)
        {
            return new CommonResult(200,"插入成功",result);

        }else {
            return new CommonResult(444,"插入失败",result);
        }
    }

    @GetMapping("get/{id}")
    @ApiOperation(value = "查询订单",tags = "订单模块")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id)
    {
        Payment payment = paymentService.getPaymentById(id);
        log.info("*****查询数据，{}" ,payment);
        if (payment != null)
        {
            return new CommonResult<>(200,"查询成功, " + "springcloud with zookeeper: " +serverPort ,payment);

        }else {
            return new CommonResult<>(444,"查询失败,无对应记录："+id  +" ,springcloud with zookeeper: " +serverPort,null);
        }
    }
    @GetMapping("test/timeout")
    @ApiOperation(value = "测试超时（3秒）",tags = "测试超时")
    public String testTimeout()
    {
       try {
           TimeUnit.SECONDS.sleep(3);
       }catch (Exception e)
       {
           e.printStackTrace();
       }
       return serverPort;
    }

}
