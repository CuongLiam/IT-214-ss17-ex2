package com.example.cache;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class RestaurantService {

    // Áp dụng Lazy Loading Cache: 
    // - value: Tên không gian cache
    // - key: Tham số id để phân biệt cache giữa các nhà hàng
    @Cacheable(value = "restaurantMenu", key = "#id")
    public List<MenuItem> getMenuByRestaurantId(Long id) {
        System.out.println("\n[DB QUERY] -> Đang truy vấn Database cho Restaurant ID: " + id + "...");
        
        try {
            // Giả lập độ trễ truy vấn DB phức tạp mất 3 giây
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("[DB QUERY] -> Truy vấn xong! Đưa dữ liệu vào Cache.\n");

        // Giả lập dữ liệu trả về từ DB
        if (id == 101L) {
            return Arrays.asList(
                    new MenuItem(1L, id, "Phở Bò", 55000),
                    new MenuItem(2L, id, "Bún Chả", 60000)
            );
        }
        
        return Arrays.asList(new MenuItem(3L, id, "Món mặc định", 10000));
    }

    public static class MenuItem {
        private Long id;
        private Long restaurantId;
        private String dishName;
        private double price;

        public MenuItem() {}

        public MenuItem(Long id, Long restaurantId, String dishName, double price) {
            this.id = id;
            this.restaurantId = restaurantId;
            this.dishName = dishName;
            this.price = price;
        }

        // Getters and Setters
        public Long getId() { return id; }
        public void setId(Long id) { this.id = id; }
        public Long getRestaurantId() { return restaurantId; }
        public void setRestaurantId(Long restaurantId) { this.restaurantId = restaurantId; }
        public String getDishName() { return dishName; }
        public void setDishName(String dishName) { this.dishName = dishName; }
        public double getPrice() { return price; }
        public void setPrice(double price) { this.price = price; }
    }
}
