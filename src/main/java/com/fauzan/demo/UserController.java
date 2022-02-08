package com.fauzan.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/")
public class UserController {
    @Autowired
    private UserService userService;



    @GetMapping("/helloWorld")
    public String helloWorld(/*request*/){
        //response
        return "Hello World2";
    }

    @GetMapping("/user/{userId}")
    public UserModel getUser(@PathVariable("userId") int userId){
        return userService.getUserById(userId);
    }

    @GetMapping("/user/all")
    public List<UserModel> getAllUsers(){
        return userService.findAllUsers();
    }

    @PostMapping("/user")
    public UserModel createUser(@RequestParam("userName") String userName,
                                @RequestParam("userPassword") String userPassword,
                                @RequestParam("userEmail") String userEmail,
                                @RequestParam("userPhone") String userPhone,
                                @RequestParam("userAddress") String userAddress,
                                @RequestParam("userResume") String userResume){
        return userService.createUser(userName, userPassword, userEmail,userPhone,userAddress,userResume);
    }


}
