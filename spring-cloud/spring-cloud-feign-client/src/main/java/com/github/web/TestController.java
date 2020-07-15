package com.github.web;


import com.github.client.LocalHelloClient;
import com.github.entity.Request;
import com.github.entity.Response;
import com.github.service.HelloClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author hangs.zhang
 * @date 2018/12/5
 * *****************
 * function:
 */
@Slf4j
@RestController
public class TestController {

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @Autowired
    private RestTemplate restTemplateAuto;

    @Autowired
    private HelloClient helloClient;

    @Autowired
    private LocalHelloClient localHelloClient;

    /**
     * 声明式REST客户端，伪RPC的方式
     * @return
     */
    @GetMapping("/nodeTwo")
    public String sleuth1() {
        String nodeOne = localHelloClient.nodeOne();
        log.info("result:{}", nodeOne);
        return "nodeTwo";
    }

    /**
     * RestTemplate的使用
     * Ribbon 负载均衡器,属于软负载，整个负载均衡的过程在客户端完成
     */
    @GetMapping("/restTemplate")
    public void restTemplate() {
        // 第一种方式
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject("http://localhost:8080/hello", String.class);
        log.info("result:{}", result);
        // 第二种方式
        ServiceInstance serviceInstance = loadBalancerClient.choose("PROVIDER-SERVICE");
        String host = serviceInstance.getHost();
        int port = serviceInstance.getPort();
        String url = String.format("http://%s:%s/hello", host, port);
        String result2 = restTemplate.getForObject(url, String.class);
        log.info("result2:{}", result2);
        // 第三种方式
        String result3 = restTemplateAuto.getForObject("http://PROVIDER-SERVICE/hello", String.class);
        log.info("result3:{}", result3);
    }

    @GetMapping("/client")
    public void client() {
        String result = helloClient.say();
        log.info("result1:{}", result);
        Request request = new Request();
        Response response = helloClient.deal(request);
        log.info("result2:{}", response);
    }

}
