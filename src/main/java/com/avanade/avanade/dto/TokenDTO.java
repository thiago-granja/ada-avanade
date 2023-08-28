package com.avanade.avanade.dto;

public record TokenDTO(String type, String token) {

    public TokenDTO(String token) {
        this("Bearer", token);
    }
}
