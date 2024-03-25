package com.assignment.controller;

import com.assignment.common.PagedResponse;
import com.assignment.entity.ShopperPersonalizedData;
import com.assignment.service.ShopperService;
import com.assignment.util.ShopperPersonalizedDataSpecs;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/external")
public class ExternalController {

    @Autowired
    private ShopperService shopperService;

    @GetMapping("/getProducts")
    public ResponseEntity<?> getProducts(
            @RequestParam(required = true) String shopperId,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String brand,
            @RequestParam(defaultValue = "1", required = false) int page,
            @Valid @RequestParam(defaultValue = "10", required = false)@Max(100) int size,
            @RequestParam(defaultValue = "id", required = false) String sort,
            @RequestParam(defaultValue = "desc", required = false) String order

    ) {
        Specification<ShopperPersonalizedData> spec =
                ShopperPersonalizedDataSpecs.filterProducts(shopperId, category, brand);
        Pageable pageable = PageRequest.of(page - 1, size, order.trim().equals("desc") ? Sort.Direction.DESC : Sort.Direction.ASC, sort);

        return ResponseEntity.ok(shopperService.findAll(pageable, spec));
    }

    @GetMapping("/getShopper")
    public PagedResponse<ShopperPersonalizedData> getShoppers(@RequestParam String productId,
                                                              @RequestParam(defaultValue = "1", required = false) int page,
                                                              @Valid  @RequestParam(defaultValue = "10", required = false)@Max(1000) int size,
                                                              @RequestParam(defaultValue = "id", required = false) String sort,
                                                              @RequestParam(defaultValue = "desc", required = false) String order) {

        Pageable pageable = (size != 0
                ? PageRequest.of(page - 1, size, order.trim().equals("desc") ? Sort.Direction.DESC : Sort.Direction.ASC,
                sort)
                : Pageable.unpaged());

        return shopperService.findAllShoppers(pageable, productId);
    }
}
