package com.wtw.springcloud.alibaba.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FlowLimitController {

    @GetMapping("/testA")
    public String testA() {
        return "----------testA----------";
    }

    @GetMapping("testB")
    public String testB() {
        return "----------testB----------";
    }

    @GetMapping("/testD")
    public String testD() {
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        int a = 10 / 0;
        return "----------testD----------";
    }

    @RequestMapping("/testHotKey")
    @SentinelResource(value = "testHotKey", blockHandler = "deal_testHotKey")
    public String testHotKey(@RequestParam(value = "p1", required = false) String p1, @RequestParam(value = "p2", required = false) String p2) {
        return "testHotKey Success!";
    }

    public String deal_testHotKey(String p1, String p2, BlockException b1) {
        return "deal_testHotKey Success!";
    }
}
