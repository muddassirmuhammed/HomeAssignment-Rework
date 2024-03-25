package com.assignment.repository;

import com.assignment.entity.ShopperPersonalizedData;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ShopperPersonalizedDataRepository extends JpaRepository<ShopperPersonalizedData, Long> {
    Optional<ShopperPersonalizedData> findByShopperIdAndProductId(String shopperId, String productId);

    Page<ShopperPersonalizedData> findAll(Specification<ShopperPersonalizedData> spec, Pageable pageable);

    Page<ShopperPersonalizedData> findByProductId(String product, Pageable pageable);
}
