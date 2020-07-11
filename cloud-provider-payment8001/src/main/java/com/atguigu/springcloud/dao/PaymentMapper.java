package com.atguigu.springcloud.dao;

import com.atguigu.springcloud.entity.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Mapper //推荐使用mapper
@Component
public interface PaymentMapper {
    int create(Payment payment);
    Payment getPaymentById(@Param("id") Long id);

}
