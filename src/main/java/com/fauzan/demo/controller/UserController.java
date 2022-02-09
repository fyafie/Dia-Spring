package com.fauzan.demo.controller;

import com.fauzan.demo.dto.UserDTO;
import com.fauzan.demo.model.UserModel;
import com.fauzan.demo.response.DataResponse;
import com.fauzan.demo.response.HandlerResponse;
import com.fauzan.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.HttpMediaTypeException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/")
public class UserController {
    @Autowired
    private UserService userService;


    //helloWorld
    @GetMapping("/helloWorld")
    public String helloWorld(/*request*/){
        //response
        return "Hello World2";
    }
    //show user by Id
    @GetMapping("/user/{userId}")
    public UserDTO getUser(@PathVariable("userId") int userId){
        return userService.getUserById(userId);
    }
    //show all user
    @GetMapping("/user/all")
    public List<UserDTO> getAllUsers(){
        return userService.findAllUsers();
    }
    //add user
    @PostMapping("/user")
    public UserModel createUser(@RequestParam("userName") String userName,
                                @RequestParam("userPassword") String userPassword,
                                @RequestParam("userEmail") String userEmail,
                                @RequestParam("userPhone") String userPhone,
                                @RequestParam("userAddress") String userAddress,
                                @RequestParam("userResume") String userResume){
        return userService.createUser(userName, userPassword, userEmail,userPhone,userAddress,userResume);
    }
    //add user with body
    @PostMapping("/user/body")
    public UserModel createUserWithBody(@RequestBody UserModel userModel){
        return userService.createUserWithBody(userModel);
    }
    //login
    @GetMapping("/user/login")
    public UserModel getUserByNameAndPassword(@RequestParam("userName") String userName,
                                              @RequestParam("userPassword") String password){
        UserModel userByName = userService.getUserByPasswordAndName(userName, password);
        if (userByName != null){
            return userByName;
        }else{
            return new UserModel();
        }
    }
    //search user
    @GetMapping("user/search")
    public List<UserModel>searchUser(@RequestParam("userName")String userName){
        return userService.searchUser(userName);
    }
    //delete user
    @DeleteMapping("/user/{userId}")
    public boolean deleteUser(@PathVariable("userId") int userId){
        return userService.deleteUser(userId);
    }
    //delete user by name
    @DeleteMapping("/user/name/{userName}")
    public boolean deleteUser(@PathVariable("userName") String userName) {
        return userService.deleteUserByName(userName);
    }

    @GetMapping("/user/response")
    public void getUserWithResponse(HttpServletResponse request, HttpServletResponse response,
                                    @RequestParam("userId") int userId){
        UserDTO userDTO = userService.getUserById(userId);

        DataResponse<UserDTO> data = new DataResponse<>();
        data.setData(userDTO);
        HandlerResponse.responseSuccessWithData(response, data);

//        HandlerResponse.responseBadRequest(response, "001", "Job id not found");
//        HandlerResponse.responseInternalServerError(response, "Something wrong");
//        HandlerResponse.responseSuccessOK(response,"Success get job");
//        HandlerResponse.responseUnauthorized(response, "User not authorized");
    }
}
