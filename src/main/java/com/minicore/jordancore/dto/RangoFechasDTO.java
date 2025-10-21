package com.minicore.jordancore.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class RangoFechasDTO {
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
}