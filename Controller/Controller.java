package com.company.Controller;

import com.company.Model.UserDTO;
import com.company.Model.UserEntity;
import com.company.Services.A_ServiceImpl;
import com.company.Services.Service_Impl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.List;

@RequestMapping("/Main")
public class Controller {

    private static final Logger log = LoggerFactory.getLogger(Controller.class);

    @Autowired
    private A_ServiceImpl a_service;

    @Autowired
    private Service_Impl B;

    @Autowired
    @Qualifier("Service_C")
    private Service_Impl C;

    @Autowired
    private ApplicationContext applicationContext;

    @PostConstruct
    public void init(){
        log.info("PostConstruct call");
    }
    @PreDestroy
    public void destroy(){
        log.info("PreDestroy call");
    }
    @RequestMapping(value = "/shutdown",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public void stop(){
        ((ConfigurableApplicationContext)applicationContext).close();
    }

    @GetMapping(value = "/server",produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String server(){
        log.info("http://192.168.0.104:8090/Main/server");
        log.info("B: " + B);
        log.info("C: " + C);
        return "\n" + B.getOne() + "\n" + C.getOne();
    }

    @PostMapping(value = "/set",produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String setData(@RequestBody UserDTO userDTO){
        a_service.setData(userDTO);
        log.info("http://192.168.0.104:8090/Main/set");
        return "Was added: " + userDTO.getName();
    }
    @GetMapping(value = "/get",produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<UserEntity> getData(){
        log.info("http://192.168.0.104:8090/Main/get");
        return a_service.getData();
    }

}
