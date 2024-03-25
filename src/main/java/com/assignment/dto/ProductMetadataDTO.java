package com.assignment.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductMetadataDTO {

    @NonNull
    @Pattern(regexp = "^[a-zA-Z0-9-]+$", message = "Invalid Product Id ")
    @Valid
    private String productId;

    @Pattern(regexp = "^[a-zA-Z ]+$", message = "Invalid Category ")
    private String category;

    @Pattern(regexp = "^[a-zA-Z ]+$", message = "Invalid Brand ")
    private String brand;




}
