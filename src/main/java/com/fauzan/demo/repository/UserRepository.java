package com.fauzan.demo.repository;

import com.fauzan.demo.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserModel,Integer> {
    Optional<UserModel> findByUserName(String userName);
    //Name & Password
    Optional<UserModel> findByUserNameAndUserPassword(String userName, String password);

    @Query(value = "select * from user_table where user_name like %:userName%", nativeQuery = true)
    List<UserModel> searchUser(@Param("userName") String userName);

    @Transactional
    void deleteByUserName(String userName);
}
