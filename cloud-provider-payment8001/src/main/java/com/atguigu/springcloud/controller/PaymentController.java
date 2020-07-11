package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entity.CommonResult;
import com.atguigu.springcloud.entity.Payment;
import com.atguigu.springcloud.service.PaymentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("payment")
//Api注解，描述信息 可通过tag进行分类
@Api(value = "PaymentController")
public class PaymentController {
    @Autowired
    private PaymentService paymentService;

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
            return new CommonResult<>(200,"查询成功",payment);

        }else {
            return new CommonResult<>(444,"查询失败,无对应记录："+id,null);
        }
    }

}
