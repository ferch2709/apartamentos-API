package com.blue.apartamentos.services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blue.apartamentos.models.DisponibilidadModel;
import com.blue.apartamentos.repositories.IDisponibilidadRepository;

@Service
public class DisponibilidadService {

    @Autowired
    private IDisponibilidadRepository disponibilidadRepository;

    public List<DisponibilidadModel> getAllDisponibilidades() {
        return disponibilidadRepository.findAll();
    }

    public Optional<DisponibilidadModel> getDisponibilidadById(Long id) {
        return disponibilidadRepository.findById(id);
    }

    public List<DisponibilidadModel> getDisponibilidadesByPropiedad(Long idPropiedad) {
        return disponibilidadRepository.findByPropiedadIdPropiedad(idPropiedad);
    }

    public DisponibilidadModel saveDisponibilidad(DisponibilidadModel disponibilidad) {
        return disponibilidadRepository.save(disponibilidad);
    }

    public DisponibilidadModel updateDisponibilidad(Long id, DisponibilidadModel details) {
        DisponibilidadModel disp = disponibilidadRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Disponibilidad no encontrada con ID: " + id));

        disp.setFechaInicio(details.getFechaInicio());
        disp.setFechaFin(details.getFechaFin());
        disp.setDisponible(details.getDisponible());
        disp.setPrecioEspecial(details.getPrecioEspecial());
        if (details.getPropiedad() != null) {
            disp.setPropiedad(details.getPropiedad());
        }

        return disponibilidadRepository.save(disp);
    }

    public boolean deleteDisponibilidad(Long id) {
        if (disponibilidadRepository.existsById(id)) {
            disponibilidadRepository.deleteById(id);
            return true;
        }
        return false;
    }
}