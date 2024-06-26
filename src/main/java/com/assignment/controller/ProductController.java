package com.assignment.controller;

import com.assignment.dto.ProductMetadataDTO;
import com.assignment.dto.ProductRequest;
import com.assignment.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/addProduct")
    public ResponseEntity<String> receiveProductMetadata(@Valid @RequestBody ProductRequest dataList) {

        productService.saveProductMetadata(dataList);

        return new ResponseEntity<>("Product data received successfully", HttpStatus.CREATED);

    }
}
