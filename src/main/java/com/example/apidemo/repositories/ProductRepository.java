package com.example.apidemo.repositories;

import com.example.apidemo.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Long> {
    /**
     * Nơi để chứa các hàm để lấy data về(data này ở đâu? text, trong database)
     * JpaRepository<"Kiểu thực thể",Trường khóa chính>
     * JpaRepository<Entity's type,primary key's type>
     */
    List<Product> findByProductName(String productName);
}
