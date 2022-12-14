package com.company.Controller;

import com.company.Model.UserDTO;
import com.company.Model.UserEntity;
import com.company.Repository.DataRepository;
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
import javax.transaction.Transactional;
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

    @Autowired
    private DataRepository dataRepository;

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
    @GetMapping(value = "/first",produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<UserEntity> getA(@RequestParam String name,@RequestParam Long id){
        log.info("http://192.168.0.104:8090/Main/findByNameAndId");

        return dataRepository.findByNameAndId(name, id);
    }
    @GetMapping(value = "/second",produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<UserEntity> getB(@RequestParam String name,@RequestParam int salary){
        log.info("http://192.168.0.104:8090/Main/findByNameOrSalaryGreaterThan");

        return dataRepository.findByNameOrSalaryGreaterThan(name,salary);
    }
    @GetMapping(value = "/third",produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<UserEntity> getC(@RequestParam String name,@RequestParam int salary){
        log.info("http://192.168.0.104:8090/Main/findByNameOrSalaryGreaterThanOrderByIdDesc");

        return dataRepository.findByNameOrSalaryGreaterThanOrderByIdDesc(name,salary);
    }
    @GetMapping(value = "/four",produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Long getD(@RequestParam Long id){
        log.info("http://192.168.0.104:8090/Main/four");

        return dataRepository.getCount(id);
    }
    @GetMapping(value = "/five",produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<UserEntity>  getE(@RequestParam int salary){
        log.info("http://192.168.0.104:8090/Main/five");

        return dataRepository.getBySalary(salary);
    }
    @GetMapping(value = "/six",produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<UserEntity> getF(@RequestParam int salary){
        log.info("http://192.168.0.104:8090/Main/six");
        return dataRepository.getBySalaryNative(salary);
    }
    @Transactional
    @RequestMapping(value = "/seven",method = RequestMethod.PUT,produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String updateSalary(@RequestParam int salary, @RequestParam String name){
        dataRepository.changeSalaryByName(salary, name);
        return "Changed " + name + "'s Salary to -" + salary;
    }
//    @Transactional
//    @RequestMapping(value = "/eight",method = RequestMethod.PUT,produces = MediaType.APPLICATION_JSON_VALUE)
//    @ResponseBody
//    public void updateSalaryA(@RequestParam int salary, @RequestParam String name){
////        List<UserEntity> UserEntities = dataRepository.findByName(name);
////        for(UserEntity x : UserEntities){
////            x.setSalary(salary);
//
//      }
//
//
//    }
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
