package com.sharefood.ShareFood.service.base;

import com.sharefood.ShareFood.model.User;

public interface UserService extends Service<User> {
    User findUserByUserNameAndPass(String username, String pass);
}
