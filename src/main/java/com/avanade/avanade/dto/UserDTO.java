package com.avanade.avanade.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.br.CPF;
import java.time.LocalDate;

public record UserDTO(String name, @CPF String cpf, @Email String email, String username, @Size(min = 8, message = "Minimo 8 caracteres")  String password, LocalDate birthdate, String phone, String shippingAddress, Long id) {
    public UserDTO(String name, String cpf, String email, String username, LocalDate birthdate, String phone, String shippingAddress, Long id) {
        this(name, cpf, email, username, null, birthdate, phone, shippingAddress, id);
    }
}
