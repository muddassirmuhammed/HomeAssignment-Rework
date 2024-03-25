package com.assignment.service;

import com.assignment.common.PagedResponse;
import com.assignment.dto.ProductMetadataDTO;
import com.assignment.dto.ShelfItemDTO;
import com.assignment.dto.ShopperPersonalizedDataDTO;
import com.assignment.entity.ProductMetadata;
import com.assignment.entity.ShopperPersonalizedData;
import com.assignment.exception.CustomException;
import com.assignment.repository.ProductMetadataRepository;
import com.assignment.repository.ShopperPersonalizedDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ShopperService {

    @Autowired
    private ShopperPersonalizedDataRepository shopperRepo;

    @Autowired
    private ProductMetadataRepository productMetadataRepository;

    public void saveShopperPersonalizedData(ShopperPersonalizedDataDTO data) {
        for (ShelfItemDTO item : data.getShelf()) {
            Optional<ProductMetadata> byProductId = productMetadataRepository.findByProductId(item.getProductId());
            if (!byProductId.isPresent())
                throw new CustomException("The product does not exist " + item.getProductId());
            Optional<ShopperPersonalizedData> byShopperId = shopperRepo.findByShopperIdAndProductId(data.getShopperId(), item.getProductId());
            if(byShopperId.isPresent())
                throw new CustomException("The shopper with ID " + data.getShopperId() + " and the product with ID " + item.getProductId() + "is already exists");
        }

        List<ShopperPersonalizedData> saveList = new ArrayList<>();
        for (ShelfItemDTO item : data.getShelf()) {
            ShopperPersonalizedData shopper = new ShopperPersonalizedData();
            shopper.setProductId(item.getProductId());
            shopper.setShopperId(data.getShopperId());
            shopper.setRelevancyScore(String.valueOf(item.getRelevancyScore()));
            saveList.add(shopper);
        }

        shopperRepo.saveAll(saveList);
    }

    public PagedResponse<ShopperPersonalizedData> findAll(Pageable pageable, Specification<ShopperPersonalizedData> spec) {
        Page<ShopperPersonalizedData> data = shopperRepo.findAll(spec, pageable);
        List<ShopperPersonalizedData> content = data.getContent();

        return new PagedResponse<>(content, data.getNumber(),
                data.getSize(), data.getTotalElements(),
                data.getTotalPages(), data.isLast());

    }

    public PagedResponse<ShopperPersonalizedData> findAllShoppers(Pageable pageable, String productId) {

        Page<ShopperPersonalizedData> data = shopperRepo.findByProductId(productId, pageable);

        if(!data.hasContent())
            return new PagedResponse<ShopperPersonalizedData>(Collections.emptyList(), data.getNumber(),
                    data.getSize(), data.getTotalElements(), data.getTotalPages(),
                    data.isLast());

        return new PagedResponse<ShopperPersonalizedData>(data.getContent(), data.getNumber(),
                data.getSize(), data.getTotalElements(), data.getTotalPages(),
                data.isLast());

    }
}
