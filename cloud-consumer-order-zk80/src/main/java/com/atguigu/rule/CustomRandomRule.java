package com.atguigu.rule;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AbstractLoadBalancerRule;
import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.Server;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *  自定义Ribbon负载均衡规则
 *  当请求次数小于INIT_REQ_SWITCH_NUM时使用轮询，大于时使用随机1
 */
public class CustomRandomRule extends AbstractLoadBalancerRule {

    private final static Logger log = LoggerFactory.getLogger(CustomRandomRule.class);
    public final int INIT_REQ_SWITCH_NUM = 10;
    public final AtomicInteger reqAtomicInteger = new AtomicInteger(0);

    /** *
     * 利用CAS自旋锁获取第几次请求
     * @return
     */
    private int getReqCountAndIncrement(){
        int current;
        int next;
        do {
            current = reqAtomicInteger.get();
            next = current >= Integer.MAX_VALUE ? 0:current+1;
        }while (!this.reqAtomicInteger.compareAndSet(current,next));
        log.info("--- the {} request. ---",next);
        return next;
    }

    /**
     * 得到随机数 [0,serverCount)
     * @param serverCount
     * @return
     */
    private int chooseRandomInt(int serverCount) {
        return ThreadLocalRandom.current().nextInt(serverCount);
    }

    @Override
    public void initWithNiwsConfig(IClientConfig iClientConfig) {

    }

    @Override
    public Server choose(Object key) {
        ILoadBalancer loadBalancer = getLoadBalancer();
        if (null == loadBalancer){
            //未开启负载均衡
            return null;
        }

        List<Server> reachableServers = loadBalancer.getReachableServers();
        // List<Server> allServers = loadBalancer.getAllServers();
        int reqCountAndIncrement = getReqCountAndIncrement();
        if (reqCountAndIncrement >= INIT_REQ_SWITCH_NUM){
            //轮询
            int serverIndex = reqCountAndIncrement % reachableServers.size();
            return reachableServers.get(serverIndex);
        }else {
            //随机
            return  reachableServers.get(chooseRandomInt(reachableServers.size()));
        }
    }

}
