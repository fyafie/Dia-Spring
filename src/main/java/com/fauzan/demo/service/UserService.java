package com.fauzan.demo.service;

import com.fauzan.demo.dto.UserDTO;
import com.fauzan.demo.model.UserModel;
import com.fauzan.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;



    //mencari user dengan menggunakan id
    public UserDTO getUserById(int userId){
        return convertUser(userRepository.findById(userId).get());
    }
    private UserDTO convertUser(UserModel userModel){
        return new UserDTO(userModel.getUserName(),userModel.getUserEmail());
    }

    //mencari List dari user model
    public List<UserDTO> findAllUsers(){
        return userRepository.findAll().stream().map(this::convertUser)
                .collect(Collectors.toList());
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
    //add user with body
    public UserModel createUserWithBody(UserModel userModel){
        return userRepository.save(userModel);
    }
    //login
    public UserModel getUserByPasswordAndName(String userName, String password){
        Optional<UserModel> userOpt = userRepository.findByUserNameAndUserPassword(userName,password);
        if (userOpt.isEmpty()){
            return null;
        }
            return userOpt.get();
    }

    //search user
    public List<UserModel> searchUser(String userName){
        return userRepository.searchUser(userName);
    }
    //update with put
    public UserModel updateUser(int userId, UserModel userModel){
        Optional<UserModel> currentUserOpt = userRepository.findById(userId);
        if (currentUserOpt.isEmpty()){
            return null;
        }
        return userRepository.save(userModel);
    }
    //update with patch
    public UserModel updateUserName(int userId, String userName){
        Optional<UserModel> currentUserOpt =userRepository.
                findById(userId);

        if (currentUserOpt.isEmpty()){
            return null;
        }
        UserModel currentUser =currentUserOpt.get();
        currentUser.setUserName(userName);

        return userRepository.save(currentUser);
    }
    //delete user
    public boolean deleteUser(int userId){
        Optional<UserModel> currentUserOpt = userRepository.findById(userId);
        if (currentUserOpt.isEmpty()){
            return false;
        }
        userRepository.deleteById(userId);

        return true;
    }
    //delete user by name
    public boolean deleteUserByName(String userName){
    Optional<UserModel> currentUserOpt =userRepository.findByUserName(userName);
    if (currentUserOpt.isEmpty()){
        return false;
        }
    userRepository.deleteByUserName(userName);
    return true;
    }
}
