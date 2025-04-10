package com.todocodeacademy.sales_service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class CartDetailsDTO {
    private Long id;
    private Double total;
    private List<ProductDTO> productList;
    private LocalDate date;
}
