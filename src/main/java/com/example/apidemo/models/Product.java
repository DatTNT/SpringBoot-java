package com.example.apidemo.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Calendar;

/**
 * Ojects create from this class are call POJO
 * Từ POJO sẽ trả về 1 dạng Json để cho client biết phía client có thể dùng moible, web, app để đọc
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name="tblProduct")
public class Product {
    //Tiem khoa chinhs. tại sao strategy để cho nó kiểu tự sinh
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)//auto-increment
    private Long id;
    //
    /**
     * validate =constraint equal unique. Unique make ban value which duplicate
     */
    @Column(nullable = false, unique = true, length = 300)
    private String productName;
    private int years;
    private Double price;
    private String url;

    //calculated field = transient, not exest in Mysql
    @Transient
    private int age;//age is calculated from "year"
    public int getAge(){
        return Calendar.getInstance().get(Calendar.YEAR) - years;
    }

    public Product(String productName, int years, Double price, String url) {
        this.productName = productName;
        this.years = years;
        this.price = price;
        this.url = url;
    }
}
