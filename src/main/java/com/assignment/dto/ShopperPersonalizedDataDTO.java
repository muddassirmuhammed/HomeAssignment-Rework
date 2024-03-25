package com.assignment.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
@Builder
public class ShopperPersonalizedDataDTO {


    @NotEmpty(message = "Shopper ID must not be empty")
    @Pattern(regexp = "^[a-zA-Z0-9-]+$", message = "Invalid Shopper Id ")
    private String shopperId;

    private List<ShelfItemDTO> shelf;

}

