package com.assignment.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor

@Component
public class ProductRequest {

    @NonNull
    public List<ProductMetadataDTO> productList;
    public ProductRequest() {
        this.productList = new ArrayList<>();
    }
}
