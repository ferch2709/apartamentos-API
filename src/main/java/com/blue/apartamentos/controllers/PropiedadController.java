package com.blue.apartamentos.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.blue.apartamentos.models.ClienteModel;
import com.blue.apartamentos.models.PropiedadModel;
import com.blue.apartamentos.models.PropiedadModel.EstadoHab;
import com.blue.apartamentos.models.PropiedadModel.TipoPropiedad;
import com.blue.apartamentos.services.PropiedadService;

@RestController
@RequestMapping("/api/propiedades")
public class PropiedadController {
    
    @Autowired
    private PropiedadService propiedadService;

    // Recuperar todas las propiedades 
    @GetMapping
    public ResponseEntity<List<PropiedadModel>> getAllPropiedades(
            @RequestParam(required = false) TipoPropiedad tipo,
            @RequestParam(required = false) EstadoHab estado) {
        
        if (tipo != null) {
            return ResponseEntity.ok(propiedadService.getPropiedadesByTipo(tipo));
        }
        if (estado != null) {
            return ResponseEntity.ok(propiedadService.getPropiedadesByEstado(estado));
        }
        return ResponseEntity.ok(propiedadService.getAllPropiedades());
    }

    // Propiedad por ID
    @GetMapping("/{id}")
    public ResponseEntity<PropiedadModel> getPropiedadById(@PathVariable Long id) {
        Optional<PropiedadModel> propiedad = propiedadService.getPropiedadById(id);
        return propiedad.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    //Recuperar propiedades por tipo (/api/propiedades/tipo/HABITACION)
    @GetMapping("/tipo/{tipo}")
    public ResponseEntity<List<PropiedadModel>> getPropiedadesByTipo(@PathVariable TipoPropiedad tipo) {
        List<PropiedadModel> propiedades = propiedadService.getPropiedadesByTipo(tipo);
        return ResponseEntity.ok(propiedades);
    }

    //Recuperar todas las propiedades de un propietario (/api/propiedades/propietario/1)
    @GetMapping("/propietario/{propietarioId}")
    public ResponseEntity<List<PropiedadModel>> getPropiedadesByPropietario(@PathVariable Long propietarioId) {
        ClienteModel propietario = new ClienteModel();
        propietario.setId(propietarioId);
        List<PropiedadModel> propiedades = propiedadService.getPropiedadesByPropietario(propietario);
        return ResponseEntity.ok(propiedades);
    }

    // Crear nueva propiedad (Retorna 201 CREATED)
    @PostMapping
    public ResponseEntity<PropiedadModel> createPropiedad(@RequestBody PropiedadModel propiedad) {
        PropiedadModel nuevaPropiedad = propiedadService.savePropiedad(propiedad);
        return new ResponseEntity<>(nuevaPropiedad, HttpStatus.CREATED);
    }

    // Actualizar propiedad existente
    @PutMapping("/{id}")
    public ResponseEntity<PropiedadModel> updatePropiedad(
            @PathVariable Long id, 
            @RequestBody PropiedadModel propiedadDetails) {
        try {
            PropiedadModel updatedPropiedad = propiedadService.updatePropiedad(id, propiedadDetails);
            return ResponseEntity.ok(updatedPropiedad);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Eliminar una propiedad
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePropiedad(@PathVariable Long id) {
        if (propiedadService.deletePropiedad(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}