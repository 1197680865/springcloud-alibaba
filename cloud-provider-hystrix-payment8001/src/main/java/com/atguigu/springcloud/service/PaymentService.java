package com.atguigu.springcloud.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class PaymentService {

    /**
     * 正常访问
     * @param id
     * @return
     */
    public String payemntInfoOk(Integer id){
        return "线程：" +Thread.currentThread().getName() + " payemntInfo is Ok, id: " + id;
    }

    /**
     * 访问延迟
     * @param id
     * @return
     */
    public String payemntInfoTimeout(Integer id){
        int timeNum = 3;
        try {
            TimeUnit.SECONDS.sleep(timeNum);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String str = "线程：" +Thread.currentThread().getName() + " payemntInfo is Ok, id: " + id +" time(s):" + timeNum;
        return str;
    }

    /**
     * 访问延迟
     * @param id
     * @return
     */
    @HystrixCommand(fallbackMethod = "payemntInfoHystrixTimeoutHandler" ,commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "3000")
    })
    public String paymentInfoHystrixTimeout(Integer id){
        int timeNum = 5;
        try {
            TimeUnit.SECONDS.sleep(timeNum);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String str = "线程：" +Thread.currentThread().getName() + " payemntInfo is Ok, id: " + id +" time(s):" + timeNum;
        return str;
    }

    /**
     * 计算异常
     * @param id
     * @return
     */
    @HystrixCommand(fallbackMethod = "payemntInfoHystrixTimeoutHandler" ,commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "3000")
    })
    public String paymentInfoError(Integer id) {
        int  i = 10/0;
        String str = "线程：" +Thread.currentThread().getName() + " payemntInfo is Ok, id: " + id ;
        return  str;
    }

    /**
     * 服务降级后调用方法
     * @param id
     * @return
     */
    private String payemntInfoHystrixTimeoutHandler(Integer id){
        return  "线程：" +Thread.currentThread().getName() + " payemntInfoHystrixTimeoutHandler ,payemntInfo is timeout, id: " + id ;
    }


}
