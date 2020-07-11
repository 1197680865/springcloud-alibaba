package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entity.CommonResult;
import com.atguigu.springcloud.entity.Payment;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/order/payment")
public class PaymentContoller {

    private final String INVOKE_URL = "http://cloud-provider-payment";

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/zk")
    @ApiOperation(value = "zookeeper 信息",tags = "支付管理")
    public String paymentInfo(){
        return restTemplate.getForObject(INVOKE_URL + "/payment/zk",String.class);
    }


    @GetMapping("/getObj/{id}")
    @ApiOperation(value = "zookeeper 信息",tags = "支付管理")
    public CommonResult<Payment> getPaymentOById(@PathVariable("id") String id){
        //getForObject 直接返回Object对象
        return restTemplate.getForObject(INVOKE_URL + "/payment/get/"+id,CommonResult.class);
    }

    @GetMapping("/getEntity/{id}")
    @ApiOperation(value = "zookeeper 信息",tags = "支付管理")
    public CommonResult<Payment>  getPaymentEById(@PathVariable("id") String id){
        //getForObject 直接返回Object对象
        ResponseEntity<CommonResult> entity = restTemplate.getForEntity(INVOKE_URL + "/payment/get/" + id, CommonResult.class);
        if (entity.getStatusCode().is2xxSuccessful()){
            //成功
            return  entity.getBody();
        }else {
            //失败
            return new CommonResult<>(444,"操作失败");
        }
    }

}
