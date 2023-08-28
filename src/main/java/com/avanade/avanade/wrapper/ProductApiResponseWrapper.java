package com.avanade.avanade.wrapper;

import com.avanade.avanade.entity.Produto;
import java.util.List;


public class ProductApiResponseWrapper {
    private List<Produto> products;


    
    public List<Produto> getProducts() {
        return products;
    }

    public void setProducts(List<Produto> products) {
        this.products = products;
    }
}
