package com.example.apidemo.controller;

import com.example.apidemo.models.Product;
import com.example.apidemo.models.ResponseObject;
import com.example.apidemo.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/v1/Products")
public class ProductController {
    //DI = Dependence Injection
    @Autowired
    private ProductRepository resRepository;

    @GetMapping("/getAllProducts")
    List<Product> getAllProducts() {
        return resRepository.findAll();//where is data?
    }
    //You must save this to Database, Now we have H2 DB = In-memory Database
    //In-memory Database dùng để test

    //Optional có thể trả về giá trị null
    @GetMapping("/{id}")
    //Let's return an object with: data, message, status
    //Vì khi chạy trường hợp không có id thì khách hàng thể hiện lỗi ngay trên postman
    ResponseEntity<ResponseObject> findByid(@PathVariable Long id) {
        Optional<Product> foundProduct = resRepository.findById(id);
        if (foundProduct.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "Query product successfully", foundProduct)
            );
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("false", "Cann't find product with id = " + id, "")
            );
        }
    }

    //insert new Product with POST method
    //RequestBody la kieu du lieu chuyen vao goi la tham so chuyen vao duoi dang body. Co thể sử dụng dưới dạng thẻ form

    /**
     * Trường hợp 2 thăng product có tên chùng nhau thê là cook
     * Vậy làm sao để validate chúng. Có 2 cách
     * -Contrain trong database
     * -Viết hàm check. Trong jpa gọi thêm 1 hàm để ta check
     */
    @PostMapping("/insert")
    ResponseEntity<ResponseObject> insertProduct(@RequestBody Product newProduct) {
        //Problems: 2 products must not have the same name
        List<Product> foundProducts = resRepository.findByProductName(newProduct.getProductName().trim());
        if (foundProducts.size() > 0) {
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
                    new ResponseObject("failed", "Product name already taken", "")
            );
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Insert Product successfully", resRepository.save(newProduct))
        );
    }

    /**
     * update, insert = update if foundm ortherwise insert
     */
    @PutMapping("/{id}")
    ResponseEntity<ResponseObject> updateProduct(@RequestBody Product newProduct, @PathVariable Long id) {
        Product updateProduct = resRepository.findById(id)
                .map(product -> {
                    product.setProductName(newProduct.getProductName());
                    product.setYears(newProduct.getYears());
                    product.setPrice(newProduct.getPrice());
                    return resRepository.save(product);
                }).orElseGet(() -> {
                    newProduct.setId(id);
                    return resRepository.save(newProduct);
                });
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Update Product successfully", updateProduct)
        );
    }

    //Delete a Product => DELETE method
    @DeleteMapping("{id}")
    ResponseEntity<ResponseObject> deletePRoduct(@PathVariable Long id) {
        boolean exists = resRepository.existsById(id);
        if (exists) {
            resRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "Delete Product successfully", "")
            );
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseObject("failed", "Can not find Product successfully", "")
        );
    }

}
