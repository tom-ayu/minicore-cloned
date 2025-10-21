package com.minicore.jordancore.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@Entity
@Table(name = "reglas")
@Data
@NoArgsConstructor
public class Regla {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRegla;


    private BigDecimal porcentajeComision;


    private BigDecimal montoMinimoVenta;


    private String nombreRegla;

    public Regla(String nombreRegla, BigDecimal porcentajeComision, BigDecimal montoMinimoVenta) {
        this.nombreRegla = nombreRegla;
        this.porcentajeComision = porcentajeComision;
        this.montoMinimoVenta = montoMinimoVenta;
    }
}