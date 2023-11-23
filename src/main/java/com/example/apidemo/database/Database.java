package com.example.apidemo.database;

import com.example.apidemo.models.Product;
import com.example.apidemo.repositories.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration chứa bean method. Khi chạy project thì class nào có Configuration thì được chạy
 * Dùng bean để chúng ta khỏi tạo database, biến môi trường
 */
@Configuration
public class Database {
    /**
     * InitData để tạo ra data ban đầu của chùng ta
     * function này gọi là code first
     * Nếu bảng ghi chưa có gì thì tự động insert vài bảng ghi vào database
     * Hoặc chưa có bảng ghi thì nó tự động tạo 1 2 bảng ghi
     * @param prodctProductRepository
     * @return
     */
    //logger
    private static final Logger logger = LoggerFactory.getLogger(Database.class);
    @Bean
    CommandLineRunner initDatabase(ProductRepository prodctProductRepository){


        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
//                Product productA = new Product("macOSaa",202, 2300.0,"");
//                Product productB = new Product("macOSAA1",202, 2300.0,"");
//                logger.info("insert data: " + prodctProductRepository.save(productA));
//                logger.info("insert data: " + prodctProductRepository.save(productB));
            }
        };
    }
}
