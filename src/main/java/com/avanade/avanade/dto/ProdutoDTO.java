package com.avanade.avanade.dto;

public record ProdutoDTO(   Long id,
                            String title,
                            String brand,
                            Float rating,
                            String description,
                            String category,
                            Integer stock,
                            Double price,
                            Double discountPercentage,
                            String thumbnail
) {
      

}
