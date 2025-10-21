package com.minicore.jordancore.controller;

import com.minicore.jordancore.dto.ComisionDTO;
import com.minicore.jordancore.dto.RangoFechasDTO;
import com.minicore.jordancore.service.ComisionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/core")
@RequiredArgsConstructor
public class ComisionController {

    private final ComisionService comisionService;

    @PostMapping("/calcular")
    public ResponseEntity<List<ComisionDTO>> calcular(@RequestBody RangoFechasDTO request) {
        List<ComisionDTO> resultado = comisionService.calcularComisiones(
                request.getFechaInicio(),
                request.getFechaFin()
        );
        return ResponseEntity.ok(resultado);
    }
}