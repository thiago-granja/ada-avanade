package com.avanade.avanade.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.avanade.avanade.entity.Order;
import com.avanade.avanade.entity.OrderItem;
import com.avanade.avanade.entity.Produto;
import com.avanade.avanade.exceptions.OutOfStockException;
import com.avanade.avanade.exceptions.ProductNotFoundException;
import com.avanade.avanade.repository.OrderRepository;
import com.avanade.avanade.repository.ProdutoRepository;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    public Order placeOrder(Order order) {
        
        for (OrderItem item : order.getOrderItems()) {
            Produto product = produtoRepository.findById(item.getProduto().getId())
                    .orElseThrow(() -> new ProductNotFoundException("Product with ID " + item.getProduto().getId() + " not found"));
            
            if (product.getStock() < item.getQuantity()) {
                throw new OutOfStockException("Product with ID " + product.getId() + " is out of stock");
            }
            
            product.setStock(product.getStock() - item.getQuantity());
            produtoRepository.save(product);
        }
        
        return orderRepository.save(order);
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Optional<Order> getOrderById(Long id) {
        return orderRepository.findById(id);
    }

    public List<Order> getOrdersByUsername(String username) {
        return orderRepository.findByUser_Username(username);
    }
    
    public Order createOrder(Order order) {
        return orderRepository.save(order);
    }

    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }

}
