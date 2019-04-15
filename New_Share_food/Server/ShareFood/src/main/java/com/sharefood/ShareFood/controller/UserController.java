package com.sharefood.ShareFood.controller;

import com.sharefood.ShareFood.model.Restaurant;
import com.sharefood.ShareFood.model.User;
import com.sharefood.ShareFood.response.base.Response;
import com.sharefood.ShareFood.response.extend.RestaurantsResponse;
import com.sharefood.ShareFood.response.extend.UserResponse;
import com.sharefood.ShareFood.service.base.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/v1")
@AllArgsConstructor
public class UserController extends AbstractController {

    private UserService userService;

    @RequestMapping(method = RequestMethod.GET, value = "/users")
    public ResponseEntity<Response> login(@RequestParam("username") String username, @RequestParam("pass") String pass){
        User user = userService.findUserByUserNameAndPass(username, pass);
        return responseData(new UserResponse(user));
    }

}
