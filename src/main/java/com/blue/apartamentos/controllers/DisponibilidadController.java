package com.blue.apartamentos.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.blue.apartamentos.models.DisponibilidadModel;
import com.blue.apartamentos.services.DisponibilidadService;

@RestController
@RequestMapping("/api/disponibilidades")
public class DisponibilidadController {

    @Autowired
    private DisponibilidadService disponibilidadService;

    // Obtener todas las disponibilidades
    @GetMapping
    public ResponseEntity<List<DisponibilidadModel>> getAll() {
        return ResponseEntity.ok(disponibilidadService.getAllDisponibilidades());
    }

    // Obtener por ID
    @GetMapping("/{id}")
    public ResponseEntity<DisponibilidadModel> getById(@PathVariable Long id) {
        return disponibilidadService.getDisponibilidadById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Obtener por propiedad: GET /api/disponibilidades/propiedad/1
    @GetMapping("/propiedad/{idPropiedad}")
    public ResponseEntity<List<DisponibilidadModel>> getByPropiedad(@PathVariable Long idPropiedad) {
        return ResponseEntity.ok(disponibilidadService.getDisponibilidadesByPropiedad(idPropiedad));
    }

    // Crear disponibilidad o reserva 
    @PostMapping
    public ResponseEntity<DisponibilidadModel> create(@RequestBody DisponibilidadModel disponibilidad) {
        DisponibilidadModel nueva = disponibilidadService.saveDisponibilidad(disponibilidad);
        return new ResponseEntity<>(nueva, HttpStatus.CREATED);
    }

    // Actualizar
    @PutMapping("/{id}")
    public ResponseEntity<DisponibilidadModel> update(@PathVariable Long id, @RequestBody DisponibilidadModel details) {
        try {
            return ResponseEntity.ok(disponibilidadService.updateDisponibilidad(id, details));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Eliminar 
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (disponibilidadService.deleteDisponibilidad(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}