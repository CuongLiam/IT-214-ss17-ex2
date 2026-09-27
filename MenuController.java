package com.example.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MenuController {

    @Autowired
    private RestaurantService restaurantService;

    @GetMapping("/restaurants/{restaurantId}/menu")
    public List<RestaurantService.MenuItem> getMenu(@PathVariable Long restaurantId) {
        return restaurantService.getMenuByRestaurantId(restaurantId);
    }
}
