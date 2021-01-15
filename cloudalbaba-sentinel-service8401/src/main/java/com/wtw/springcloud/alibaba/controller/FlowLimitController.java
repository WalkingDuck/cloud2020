package com.wtw.springcloud.alibaba.controller;

import org.springframework.web.bind.annotation.GetMapping;
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
}
