package com.sharefood.ShareFood.controller;

import com.sharefood.ShareFood.model.Restaurant;
import com.sharefood.ShareFood.response.base.Response;
import com.sharefood.ShareFood.response.extend.CountResponse;
import com.sharefood.ShareFood.response.extend.RestaurantsResponse;
import com.sharefood.ShareFood.service.base.RestaurantService;
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
public class RestaurantController extends AbstractController {

    private RestaurantService restaurantService;

    @RequestMapping(method = RequestMethod.GET, value = "/restaurants")
    public ResponseEntity<Response> getAllWithPage(@RequestParam("page") int page){
        List<Restaurant> restaurants = restaurantService.findWithPage(page);
        return responseData(new RestaurantsResponse(restaurants));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/restaurants/count")
    public ResponseEntity<Response> getCount() {
        return responseData(new CountResponse(restaurantService.countAll()));
    }

}
