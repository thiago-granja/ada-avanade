package com.avanade.avanade.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import com.avanade.avanade.dto.ProdutoDTO;
import com.avanade.avanade.entity.Produto;
import com.avanade.avanade.repository.ProdutoRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Service
public class ProdutoService {
    
    private ProdutoRepository repository;
    public ProdutoService(ProdutoRepository repository){
        this.repository = repository;
    }

    private static final Logger logger = LoggerFactory.getLogger(ProdutoService.class);

    public ProdutoDTO create(ProdutoDTO dto) {
        Produto categoria = new Produto(dto);
        categoria = this.repository.save(categoria);
        return categoria.dto();
    }


    public ProdutoDTO getOne(Long id) {
        Optional<Produto> produtoOp = this.repository.findById(id);
        if (produtoOp.isPresent()) {
            return produtoOp.get().dto();
        }

        throw new EntityNotFoundException();
    }


    public List<ProdutoDTO> getAll() {
        List<ProdutoDTO> products = new ArrayList<>();
        try {
            products = this.repository.findAll().stream().map(product -> product.dto()).toList();
        } catch(Exception e) {
            logger.error("Error fetching products:", e);
        }

        return products;

    }

    public void delete(Long id) {
        this.repository.deleteById(id);

    }

    public void persistDataFromApi(List<Produto> products) {
        repository.saveAll(products);
    }

}
