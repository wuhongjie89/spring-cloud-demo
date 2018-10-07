package com.wu.springdemo.zookeeper.demo.client.controller;

import com.wu.springdemo.zookeeper.demo.client.client.ZookeeperDemoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping(value = "api/demo")
public class DemoController {
    private static final UUID INSTANCE_UUID = UUID.randomUUID();
    private final ZookeeperDemoService zookeeperDemoService;

    public DemoController(final ZookeeperDemoService zookeeperDemoService) {
        this.zookeeperDemoService = zookeeperDemoService;
    }

    @GetMapping(value = "/remote-instance")
    public Object remoteInstance() {
        return this.zookeeperDemoService.instance();
    }

    @GetMapping(value = "/instance")
    public Object instance() {
        return INSTANCE_UUID.toString();
    }
}
