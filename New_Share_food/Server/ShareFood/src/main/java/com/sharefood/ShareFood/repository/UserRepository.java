package com.sharefood.ShareFood.repository;

import com.sharefood.ShareFood.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {

    @Query(value = "SELECT * FROM tbl_users WHERE user_name = ?1 AND password = ?2", nativeQuery = true)
    User findUserByUserNameAndPass(String username, String pass);

}
