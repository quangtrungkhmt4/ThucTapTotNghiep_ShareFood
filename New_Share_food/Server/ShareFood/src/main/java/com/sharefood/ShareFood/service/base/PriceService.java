package com.sharefood.ShareFood.service.base;

import com.sharefood.ShareFood.model.Price;

import java.util.List;

public interface PriceService extends Service<Price> {
    List<Price> findWithPage(int page);

    int countAll();
}
