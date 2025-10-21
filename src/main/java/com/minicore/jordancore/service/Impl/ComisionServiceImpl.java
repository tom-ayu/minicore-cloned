package com.minicore.jordancore.service.Impl;
import com.minicore.jordancore.service.ComisionService;
import com.minicore.jordancore.dto.ComisionDTO;
import com.minicore.jordancore.model.Regla;
import com.minicore.jordancore.model.Venta;
import com.minicore.jordancore.repository.ReglaRepository;
import com.minicore.jordancore.repository.VentaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ComisionServiceImpl implements ComisionService {

    private final VentaRepository ventaRepository;
    private final ReglaRepository reglaRepository;

    @Override
    public List<ComisionDTO> calcularComisiones(LocalDate fechaInicio, LocalDate fechaFin) {
        List<Venta> ventasEnRango = ventaRepository.findByFechaBetween(fechaInicio, fechaFin);
        List<Regla> reglasOrdenadas = reglaRepository.findAllByOrderByMontoMinimoVentaDesc();


        Map<String, BigDecimal> totalVentasPorVendedor = ventasEnRango.stream()
                .collect(Collectors.groupingBy(
                        venta -> venta.getVendedor().getUsuario(),
                        Collectors.mapping(Venta::getMonto, Collectors.reducing(BigDecimal.ZERO, BigDecimal::add))
                ));


        return totalVentasPorVendedor.entrySet().stream()
                .map(entry -> {
                    String nombreVendedor = entry.getKey();
                    BigDecimal totalVendido = entry.getValue();


                    Regla reglaAplicable = reglasOrdenadas.stream()
                            .filter(regla -> totalVendido.compareTo(regla.getMontoMinimoVenta()) >= 0)
                            .findFirst()
                            .orElse(null);

                    BigDecimal comisionCalculada = BigDecimal.ZERO;
                    String nombreReglaAplicada = "Sin comisión";

                    if (reglaAplicable != null) {
                        BigDecimal porcentaje = reglaAplicable.getPorcentajeComision();
                        comisionCalculada = totalVendido.multiply(porcentaje.divide(new BigDecimal("100")));
                        nombreReglaAplicada = reglaAplicable.getNombreRegla();
                    }

                    return new ComisionDTO(nombreVendedor, totalVendido, nombreReglaAplicada, comisionCalculada);
                })
                .collect(Collectors.toList());
    }
}