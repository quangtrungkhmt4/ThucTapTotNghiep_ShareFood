package com.sharefood.ShareFood.repository;

import com.sharefood.ShareFood.model.Restaurant;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RestaurantRepository extends CrudRepository<Restaurant, Integer> {

    @Query(value="SELECT * FROM tbl_restaurants ORDER BY id_restaurant DESC LIMIT 5 OFFSET ?1", nativeQuery = true)
    List<Restaurant> findWithPage(int offset);

    @Query(value="SELECT count(*) as count FROM tbl_restaurants", nativeQuery = true)
    int countAll();

}
