package com.minicore.jordancore.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Entity
@Table(name = "vendedores")
@Data
@NoArgsConstructor
public class Vendedor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String usuario;

    @OneToMany(mappedBy = "vendedor")
    private List<Venta> ventas;

    public Vendedor(String usuario) {
        this.usuario = usuario;
    }
}