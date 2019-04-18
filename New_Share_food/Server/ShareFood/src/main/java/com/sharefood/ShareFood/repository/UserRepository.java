package com.sharefood.ShareFood.repository;

import com.sharefood.ShareFood.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Integer> {

    @Query(value = "SELECT * FROM tbl_users WHERE user_name = ?1 AND password = ?2", nativeQuery = true)
    User findUserByUserNameAndPass(String username, String pass);

    List<User> findAllByPermissionAndLocked(int permission, int lock);

    User findUserById(int id);

}
