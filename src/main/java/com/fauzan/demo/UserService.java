package com.fauzan.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;


    //mencari user dengan menggunakan id
    public UserModel getUserById(int userId){
        return userRepository.findById(userId).get();
    }
    //mencari List dari user model
    public List<UserModel> findAllUsers(){
        return userRepository.findAll();
    }
    //membuat user baru
    public UserModel createUser(String userName,
                                String userPassword,
                                String userEmail,
                                String userPhone,
                                String userAddress,
                                String userResume){

        //validasi model
        UserModel newUser = new UserModel();
        newUser.setUserName(userName);
        newUser.setUserPassword(userPassword);
        newUser.setUserEmail(userEmail);
        newUser.setUserPhone(userPhone);
        newUser.setUserAddress(userAddress);
        newUser.setUserResume(userResume);

        return userRepository.save(newUser);
    }
}
