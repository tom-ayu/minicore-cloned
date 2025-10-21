package com.minicore.jordancore.config;

import com.minicore.jordancore.model.Regla;
import com.minicore.jordancore.model.Vendedor;
import com.minicore.jordancore.model.Venta;
import com.minicore.jordancore.repository.ReglaRepository;
import com.minicore.jordancore.repository.VendedorRepository;
import com.minicore.jordancore.repository.VentaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final VendedorRepository vendedorRepository;
    private final VentaRepository ventaRepository;
    private final ReglaRepository reglaRepository;

    @Override
    public void run(String... args) throws Exception {

        Vendedor juan = new Vendedor("Juan Perez");
        Vendedor maria = new Vendedor("Maria Garcia");
        Vendedor pedro = new Vendedor("Pedro Lopez");
        vendedorRepository.saveAll(List.of(juan, maria, pedro));


        Regla reglaOro = new Regla("Comisión 10", new BigDecimal("10.0"), new BigDecimal("2000"));
        Regla reglaPlata = new Regla("Comisión 5", new BigDecimal("5.0"), new BigDecimal("1000"));
        Regla reglaBronce = new Regla("Comisión 2.5", new BigDecimal("2.5"), new BigDecimal("500"));
        reglaRepository.saveAll(List.of(reglaOro, reglaPlata, reglaBronce));


        ventaRepository.save(new Venta(LocalDate.of(2025, 6, 1), new BigDecimal("800.00"), juan));
        ventaRepository.save(new Venta(LocalDate.of(2025, 6, 5), new BigDecimal("300.50"), juan));


        ventaRepository.save(new Venta(LocalDate.of(2025, 6, 3), new BigDecimal("1500.00"), maria));
        ventaRepository.save(new Venta(LocalDate.of(2025, 6, 10), new BigDecimal("600.00"), maria));

        ventaRepository.save(new Venta(LocalDate.of(2025, 6, 8), new BigDecimal("450.00"), pedro));


        ventaRepository.save(new Venta(LocalDate.of(2025, 7, 15), new BigDecimal("5000.00"), juan));

        System.out.println("---- DATOS QUEMADOS CARGADOS CORRECTAMENTE ----");
    }
}