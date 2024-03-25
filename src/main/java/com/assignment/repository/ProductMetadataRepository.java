package com.assignment.repository;

import com.assignment.entity.ProductMetadata;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductMetadataRepository extends JpaRepository<ProductMetadata, Long> {
    Optional<ProductMetadata> findByProductId(String productId);

}
