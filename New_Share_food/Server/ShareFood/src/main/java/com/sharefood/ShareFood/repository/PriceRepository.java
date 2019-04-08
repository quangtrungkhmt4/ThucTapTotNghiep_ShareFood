package com.sharefood.ShareFood.repository;

import com.sharefood.ShareFood.model.Price;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PriceRepository extends CrudRepository<Price, Integer> {

    @Query(value="SELECT * FROM tbl_prices ORDER BY id_price DESC LIMIT 5 OFFSET ?1", nativeQuery = true)
    List<Price> findWithPage(int offset);

    @Query(value="SELECT count(*) as count FROM tbl_prices", nativeQuery = true)
    int countAll();
}
