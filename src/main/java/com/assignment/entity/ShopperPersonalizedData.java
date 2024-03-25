package com.assignment.entity;

import com.assignment.dto.ProductMetadataDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor


@Table(name = "shopper_data")
public class ShopperPersonalizedData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull(message = "Shopper ID must not be null")
    private String shopperId;

    @Column(name = "productId")
    private String productId;

    @Column(name = "relevancyScore")
    private String relevancyScore;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "productId", referencedColumnName = "productId", insertable=false, updatable=false)
    private ProductMetadata productDetails;


}


