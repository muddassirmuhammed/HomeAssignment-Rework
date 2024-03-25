package com.assignment.controller;

import com.assignment.dto.ShopperPersonalizedDataDTO;
import com.assignment.service.ShopperService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/shopper")
public class ShopperController {

    @Autowired
    private ShopperService shopperService;

    @PostMapping("/addShopperData")
    public ResponseEntity<String> receivePersonalizedData(@Valid @RequestBody ShopperPersonalizedDataDTO data) {
        shopperService.saveShopperPersonalizedData(data);

        return new ResponseEntity<String>("Shopper data received successfully", HttpStatus.CREATED);
    }
}
