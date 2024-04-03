package com.assignment.service;

import com.assignment.dto.ProductMetadataDTO;
import com.assignment.dto.ProductRequest;
import com.assignment.entity.ProductMetadata;
import com.assignment.exception.CustomException;
import com.assignment.repository.ProductMetadataRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductService {
    @Autowired
    private ProductMetadataRepository productRepo;
    public void saveProductMetadata(ProductRequest dataList) {

        List<ProductMetadata> saveList = new ArrayList<>();

        for (ProductMetadataDTO obj : dataList.getProductList()) {
            Optional<ProductMetadata> byProductId = productRepo.findByProductId(obj.getProductId());
            if (byProductId.isPresent()) {
                throw new CustomException("Product Already exists " + byProductId.get().getProductId());
            }else {
                ProductMetadata product = new ProductMetadata();
                product.setProductId(obj.getProductId());
                product.setBrand(obj.getBrand());
                product.setCategory(obj.getCategory());
                saveList.add(product);

            }
        }
            productRepo.saveAll(saveList);

    }

}
