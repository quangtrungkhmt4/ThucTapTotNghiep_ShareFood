package com.sharefood.ShareFood.controller;

import com.sharefood.ShareFood.model.Category;
import com.sharefood.ShareFood.response.base.Response;
import com.sharefood.ShareFood.response.extend.CategoriesResponse;
import com.sharefood.ShareFood.service.base.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/v1")
@AllArgsConstructor
public class CategoryController extends AbstractController {

    private CategoryService categoryService;

    @RequestMapping(method = RequestMethod.GET, value = "/categories")
    public ResponseEntity<Response> getAll(){
        List<Category> categories = categoryService.findAll();
        return responseData(new CategoriesResponse(categories));
    }
}
