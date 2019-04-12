package com.cccc.demo.controller;


//import com.bbbb.demo.bean.User;
//import com.bbbb.demo.service.UserService;

import com.cccc.demo.model.User;
import com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider;
import org.apache.cxf.jaxrs.client.WebClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.apache.cxf.endpoint.Client;

import javax.websocket.server.PathParam;
import javax.ws.rs.core.MediaType;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping(value = "/rest")
public class UserController {
    // 注入配置的转json工具
    @Autowired
    private List<JacksonJaxbJsonProvider> jsonProvider;

    @GetMapping("/p1")
    @ResponseBody
    public String getString() {
        //调用webservice获取查询数据
        String s= WebClient
                .create("http://localhost:9001/services/userService/p1", jsonProvider)
                .accept(MediaType.APPLICATION_JSON).get(String.class);
        return s;
    }

    @PostMapping("/user")
    @ResponseBody
    public void addUser(int id,String name,int age) {

        //调用webservice获取查询数据
        WebClient
                .create("http://localhost:9001/services/userService/user?id="+id+"&name="+name+"&age="+age, jsonProvider)
                .accept(MediaType.APPLICATION_XML).get();

    }

    @PutMapping("/user")
    @ResponseBody
    public void updateUser(User user) {
        //调用webservice获取查询数据
        WebClient
                .create("http://localhost:9001/services/userService/user?user="+user, jsonProvider)
                .accept(MediaType.APPLICATION_XML).get(User.class);

    }



    @DeleteMapping("/user")
    @ResponseBody
    public Boolean deleteUser(int id) {
        //调用webservice获取查询数据
        Boolean b = WebClient
                .create("http://localhost:9001/services/userService/user?id="+id, jsonProvider)
                .accept(MediaType.TEXT_HTML).get(Boolean.class);
        return b;

    }

    @GetMapping("/user")
    @ResponseBody
    public User findByName(String userName) {
        //调用webservice获取查询数据
        User user = WebClient
                .create("http://localhost:9001/services/userService/user?userName="+userName, jsonProvider)
                .accept(MediaType.APPLICATION_XML).get(User.class);
        return user;
    }


    @GetMapping("/userAll")
    @ResponseBody
    public List<User> findAll() {
        //调用webservice获取查询数据
        List<User> user = WebClient
                .create("http://localhost:9001/services/userService/userAll", jsonProvider)
                .accept(MediaType.APPLICATION_JSON).get(List.class);
        return user;
    }







}
