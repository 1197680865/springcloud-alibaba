package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.service.PaymentService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaymentController {
    private final static Logger log = LoggerFactory.getLogger(PaymentController.class);

    @Value("${server.port}")
    private String serverPort;

    @Autowired
    private PaymentService paymentService;


    /**
     * * 注：当20000个线程请求payemntInfoTimeout时，系统资源被占用，payemntInfoOk也会出现访问慢的情况
     * @param id
     * @return
     */
    @GetMapping("/payment/no_hystrix/ok/{id}")
    @ApiOperation(value = "正常访问(无hystrix)",tags = "hystrix 测试")
    public String paymentInfoNoHystrixOk(@PathVariable("id") Integer id){
        log.info("正常访问，端口：" +serverPort);
        return paymentService.payemntInfoOk(id);
    }


    @GetMapping("/payment/no_hystrix/timeout/{id}")
    @ApiOperation(value = "超时访问(无hystrix)",tags = "hystrix 测试")
    public String paymentInfoNoHystrixTimeout(@PathVariable("id") Integer id){
        log.info("超时访问，端口：" +serverPort);
        return paymentService.payemntInfoTimeout(id);
    }


    @GetMapping("/payment/hystrix/ok/{id}")
    @ApiOperation(value = "正常访问(有hystrix)",tags = "hystrix 测试")
    public String paymentInfoOk(@PathVariable("id") Integer id){
        log.info("正常访问，端口：" +serverPort);
        return paymentService.payemntInfoOk(id);
    }


    @GetMapping("/payment/hystrix/timeout/{id}")
    @ApiOperation(value = "超时访问(有hystrix)",tags = "hystrix 测试")
    public String paymentInfoTimeout(@PathVariable("id") Integer id){
        log.info("超时访问，端口：" +serverPort);
        return paymentService.paymentInfoHystrixTimeout(id);
    }

    @GetMapping("/payment/hystrix/error/{id}")
    @ApiOperation(value = "内部异常(有hystrix)",tags = "hystrix 测试")
    public String paymentInfoErro(@PathVariable("id") Integer id){
        log.info("超时访问，端口：" +serverPort);
        return paymentService.paymentInfoError(id);
    }
}
