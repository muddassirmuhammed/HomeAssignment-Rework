package com.assignment.entity;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "product_Metadata")
public class ProductMetadata {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "productId")
    @NotBlank(message = "Product ID must not be blank")
    @Valid
    private String productId;

    @Column(name = "category")
    private String category;

    @Column(name = "brand")
    private String brand;


}

