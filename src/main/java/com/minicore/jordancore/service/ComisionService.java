package com.minicore.jordancore.service;

import com.minicore.jordancore.dto.ComisionDTO;
import java.time.LocalDate;
import java.util.List;

public interface ComisionService {
    List<ComisionDTO> calcularComisiones(LocalDate fechaInicio, LocalDate fechaFin);
}