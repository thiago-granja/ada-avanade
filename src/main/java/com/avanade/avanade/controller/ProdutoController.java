package com.avanade.avanade.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
/* import org.springframework.web.bind.annotation.PutMapping; */
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.avanade.avanade.dto.ProdutoDTO;
import com.avanade.avanade.entity.Produto;
import com.avanade.avanade.service.ProdutoService;
import com.avanade.avanade.wrapper.ProductApiResponseWrapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    private ProdutoService service;
    private static final Logger log = LoggerFactory.getLogger(ProdutoController.class);
    @Autowired
    public ProdutoController(ProdutoService service){
        this.service = service;
    }

    @GetMapping("/retrieve-from-external-api")
    public String retrieveProductsFromApi(){
        String url = "https://dummyjson.com/products/search?q=phone";
        try {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<ProductApiResponseWrapper> response = restTemplate.exchange(
            url,
            HttpMethod.GET,
            null,
            ProductApiResponseWrapper.class
        );
        List<Produto> products = response.getBody().getProducts();
        service.persistDataFromApi(products);
        } catch (HttpClientErrorException e) {
            log.error("Error calling external API", e);
            log.error("Response Body: {}", e.getResponseBodyAsString());
        }

        return "data retrieved and persisted successfully";
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProdutoDTO create(@RequestBody ProdutoDTO body) {
        return service.create(body);
    }

    @GetMapping("/{id}")
    public ProdutoDTO getOne(@PathVariable Long id) {
        return service.getOne(id);
    }

    @GetMapping()
    public ResponseEntity<?> getAll(@RequestParam(value = "filter", defaultValue = "") String filter) {
        try {
            List<ProdutoDTO> products = service.getAll();
            return new ResponseEntity<>(products, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error fetching products", e);
            return new ResponseEntity<>("Error fetching products: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}