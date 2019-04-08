package com.sharefood.ShareFood.controller;

import com.sharefood.ShareFood.model.Price;
import com.sharefood.ShareFood.response.base.Response;
import com.sharefood.ShareFood.response.extend.CountResponse;
import com.sharefood.ShareFood.response.extend.PricesResponse;
import com.sharefood.ShareFood.service.base.PriceService;
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
public class PriceController extends AbstractController{

    private PriceService priceService;

    @RequestMapping(method = RequestMethod.GET, value = "/prices")
    public ResponseEntity<Response> getAllWithPage(@RequestParam("page") int page){
        List<Price> prices = priceService.findWithPage(page);
        return responseData(new PricesResponse(prices));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/prices/count")
    public ResponseEntity<Response> getCount(){
        return responseData(new CountResponse(priceService.countAll()));
    }

}
