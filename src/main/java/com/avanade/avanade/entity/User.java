package com.avanade.avanade.entity;

import com.avanade.avanade.dto.UserDTO;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;

@Entity
@Data
@NoArgsConstructor
@Table(name = "Users")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(unique = true)
    private String username;
    private String password;
    private String name;
    private String email;
    private String phone;
    private String shippingAddress;
    private LocalDate birthdate;
    @CreationTimestamp
    private LocalDateTime dataRegistro;
    @UpdateTimestamp
    private LocalDateTime ultimaModDataRegistro;
    private String cpf;

    public User(UserDTO dto){
        this.username = dto.username();
        this.password = dto.password();
        this.birthdate = dto.birthdate();
        this.cpf = dto.cpf();
        this.email = dto.email();
        this.name = dto.name();
        this.shippingAddress = dto.shippingAddress();
        this.phone = dto.phone();
    }


    public User(UserDTO dto, Long id){
        this(dto);
        this.id = id;
    }

    public UserDTO dto() {
        return new UserDTO(this.getName(), this.getCpf(), this.getEmail(), this.username, this.getBirthdate(), this.getPhone(), this.getShippingAddress(), this.id);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
