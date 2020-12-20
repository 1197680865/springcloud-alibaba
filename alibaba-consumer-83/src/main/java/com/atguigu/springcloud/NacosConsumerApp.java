package com.atguigu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * nacos 服务消费者
 * https://github.com/alibaba/spring-cloud-alibaba/wiki/Nacos-discovery
 * 1.使用nacos作为注册中心
 * 2.负载均衡 ，默认使用ribbon @LoadBalanced
 * 3.配置中心，
 *     3.1 @RefreshScope 配置动态刷新
 *     3.2
 *      namespace 用于区分空间，如开发、测试、预发布、生产
 *      group：分组，可以把不同的微服务划分到同一个组
 *      cluster：通过微服务的集群分组，比如位于广州的集群和位于深圳的集群，以不同的cluster区分，保证同一cluster的服务尽量相互调用
 *     3.3 重要**  集群和持久化
 *      架构： 1个VIP（Nginx）+ 3个nacos + 1个mysql
 *      https://nacos.io/zh-cn/docs/cluster-mode-quick-start.html
 *      3.3.1.mysql 持久化配置
 *          conf文件夹下：新建数据库UTF8字符集，执行sql脚本（nacos-mysql.sql），
 *          修改application.properties追加以下配置（）
 *          #*************** mysql持久化配置 ***************#
 *          spring.datasource.platform=mysql
 *          db.num=1
 *          db.url.0=jdbc:mysql://127.0.0.1:3306/nacos_config?characterEncoding=utf8&connectTimeout=10000&socketTimeout=30000&autoReconnect=true&useUnicode=true&useSSL=false&serverTimezone=UTC
 *          db.user=root
 *          db.password=123456
 *      3.3.2 集群
 *          修改 cluster.conf： 不要使用127.0.0.1
 *          127.0.0.1:8848
 *          127.0.0.1:8849
 *          127.0.0.1:8850
 *          修改 startup脚本，略见B站视频P109
 *
 */
@SpringBootApplication
@EnableDiscoveryClient
public class NacosConsumerApp {
    public static void main(String[] args) {
        SpringApplication.run(NacosConsumerApp.class,args);
    }
}
