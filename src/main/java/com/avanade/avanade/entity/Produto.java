package com.avanade.avanade.entity;

import com.avanade.avanade.dto.ProdutoDTO;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "Produtos")
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private String brand;
    private Float rating;
    private String description;
    private String category;
    private Integer stock;
    private Double price;
    private Double discountPercentage;
    private String thumbnail;
    

    public Produto(ProdutoDTO dto){
        this.id = dto.id();
        this.title = dto.title();
        this.brand = dto.brand();
        this.rating = dto.rating();
        this.description = dto.description();
        this.category = dto.category();
        this.stock = dto.stock();
        this.price = dto.price();
        this.discountPercentage = dto.discountPercentage();
        this.thumbnail = dto.thumbnail();
        
    }

    public ProdutoDTO dto(){
        return new ProdutoDTO(this.id,this.title,this.brand,this.rating,this.description,this.category,this.stock,this.price,this.discountPercentage,this.thumbnail);
    }




}
